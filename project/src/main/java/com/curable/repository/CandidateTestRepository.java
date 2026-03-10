package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


import com.curable.domain.CandidateTest;
import com.curable.service.dto.CandidateTestDTO;
import com.curable.service.dto.custom.CommonTestParamsDTO;
import com.curable.service.dto.custom.DiseaseEligibilityDTO;
@Repository
public interface CandidateTestRepository
		extends JpaRepository<CandidateTest, Long>, QuerydslPredicateExecutor<CandidateTest> {

	List<CandidateTest> findByCandidate_IdAndIsRecordDeletedFalse(Long candidateId);

	List<CandidateTest> findByDiseaseTestMaster_IdAndIsRecordDeletedFalse(Long diseaseTestMasterId);

	@Query("select  new com.curable.service.dto.custom.DiseaseEligibilityDTO(a.candidate.id,b.description,b.name,b.id,a.id) from CandidateTest a left join a.diseaseTestMaster b  "
			+ " where a.candidate.id in :ids and b.name=:name and a.completed=0 ")
	List<DiseaseEligibilityDTO> getEligibleTestScreeningOrClinic(List<Long> ids, String name);

	@Query("select  new com.curable.service.dto.custom.DiseaseEligibilityDTO(a.candidate.id,b.description,b.name,b.id,a.id) from CandidateTest a left join a.diseaseTestMaster b  "
			+ " where a.candidate.id in :ids and b.name=:name and a.testDate is null")
	List<DiseaseEligibilityDTO> getEligibleTestClinic(List<Long> ids, String name);

	@Query("select b.description from CandidateTest a left join a.diseaseTestMaster b where a.candidate.id=:candidateId and a.testResult is not null")
	List<String> completedTestBasedOnCandidateId(Long candidateId);

	CandidateTest findByCandidate_IdAndDiseaseTestMaster_IdAndIsRecordDeletedFalse(Long candidateId,
			Long candidateTestId);

	@Query("select count(a.id) from CandidateTest a left join DiseaseTestMaster b on b.id=a.diseaseTestMaster.id where "
			+ "a.candidate.id=:candidateId and b.name=:name and a.completed=0")
	long isScreeningOrClinicalCompleted(Long candidateId, String name);

	long countByCandidate_IdAndTestDateIsNullAndDiseaseTestMaster_Name(Long candidateId, String name);

	long countByCandidate_IdAndTestDateIsNullAndReferralIsNull(Long candidateId);

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("update CandidateTest set referral=:referral,testResult=:params,lastModifiedDate=:modifiedDate,lastModifiedBy=:modifiedBy,completed=:completed, "
			+ " dCreatedDate = COALESCE(dCreatedDate, :modifiedDate),dCreatedBy=COALESCE(dCreatedBy, :modifiedBy) "
			+ "where candidate.id=:candidateId and diseaseTestMaster.id=:testId")
	int updateFieldByCandidateId(Long candidateId, Boolean referral, CommonTestParamsDTO params, Long testId,
			Instant modifiedDate, String modifiedBy, int completed);

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("update CandidateTest set testDate=now(),testResult=:params,lastModifiedDate=:modifiedDate,lastModifiedBy=:modifiedBy,completed=:completed  where candidate.id=:candidateId and diseaseTestMaster.id=:testId")
	int updateFieldByCandidateId(Long candidateId, CommonTestParamsDTO params, Long testId, Instant modifiedDate,
			String modifiedBy, int completed);

	void deleteByCandidateIdAndDiseaseTestMaster_Id(Long candidateId, Long testId);

	long countByCandidate_IdAndIsRecordDeletedFalse(Long candidateId);

	@Query("select new com.curable.service.dto.CandidateTestDTO(id,candidate.id,diseaseTestMaster.id,testResult) from  CandidateTest where candidate.id=:candidateId and  diseaseTestMaster.id=:typeId ")
	CandidateTestDTO getScreeningOrClinicalData(Long candidateId, Long typeId);

	long countByCandidateIdAndDiseaseTestMaster_IdAndIsRecordDeletedFalse(Long candidateId, Long testId);

}
