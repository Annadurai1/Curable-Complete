CREATE  PROCEDURE `PatientRegReport`()
BEGIN
  WITH em AS (
    SELECT eligibilityMetrics, candidateId,
           ROW_NUMBER() OVER (PARTITION BY candidateId ORDER BY createdDate DESC) rn
    FROM candidatehistory
    WHERE eligibilityMetrics IS NOT NULL AND isRecordDeleted = 0
), 
fm AS (
    SELECT familyMedicalMetrics, candidateId,
           ROW_NUMBER() OVER (PARTITION BY candidateId ORDER BY createdDate DESC) rn
    FROM candidatehistory
    WHERE familyMedicalMetrics IS NOT NULL AND isRecordDeleted = 0 
), 
famm AS (
    SELECT familyMetrics, candidateId,
           ROW_NUMBER() OVER (PARTITION BY candidateId ORDER BY createdDate DESC) rn
    FROM candidatehistory
    WHERE familyMetrics IS NOT NULL AND isRecordDeleted = 0
),
eligibility_params AS (
  SELECT em.candidateId, jt.testName, jt.selectedValue
  FROM em
  JOIN JSON_TABLE(
    em.eligibilityMetrics,
    '$.params[*]'
    COLUMNS (
      testName VARCHAR(100) PATH '$.testName',
      selectedValue VARCHAR(100) PATH '$.selectedValues[0]'
    )
  ) jt ON TRUE
  WHERE em.rn = 1
),
eligibility_pivot AS (
  SELECT candidateId,
         MAX(CASE WHEN testName = 'Marital Status' THEN selectedValue END) AS eligibility_MaritalStatus,
         MAX(CASE WHEN testName = 'Hysterectomy Done' THEN selectedValue END) AS eligibility_HysterectomyDone
  FROM eligibility_params
  GROUP BY candidateId
),
familyMedical_params_flat AS (
  SELECT fm.candidateId, jt_outer.idx AS groupIndex, jt_inner.testName, jt_inner.selectedValue
  FROM fm
  JOIN JSON_TABLE(
    fm.familyMedicalMetrics,
    '$[*]'
    COLUMNS (idx FOR ORDINALITY, params JSON PATH '$.params')
  ) jt_outer ON TRUE
  JOIN JSON_TABLE(
    jt_outer.params,
    '$[*]'
    COLUMNS (testName VARCHAR(100) PATH '$.testName',
             selectedValue VARCHAR(100) PATH '$.selectedValues[0]')
  ) jt_inner ON TRUE
  WHERE fm.rn = 1
),
familyMedical_pivot AS (
  SELECT candidateId, groupIndex,
         MAX(CASE WHEN testName = 'Relation ' THEN selectedValue END) AS familyMedical_Relation,
         MAX(CASE WHEN testName = 'Cancer Site' THEN selectedValue END) AS familyMedical_CancerSite,
         MAX(CASE WHEN testName = 'Age At Diagnosis' THEN selectedValue END) AS familyMedical_AgeAtDiagnosis,
         MAX(CASE WHEN testName = 'Outcome' THEN selectedValue END) AS familyMedical_Outcome
  FROM familyMedical_params_flat
  GROUP BY candidateId, groupIndex
),
family_params_flat AS (
  SELECT famm.candidateId, jt_outer.idx AS groupIndex, jt_inner.testName, jt_inner.selectedValue
  FROM famm
  JOIN JSON_TABLE(
    famm.familyMetrics,
    '$[*]'
    COLUMNS (idx FOR ORDINALITY, params JSON PATH '$.params')
  ) jt_outer ON TRUE
  JOIN JSON_TABLE(
    jt_outer.params,
    '$[*]'
    COLUMNS (testName VARCHAR(100) PATH '$.testName',
             selectedValue VARCHAR(100) PATH '$.selectedValues[0]')
  ) jt_inner ON TRUE
  WHERE famm.rn = 1
),
family_pivot AS (
  SELECT candidateId, groupIndex,
         MAX(CASE WHEN testName = 'Name' THEN selectedValue END) AS family_Name,
         MAX(CASE WHEN testName = 'Relation ' THEN selectedValue END) AS family_Relation
  FROM family_params_flat
  GROUP BY candidateId, groupIndex
),
-- ✅ merge family + familyMedical to avoid cross join
merged_family AS (
  SELECT COALESCE(fp.candidateId, fmp.candidateId) AS candidateId,
         COALESCE(fp.groupIndex, fmp.groupIndex) AS groupIndex,
         fp.family_Name,
         fp.family_Relation,
         fmp.familyMedical_Relation,
         fmp.familyMedical_CancerSite,
         fmp.familyMedical_AgeAtDiagnosis,
         fmp.familyMedical_Outcome
  FROM family_pivot fp
  LEFT JOIN familyMedical_pivot fmp
         ON fp.candidateId = fmp.candidateId
        AND fp.groupIndex = fmp.groupIndex
  UNION
  SELECT COALESCE(fp.candidateId, fmp.candidateId) AS candidateId,
         COALESCE(fp.groupIndex, fmp.groupIndex) AS groupIndex,
         fp.family_Name,
         fp.family_Relation,
         fmp.familyMedical_Relation,
         fmp.familyMedical_CancerSite,
         fmp.familyMedical_AgeAtDiagnosis,
         fmp.familyMedical_Outcome
  FROM familyMedical_pivot fmp
  LEFT JOIN family_pivot fp
         ON fp.candidateId = fmp.candidateId
        AND fp.groupIndex = fmp.groupIndex
),
final_result AS (
  SELECT a.registraionId, a.name, a.mobileNo,a.gender,  a.age, a.address, a.streetId,
  a.fatherName,a.spouseName,a.alternateMobileNo,a.monthlyIncome,a.houseType,a.education,a.occupation,a.aadhar, a.voterId,a.rationCard,
         c.medicalhistory,c.bloodPressure,c.pulseRate,c.weight,c.historyOfSurgery,c.height,c.spo2,c.allergy,c.otherComplaints,
         c.ageAtMenarche,c.ageAtFirstChild,c.ageAtLastChild,c.ageAtMarriage,c.lastMentrution,c.abnormalBleedingVaginum,
         c.totalPregnancies,c.currentlyPregant,c.methodOfContraceptionUsed,c.undergoneCervicalBreastScrening,
         -- ✅ habit columns
         h.duration, h.frequency, h.habits, h.howLong, h.quit, h.type,
         ep.eligibility_MaritalStatus, ep.eligibility_HysterectomyDone,
         mf.family_Name, mf.family_Relation,
         mf.familyMedical_Relation, mf.familyMedical_CancerSite,
         mf.familyMedical_AgeAtDiagnosis, mf.familyMedical_Outcome,
         mf.groupIndex,
         ROW_NUMBER() OVER (PARTITION BY a.id ORDER BY mf.groupIndex) rn
  FROM candidate a
  LEFT JOIN candidatemedicaldetails c ON c.candidateId = a.id
  left join candidatehabit h on h.candidateId=a.id
  LEFT JOIN em ON em.candidateId = a.id AND em.rn = 1
  LEFT JOIN eligibility_pivot ep ON ep.candidateId = a.id
  LEFT JOIN merged_family mf ON mf.candidateId = a.id
  WHERE a.isRecordDeleted=false
)
SELECT registraionId, name, mobileNo, gender, age, address, streetId,
       fatherName, spouseName, alternateMobileNo, monthlyIncome, houseType, education, occupation, aadhar, voterId, rationCard,
       medicalhistory, bloodPressure, pulseRate, weight, historyOfSurgery, height, spo2, allergy, otherComplaints,
       ageAtMenarche, ageAtFirstChild, ageAtLastChild, ageAtMarriage, lastMentrution, abnormalBleedingVaginum,
       totalPregnancies, currentlyPregant, methodOfContraceptionUsed, undergoneCervicalBreastScrening,
       duration, frequency, habits, howLong, quit, type,

       -- ✅ eligibility values shown only in first row
       CASE WHEN rn = 1 THEN eligibility_MaritalStatus END AS eligibility_MaritalStatus,
       CASE WHEN rn = 1 THEN eligibility_HysterectomyDone END AS eligibility_HysterectomyDone,

       family_Name, family_Relation,
       familyMedical_Relation, familyMedical_CancerSite,
       familyMedical_AgeAtDiagnosis, familyMedical_Outcome,
       groupIndex
FROM final_result
ORDER BY groupIndex;

END