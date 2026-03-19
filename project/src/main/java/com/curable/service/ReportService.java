package com.curable.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curable.repository.CampRepository;
import com.curable.repository.CandidateRepository;
import com.curable.service.dto.CampsReportDTO;
import com.curable.service.dto.ClinicalReportDTO;
import com.curable.service.dto.DownloadRequestDTO;
import com.curable.service.dto.PatientRegReportDTO;
import com.curable.service.dto.SavedPatientDTO;
import com.curable.service.dto.ScreeningReportDTO;
import com.curable.service.dto.custom.CommonTestParamsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;

@Service
@Transactional
public class ReportService {
	@Autowired
	CampRepository campRepository;

	@Autowired
	CandidateRepository candidateRepository;

	public byte[] generateReport(DownloadRequestDTO downloadRequestDTO) throws Exception {
		switch (downloadRequestDTO.getReportType()) {
			case Outreachclinic:
				return generateCampsReportCsv(downloadRequestDTO);
			case Patientregistration:
				return generatePatientRegReportCsv(downloadRequestDTO);
			case Screening:
				return generateScreeningReportCsv(downloadRequestDTO);
			case SavedPatient:
				return generateSavedPatientCsv(downloadRequestDTO);
			case Clinical_Evaluation:
				return generateClinicalReportCsv(downloadRequestDTO);
			case UniquePatientReport:
				return uniquePatientReport(downloadRequestDTO);
			default:
				break;
		}
		return null;
	}

	public byte[] uniquePatientReport(DownloadRequestDTO downloadRequestDTO) throws IOException {
		String campIdsStr = downloadRequestDTO.getIds().stream().map(String::valueOf).collect(Collectors.joining(","));
		List<SavedPatientDTO> patients = candidateRepository.uniquePatientReport(campIdsStr,
				downloadRequestDTO.getStartDate(), downloadRequestDTO.getEndDate());

		if (patients.isEmpty()) {
			return new byte[0];
		}
		try (StringWriter sw = new StringWriter(); CSVWriter csvWriter = new CSVWriter(sw)) {
			// CSV Header
			String[] header = { "OUTREACH CLINIC ID", "OUTREACH CLINIC NAME", "CREATED DATE", "CREATED BY",
					"REGISTRATIONID", "NAME", "MOBILE NO", "GENDER", "AGE", "ADDRESS", "STREETID", "FATHER NAME",
					"SPOUSE NAME", "ALTERNATE MOBILE NO", "MONTHLY INCOME", "HOUSE TYPE", "EDUCATION", "OCCUPATION",
					"AADHAR", "VOTER ID", "RATION CARD", "MEDICAL HISTORY", "BLOOD PRESSURE", "PULSE RATE", "WEIGHT",
					"HISTORY OF SURGERY", "HEIGHT", "SPO2", "ALLERGY", "OTHER COMPLAINTS", "AGE AT MENARCHE",
					"AGE AT FIRST CHILD", "AGE AT LAST CHILD", "AGE AT MARRIAGE", "LAST MENSTRUATION",
					"ABNORMAL BLEEDING VAGINUM", "TOTAL PREGNANCIES", "CURRENTLY PREGNANT",
					"METHOD OF CONTRACEPTION USED", "UNDERGONE CERVICAL BREAST SCREENING", "BREAST FED",
					"SOCIAL HABITS", "ELIGIBILITY METRICS" };
			csvWriter.writeNext(header);
			ObjectMapper objectMapper = new ObjectMapper();
			// CSV Rows
			for (SavedPatientDTO dto : patients) {
				CommonTestParamsDTO eligibilityMetrics = null;
				if (dto.getEligibilityMetrics() != null) {
					eligibilityMetrics = objectMapper.readValue(dto.getEligibilityMetrics(), CommonTestParamsDTO.class);
				}

				System.out.println(dto.getEligibilityMetrics());
				String[] row = { convertUpperCaseWithForceText(dto.getOutreachClinicID()),
						convertUpperCase(dto.getOutreachClinicName()),

						convertUpperCaseWithForceText(dto.getCreatedDate()), convertUpperCaseWithForceText(dto.getCreatedBy()),

						convertUpperCaseWithForceText(dto.getRegistraionId()), convertUpperCase(dto.getName()),
						convertUpperCaseWithForceText(dto.getMobileNo()), convertUpperCase(dto.getGender()),

						convertUpperCase(dto.getAge() != null ? dto.getAge().toString() : ""),
						convertUpperCase(dto.getAddress()),

						convertUpperCaseWithForceText(
								dto.getStreetId() != null ? String.format("%03d", Integer.parseInt(dto.getStreetId()))
										: "000"),

						convertUpperCase(dto.getFatherName()), convertUpperCase(dto.getSpouseName()),
						convertUpperCase(dto.getAlternateMobileNo()),

						convertUpperCase(dto.getMonthlyIncome()), convertUpperCase(dto.getHouseType()),
						convertUpperCase(dto.getEducation()), convertUpperCase(dto.getOccupation()),

						convertUpperCase(dto.getAadhar()), convertUpperCase(dto.getVoterId()),
						convertUpperCase(dto.getRationCard()),

						convertUpperCase(dto.getMedicalhistory()), convertUpperCase(dto.getBloodPressure()),
						convertUpperCase(dto.getPulseRate()), convertUpperCase(dto.getWeight()),
						convertUpperCase(dto.getHistoryOfSurgery()), convertUpperCase(dto.getHeight()),
						convertUpperCase(dto.getSpo2()), convertUpperCase(dto.getAllergy()),
						convertUpperCase(dto.getOtherComplaints()),

						convertUpperCase(dto.getAgeAtMenarche() != null ? dto.getAgeAtMenarche().toString() : ""),
						convertUpperCase(dto.getAgeAtFirstChild() != null ? dto.getAgeAtFirstChild().toString() : ""),
						convertUpperCase(dto.getAgeAtLastChild() != null ? dto.getAgeAtLastChild().toString() : ""),
						convertUpperCase(dto.getAgeAtMarriage() != null ? dto.getAgeAtMarriage().toString() : ""),

						convertUpperCase(dto.getLastMenstruation()), convertUpperCase(dto.getAbnormalBleedingVaginum()),

						convertUpperCase(dto.getTotalPregnancies() != null ? dto.getTotalPregnancies().toString() : ""),
						convertUpperCase(dto.getCurrentlyPregnant()),

						convertUpperCase(dto.getMethodOfContraceptionUsed()),
						convertUpperCase(dto.getUndergoneCervicalBreastScreening()),
						convertUpperCase(dto.getBreastFed()),

						convertUpperCase(dto.getSocialHabits()),

						convertUpperCase((eligibilityMetrics != null && eligibilityMetrics.getParams() != null
								&& !eligibilityMetrics.getParams().isEmpty()
								&& eligibilityMetrics.getParams().get(0).getSelectedValues() != null)
								? safe(eligibilityMetrics.getParams().get(0).getSelectedValues().get(0))
								: "") };
				csvWriter.writeNext(row, true);
			}

			csvWriter.flush();
			return sw.toString().getBytes(StandardCharsets.UTF_8);
		}

	}

	public byte[] generateSavedPatientCsv(DownloadRequestDTO downloadRequestDTO) throws Exception {
		String campIdsStr = downloadRequestDTO.getIds().stream().map(String::valueOf).collect(Collectors.joining(","));
		List<SavedPatientDTO> patients = candidateRepository.findSavedPatients(campIdsStr,
				downloadRequestDTO.getStartDate(), downloadRequestDTO.getEndDate());

		if (patients.isEmpty()) {
			return new byte[0];
		}

		try (StringWriter sw = new StringWriter(); CSVWriter csvWriter = new CSVWriter(sw)) {
			// CSV Header
			String[] header = { "REGISTRATIONID", "NAME", "MOBILE NO", "GENDER", "AGE", "ADDRESS", "STREETID", "REASON",
					"CREATED DATE", "CREATED BY" };
			csvWriter.writeNext(header);

			// CSV Rows
			for (SavedPatientDTO dto : patients) {
				String[] row = { convertUpperCaseWithForceText(dto.getRegistraionId()), convertUpperCase(dto.getName()),
						convertUpperCase(dto.getMobileNo()), convertUpperCase(dto.getGender()),
						convertUpperCase(dto.getAge() != null ? dto.getAge().toString() : ""),
						convertUpperCase(dto.getAddress()),
						convertUpperCaseWithForceText(
								dto.getStreetId() != null ? String.format("%03d", Integer.parseInt(dto.getStreetId()))
										: "000"),
						convertUpperCase(dto.getReason()), convertUpperCase(forceText(dto.getCreatedDate())),
						convertUpperCase(dto.getCreatedBy()) };
				csvWriter.writeNext(row, true);
			}

			csvWriter.flush();
			return sw.toString().getBytes(StandardCharsets.UTF_8);
		}
	}

	public byte[] generateClinicalReportCsv(DownloadRequestDTO downloadRequestDTO) throws Exception {
		String campIdStr = downloadRequestDTO.getIds().stream().map(String::valueOf).collect(Collectors.joining(","));
		List<ClinicalReportDTO> reports = candidateRepository.clinicalReport(campIdStr,
				downloadRequestDTO.getStartDate(), downloadRequestDTO.getEndDate());

		if (reports.isEmpty()) {
			return new byte[0];
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		reports.sort(Comparator.comparing(
				dto -> dto.getBreastCreatedDate() == null ? null
						: LocalDateTime.parse(dto.getBreastCreatedDate(), formatter),
				Comparator.nullsLast(Comparator.reverseOrder())));
		try (StringWriter sw = new StringWriter(); CSVWriter csvWriter = new CSVWriter(sw)) {

			// ✅ Header
			String[] header = { "REGISTRATIONID", "CANDIDATE NAME",

					// --- BREAST ---
					"BREAST CREATED DATE", "CREATED BY", "MODIFIED BY", "BREAST CLINICAL EXAMINATION", "UHID",
					"SCR OPD NUMBER", "MAMMOGRAM", "MAMMOGRAM RIGHT BREASTS", "MAMMOGRAM LEFT BREASTS",
					"MAMMOGRAM REPORT DATE", "ULTRASOUND", "ULTRASOUND RIGHT BREASTS", "ULTRASOUND LEFT BREASTS",
					"ULTRASOUND REPORT DATE", "BREAST CYTOLOGY", "BREAST CYTOLOGY REPORT DATE", "BREAST CYTOLOGY IMAGE",
					"OTHER FINDINGS", "BREAST BIOPSY", "BREAST BIOPSY REPORT DATE", "BREAST BIOPSY REPORT",
					"ADDITIONAL COMMENTS", "FINAL DIAGNOSIS", "TREATMENT", "FOLLOWUP ADVICE",

					// --- CERVICAL ---
					"CERVICAL CREATED DATE", "CREATED BY", "MODIFIED BY", "CERVICAL CLINICAL EXAMINATION", "UHID",
					"OPD NUMBER", "COLPOSCOPY", "COLPOSCOPY IMAGE", "COLPOSCOPY REPORT", "COLPOSCOPY REPORT DATE",
					"COLPO GUIDED BIOPSY", "COLPO BIOPSY REPORT DATE", "COLPO BIOPSY IMAGE", "ADDITIONAL COMMENTS",
					"TREATMENT PLAN", "TREATMENT", "FOLLOWUP ADVICE",

					// --- ORAL ---
					"ORAL CREATED DATE", "CREATED BY", "MODIFIED BY", "ORAL CLINICAL EXAMINATION", "UHID",
					"SCR OPD NUMBER", "TOBACCO COUNSELLING", "TOBACCO COUNSELLING OUTCOME",
					"TOBACCO COUNSELLING REPORT DATE", "HEAD & NECK OPD", "HEAD & NECK REPORT DATE",
					"HEAD & NECK OUTCOME", "ORAL CYTOLOGY", "ORAL CYTOLOGY REPORT", "ORAL CYTOLOGY REPORT DATE",
					"ORAL BIOPSY", "ORAL BIOPSY REPORT", "ORAL BIOPSY REPORT DATE", "ADDITIONAL COMMENTS",
					"FINAL DIAGNOSIS", "TREATMENT", "FOLLOWUP ADVICE" };
			csvWriter.writeNext(header);

			for (ClinicalReportDTO dto : reports) {
				if (dto.getBreastClinicalExam() == null && dto.getCervicalClinicalExam() == null
						&& dto.getOralClinicalEvaluation() == null) {
					continue;
				}

				String[] row = {
						// Registration Info
						convertUpperCaseWithForceText(dto.getRegistrationId() != null ? dto.getRegistrationId() : ""),
						convertUpperCase(dto.getCandidateName() != null ? dto.getCandidateName() : ""),

						// --- Breast ---
						convertUpperCaseWithForceText(
								dto.getBreastCreatedDate() != null ? dto.getBreastCreatedDate() : ""),
						convertUpperCase(dto.getBreastcBy()), convertUpperCase(dto.getBreastlBy()),
						convertUpperCase(dto.getBreastClinicalExam()), convertUpperCase(dto.getBreastUhid()),
						convertUpperCase(dto.getBreastScrOpdNumber()), convertUpperCase(dto.getBreastMammogram()),
						convertUpperCase(dto.getBreastMammogramRight()), convertUpperCase(dto.getBreastMammogramLeft()),
						convertUpperCase(dto.getBreastMammogramReportDate()),
						convertUpperCase(dto.getBreastUltrasound()), convertUpperCase(dto.getBreastUltrasoundRight()),
						convertUpperCase(dto.getBreastUltrasoundLeft()),
						convertUpperCase(dto.getBreastUltrasoundReportDate()),
						convertUpperCase(dto.getBreastCytology()), convertUpperCase(dto.getBreastCytologyReportDate()),
						convertUpperCase(dto.getBreastCytologyImage()), convertUpperCase(dto.getBreastOtherFindings()),
						convertUpperCase(dto.getBreastBiopsy()), convertUpperCase(dto.getBreastBiopsyReportDate()),
						convertUpperCase(dto.getBreastBiopsyReport()),
						convertUpperCase(dto.getBreastAdditionalComments()),
						convertUpperCase(dto.getBreastFinalDiagnosis()), convertUpperCase(dto.getBreastTreatment()),
						convertUpperCase(dto.getBreastFollowupAdvice()),

						// --- Cervical ---
						convertUpperCaseWithForceText(
								dto.getCervicalCreatedDate() != null ? dto.getCervicalCreatedDate() : ""),
						convertUpperCase(dto.getCervicalcBy()), convertUpperCase(dto.getCervicallBy()),
						convertUpperCase(dto.getCervicalClinicalExam()), convertUpperCase(dto.getCervicalUhid()),
						convertUpperCase(dto.getCervicalOpdNumber()), convertUpperCase(dto.getCervicalColposcopy()),
						convertUpperCase(dto.getCervicalImage()), convertUpperCase(dto.getCervicalColposcopyReport()),
						convertUpperCase(dto.getCervicalColposcopyReportDate()),
						convertUpperCase(dto.getCervicalColpoBiopsy()),
						convertUpperCase(dto.getCervicalColpoBiopsyReportDate()),
						convertUpperCase(dto.getCervicalColpoBiopsyImage()),
						convertUpperCase(dto.getCervicalAdditionalComments()),
						convertUpperCase(dto.getCervicalTreatmentPlan()), convertUpperCase(dto.getCervicalTreatment()),
						convertUpperCase(dto.getCervicalFollowupAdvice()),

						// --- Oral ---
						convertUpperCaseWithForceText(dto.getOralCreatedDate() != null ? dto.getOralCreatedDate() : ""),
						convertUpperCase(dto.getOralcBy()), convertUpperCase(dto.getOrallBy()),
						convertUpperCase(dto.getOralClinicalEvaluation()), convertUpperCase(dto.getOralUhid()),
						convertUpperCase(dto.getOralOpdNumber()), convertUpperCase(dto.getOralTobaccoCounselling()),
						convertUpperCase(dto.getOralTobaccoOutcome()), convertUpperCase(dto.getOralTobaccoReportDate()),
						convertUpperCase(dto.getOralHeadNeckOpd()), convertUpperCase(dto.getOralHeadNeckReportDate()),
						convertUpperCase(dto.getOralHeadNeckOutcome()), convertUpperCase(dto.getOralCytology()),
						convertUpperCase(dto.getOralCytologyReport()),
						convertUpperCase(dto.getOralCytologyReportDate()), convertUpperCase(dto.getOralBiopsy()),
						convertUpperCase(dto.getOralBiopsyReport()), convertUpperCase(dto.getOralBiopsyReportDate()),
						convertUpperCase(dto.getOralAdditionalComments()),
						convertUpperCase(dto.getOralFinalDiagnosis()), convertUpperCase(dto.getOralTreatment()),
						convertUpperCase(dto.getOralFollowupAdvice()) };

				csvWriter.writeNext(row, true);
			}

			csvWriter.flush();
			return sw.toString().getBytes(StandardCharsets.UTF_8);
		}
	}

	public byte[] generateScreeningReportCsv(DownloadRequestDTO downloadRequestDTO) throws Exception {
		String campIdsStr = downloadRequestDTO.getIds().stream().map(String::valueOf).collect(Collectors.joining(","));
		List<ScreeningReportDTO> reportData = candidateRepository.screeningReport(campIdsStr,
				downloadRequestDTO.getStartDate(), downloadRequestDTO.getEndDate());
		if (reportData.isEmpty()) {
			return new byte[0];
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		reportData.sort(Comparator.comparing(
				dto -> dto.getBreastCreatedDate() == null ? null
						: LocalDateTime.parse(dto.getBreastCreatedDate(), formatter),
				Comparator.nullsLast(Comparator.reverseOrder())));
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 OutputStreamWriter osw = new OutputStreamWriter(baos, StandardCharsets.UTF_8);
			 CSVWriter csvWriter = new CSVWriter(osw)) {

			String[] header = { "REGISTRATIONID", "CANDIDATENAME", "BREASTCREATEDDATE", "CREATED BY", "MODIFIED BY",
					"BREASTSCREENING", "BREASTSYMPTOMS", "RIGHTBREASTONPALPATION", "LEFTBREASTONPALPATION",
					"NIPPLEDISCHARGE", "BREASTREFERRAL", "BREAST/PATHOLOGY AXILLARY", "BASELINE MAMMOGRAM",
					"USG BREAST", "STATUS",

					"CERVICALCREATEDDATE", "CREATED BY", "MODIFIED BY", "CERVICALSCREENING", "CERVICALREASON",
					"CERVICALSYMPTOMS", "CERVICALABNORMALBLEEDING", "CERVICALVISUALINSPECTION",
					"CERVICALFINDINGSONVISUALINSPECTION", "HR-HPV DNA", "HR-HPV DNA SAMPLE COLLECTION DATE",
					"HR-HPV SAMPLE TYPE", "HR-HPV DNA REPORT DATE", "HR-HPV DNA REPORT", "GENOTYPE", "CYTOLOGY",
					"CYTOLOGY SAMPLE COLLECTION DATE", "CYTOLOGY REPORT DATE", "CYTOLOGY REPORT", "VISUAL TEST",
					"VISUAL TEST REPORT", "VISUAL TEST REPORT DATE", "CERVICALREFERRAL", "COLPOSCOPY",
					"COLPO GUIDED BIOPSY", "OTHERS", "STATUS",

					"ORAL CREATED DATE", "CREATED BY", "MODIFIED BY", "ORAL SCREENING", "ORAL SYMPTOMS",
					"BENIGN LESION SUBTYPE", "DENTAL HYGIENE", "CLINICAL FINDINGS", "ORAL SELECT SUBTYPE",
					"ORAL MALIGNANT LEUKOPLAKIA", "LEUKOPLAKIA TYPE", "LESION SITE", "LESION SIZE", "LESION SUBTYPE",
					"MULTIPLE LESION SITE", "MULTIPLE LESION SIZE", "MULTIPLE LESION SUBTYPE",
					"ORAL MALIGNANT ERYTHROPLAKIA", "ERYTHROPLAKIA SITE", "ORAL SUBMUCOUS FIBROSIS",
					"ORAL SUBMUCOUS FIBROSIS TYPE", "NON HEALING ULCER", "NON HEALING ULCER SITE",
					"NON HEALING ULCER SIZE", "SMOKERS PALATE", "LICHEN PLANUS", "LICHEN PLANUS SITE",
					"ORAL LICHENOID REACTION", "ORAL LICHENOID REACTION SITE", "OTHER LESIONS FLAG",
					"OTHER LESIONS TEXT", "LESION INVASIVE SITE", "LESION INVASIVE SIZE", "LESION INVASIVE APPEARANCE",
					"ORAL REFERRAL", "TOBACCO CESSATION COUNSELING", "ORAL MALIGNANT POTENTIAL DISEASE",
					"CANCEROUS OR INVASIVE LESION", "OTHERS", "STATUS",

					// ✅ SYMPTOMS BASED CANCER
					"SYMPCREATEDDATE", "CREATED BY", "MODIFIED BY", "SYMPTOMSBASEDREFERRAL", "SYMPTOMS",
					"DURATIONOFSYMPTOMS", "KNOWNCANCERPATIENT", "CANCERSITE", "DETAILSOFTREATMENT", "REFERRAL",
					"INVESTIGATIONS", "REPORTS", "REPORTFINDINGS", "FINALDIAGNOSIS", "STATUS" };

			csvWriter.writeNext(header);

			for (ScreeningReportDTO dto : reportData) {
				if (dto.getBreastExam() == null && dto.getCervicalScreening() == null && dto.getOralExam() == null
						&& dto.getSympBasedReferral() == null) {
					continue;
				}
				List<String> data = new ArrayList<>();

				// General Info
				data.add(convertUpperCaseWithForceText(dto.getRegistraionId()));
				data.add(convertUpperCase(dto.getCandidateName()));

				// --- Breast ---
				data.add(convertUpperCaseWithForceText(dto.getBreastCreatedDate()));
				data.add(convertUpperCase(dto.getBreastcBy()));
				data.add(convertUpperCase(dto.getBreastlBy()));
				data.add(convertUpperCase(dto.getBreastExam()));
				data.add(convertUpperCase(dto.getBreastSymptoms()));
				data.add(convertUpperCase(dto.getRightBreastOnPalpation()));
				data.add(convertUpperCase(dto.getLeftBreastOnPalpation()));
				data.add(convertUpperCase(dto.getBreastNippleDischarge()));
				data.add(convertUpperCase(dto.getBreastReferral()));
				/*
				 * "BREAST/PATHOLOGY AXILLARY", "BASELINE MAMMOGRAM", "USG BREAST",
				 */
				String bC1 = "";
				String bC2 = "";
				String bC3 = "";
				if (dto.getBreastReferral() != null && dto.getBreastReferral().equals("Yes")
						&& dto.getBreastReferredFor() != null) {
					// Normalize list to upper case, trimmed
					Set<String> referredSet = Arrays.stream(dto.getBreastReferredFor().split(",")).map(String::trim)
							.map(String::toUpperCase).collect(Collectors.toSet());
					bC1 = referredSet.contains("BREAST/PATHOLOGY AXILLARY") ? "YES" : "NO";
					bC2 = referredSet.contains("BASELINE MAMMOGRAM") ? "YES" : "NO";
					bC3 = referredSet.contains("USG BREAST") ? "YES" : "NO";

				} else if (dto.getBreastReferral() != null && dto.getBreastReferral().equals("No")) {
					bC1 = "NO";
					bC2 = "NO";
					bC3 = "NO";
				}
				data.add(convertUpperCase(bC1));
				data.add(convertUpperCase(bC2));
				data.add(convertUpperCase(bC3));
				data.add((dto.getBreastCompleted() != null && dto.getBreastCompleted() == 1) ? "COMPLETED" : "PENDING");
				// --- Cervical ---
				data.add(convertUpperCaseWithForceText(dto.getCervicalCreatedDate()));
				data.add(convertUpperCase(dto.getCervicalcBy()));
				data.add(convertUpperCase(dto.getCervicallBy()));
				data.add(convertUpperCase(dto.getCervicalScreening()));
				data.add(convertUpperCase(dto.getCervicalReason()));
				data.add(convertUpperCase(dto.getCervicalSymptoms()));
				data.add(convertUpperCase(dto.getCervicalAbnormalBleeding()));
				data.add(convertUpperCase(dto.getCervicalVisualInspection()));
				data.add(convertUpperCase(dto.getCervicalFindingsOnVisualInspection()));
				data.add(convertUpperCase(dto.getCervicalHRHPVDNA()));
				data.add(convertUpperCase(dto.getCervicalHSampleCollectionDate()));
				data.add(convertUpperCase(dto.getCervicalHSampleType()));
				data.add(convertUpperCase(dto.getCervicalHSampleReportDate()));
				data.add(convertUpperCase(dto.getCervicalHReport()));
				data.add(convertUpperCase(dto.getGenotype()));
				data.add(convertUpperCase(dto.getCervicalCytology()));
				data.add(convertUpperCase(dto.getCervicalCSampleCollectionDate()));
				data.add(convertUpperCase(dto.getCervicalCReportDate()));
				data.add(convertUpperCase(dto.getCervicalCReport()));
				data.add(convertUpperCase(dto.getCervicalVisualTest()));
				data.add(convertUpperCase(dto.getCervicalVReport()));
				data.add(convertUpperCase(dto.getCervicalVReportDate()));
				data.add(convertUpperCase(dto.getCervicalReferral()));

				/*
				 * "COLPOSCOPY","COLPO GUIDED BIOPSY", "OTHERS",
				 */
				String cC1 = "";
				String cC2 = "";
				String cC3 = "";
				if (dto.getCervicalReferral() != null && dto.getCervicalReferral().equals("Yes")
						&& dto.getCervicalReferredFor() != null) {
					// Normalize list to upper case, trimmed
					Set<String> referredSet = Arrays.stream(dto.getCervicalReferredFor().split(",")).map(String::trim)
							.map(String::toUpperCase).collect(Collectors.toSet());
					cC1 = referredSet.contains("COLPOSCOPY") ? "YES" : "NO";
					cC2 = referredSet.contains("COLPO GUIDED BIOPSY") ? "YES" : "NO";
					cC3 = referredSet.contains("OTHERS") ? "YES" : "NO";

				} else if (dto.getCervicalReferral() != null && dto.getCervicalReferral().equals("No")) {
					cC1 = "NO";
					cC2 = "NO";
					cC3 = "NO";
				}
				data.add(convertUpperCase(cC1));
				data.add(convertUpperCase(cC2));
				data.add(convertUpperCase(cC3));
				data.add((dto.getCervicalCompleted() != null && dto.getCervicalCompleted() == 1) ? "COMPLETED"
						: "PENDING");

				// --- Oral ---
				data.add(convertUpperCaseWithForceText(dto.getOralCreatedDate()));
				data.add(convertUpperCase(dto.getOralcBy()));
				data.add(convertUpperCase(dto.getOrallBy()));
				data.add(convertUpperCase(dto.getOralExam()));
				data.add(convertUpperCase(dto.getOralSymptoms()));
				data.add(convertUpperCase(dto.getOralSelectSymptoms()));
				data.add(convertUpperCase(dto.getDentalHygiene()));
				data.add(convertUpperCase(dto.getClinicalFindings()));
				data.add(convertUpperCase(dto.getOralSelectSubtype()));
				data.add(convertUpperCase(dto.getOralMalignantLeukoplakia()));
				data.add(convertUpperCase(dto.getLeukoplakiaType()));
				data.add(convertUpperCase(dto.getLesionSite()));
				data.add(convertUpperCase(dto.getLesionSize()));
				data.add(convertUpperCase(dto.getLesionSubtype()));
				data.add(convertUpperCase(dto.getMultipleLesionSite()));
				data.add(convertUpperCase(dto.getMultipleLesionSize()));
				data.add(convertUpperCase(dto.getMultipleLesionSubtype()));
				data.add(convertUpperCase(dto.getOralMalignantErythroplakia()));
				data.add(convertUpperCase(dto.getErythroplakiaSite()));
				data.add(convertUpperCase(dto.getOralSubmucousFibrosis()));
				data.add(convertUpperCase(dto.getOsmfType()));
				data.add(convertUpperCase(dto.getNonHealingUlcer()));
				data.add(convertUpperCase(dto.getNonHealingUlcerSite()));
				data.add(convertUpperCase(dto.getNonHealingUlcerSize()));
				data.add(convertUpperCase(dto.getSmokersPalate()));
				data.add(convertUpperCase(dto.getLichenPlanus()));
				data.add(convertUpperCase(dto.getLichenPlanusSite()));
				data.add(convertUpperCase(dto.getOralLichenoidReaction()));
				data.add(convertUpperCase(dto.getOralLichenoidReactionSite()));
				data.add(convertUpperCase(dto.getOtherLesionsFlag()));
				data.add(convertUpperCase(dto.getOtherLesionsText()));
				data.add(convertUpperCase(dto.getLesionInvasiveSite()));
				data.add(convertUpperCase(dto.getLesionInvasiveSize()));
				data.add(convertUpperCase(dto.getLesionInvasiveAppearance()));
				data.add(convertUpperCase(dto.getOralReferral()));

				/*
				 * "TOBACCO CESSATION COUNSELING",
				 * "ORAL MALIGNANT POTENTIAL DISEASE","CANCEROUS OR INVASIVE LESION", "OTHERS",
				 */
				String oC1 = "";
				String oC2 = "";
				String oC3 = "";
				String oC4 = "";
				if (dto.getOralReferral() != null && dto.getOralReferral().equals("Yes")
						&& dto.getOralReferredFor() != null) {
					// Normalize list to upper case, trimmed
					Set<String> referredSet = Arrays.stream(dto.getOralReferredFor().split(",")).map(String::trim)
							.map(String::toUpperCase).collect(Collectors.toSet());
					oC1 = referredSet.contains("TOBACCO CESSATION COUNSELING") ? "YES" : "NO";
					oC2 = referredSet.contains("ORAL MALIGNANT POTENTIAL DISEASE") ? "YES" : "NO";
					oC3 = referredSet.contains("CANCEROUS OR INVASIVE LESION") ? "YES" : "NO";
					oC4 = referredSet.contains("OTHERS") ? "YES" : "NO";

				} else if (dto.getOralReferral() != null && dto.getOralReferral().equals("Yes")) {
					oC1 = "NO";
					oC2 = "NO";
					oC3 = "NO";
					oC4 = "NO";
				}
				data.add(convertUpperCase(oC1));
				data.add(convertUpperCase(oC2));
				data.add(convertUpperCase(oC3));
				data.add(convertUpperCase(oC4));
				data.add((dto.getOralCompleted() != null && dto.getOralCompleted() == 1) ? "COMPLETED" : "PENDING");
				// --- Symptoms Based Cancer ---
				data.add(convertUpperCaseWithForceText(dto.getSympCreatedDate()));
				data.add(convertUpperCase(dto.getSympcBy()));
				data.add(convertUpperCase(dto.getSymplBy()));
				data.add(convertUpperCase(dto.getSympBasedReferral()));
				data.add(convertUpperCase(dto.getSympSymptoms()));
				data.add(convertUpperCaseWithForceText(dto.getSympDuration()));
				data.add(convertUpperCase(dto.getSympKnownCancerPatient()));
				data.add(convertUpperCase(dto.getSympCancerSite()));
				data.add(convertUpperCase(dto.getSympTreatment()));
				data.add(convertUpperCase(dto.getSympReferral()));
				data.add(convertUpperCase(dto.getSympInvestigations()));
				data.add(convertUpperCase(dto.getSympReports()));
				data.add(convertUpperCase(dto.getSympReportFindings()));
				data.add(convertUpperCase(dto.getSympFinalDiagnosis()));
				data.add((dto.getSymCompleted() != null && dto.getSymCompleted() == 1) ? "COMPLETED" : "PENDING");

				csvWriter.writeNext(data.toArray(new String[0]), true);
			}
			csvWriter.flush();
			return baos.toByteArray();
		}
	}

	public byte[] generateCampsReportCsv(DownloadRequestDTO downloadRequestDTO) throws Exception {
		String campIdsStr = downloadRequestDTO.getIds().stream().map(String::valueOf).collect(Collectors.joining(","));
		List<CampsReportDTO> reportData = campRepository.report(downloadRequestDTO.getStartDate(),
				downloadRequestDTO.getEndDate(), campIdsStr, downloadRequestDTO.getHospitalId());
		if (reportData.isEmpty()) {
			return new byte[0];
		}

		try (StringWriter sw = new StringWriter(); CSVWriter csvWriter = new CSVWriter(sw)) {

			// Header
			String[] header = { "OUTREACHCLINICID", "OUTREACHCLINICNAME", "STARTDATE", "ENDDATE", "PANJAYATHNAME",
					"TALUKNAME", "DISTRICTNAME", "STATENAME", "NOCAMPCOORDINATORS", "NODOCTORS", "NONURSES",
					"NOPROGRAMCOORDINATORS", "NOSOCIALWORKERS", "DOCTOR", "PROGRAM_COORDINATOR", "CAMP_COORDINATOR",
					"SOCIAL_WORKER", "NURSE", "CREATEDBY", "CREATEDDATE" };

			csvWriter.writeNext(header);

			// Rows
			for (CampsReportDTO row : reportData) {
				String[] data = { convertUpperCaseWithForceText(row.getOutreachClinicID()),
						convertUpperCase(row.getOutreachClinicName()), convertUpperCase(row.getStartDate()),
						convertUpperCase(row.getEndDate()), convertUpperCase(row.getPanjayathName()),
						convertUpperCase(row.getTalukName()), convertUpperCase(row.getDistrictName()),
						convertUpperCase(row.getStateName()),
						convertUpperCase(
								row.getNoCampcordinators() != null ? row.getNoCampcordinators().toString() : ""),
						convertUpperCase(row.getNoDoctors() != null ? row.getNoDoctors().toString() : ""),
						convertUpperCase(row.getNoNurses() != null ? row.getNoNurses().toString() : ""),
						convertUpperCase(
								row.getNoProgramcordinators() != null ? row.getNoProgramcordinators().toString() : ""),
						convertUpperCase(row.getNoSocialworkers() != null ? row.getNoSocialworkers().toString() : ""),
						convertUpperCase(row.getDoctor()), convertUpperCase(row.getProgramCoordinator()),
						convertUpperCase(row.getCampCoordinator()), convertUpperCase(row.getSocialWorker()),
						convertUpperCase(row.getNurse()), convertUpperCase(row.getCreatedBy()),
						convertUpperCaseWithForceText(row.getCreatedDate()) };

				csvWriter.writeNext(data);
			}

			csvWriter.flush(); // flush all data to StringWriter
			return sw.toString().getBytes(StandardCharsets.UTF_8);
		}
	}

	public byte[] generatePatientRegReportCsv(DownloadRequestDTO downloadRequestDTO) throws Exception {
		String campIdsStr = downloadRequestDTO.getIds().stream().map(String::valueOf).collect(Collectors.joining(","));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		List<PatientRegReportDTO> reportData = candidateRepository.report(downloadRequestDTO.getStartDate(),
				downloadRequestDTO.getEndDate(), campIdsStr, downloadRequestDTO.getHospitalId());

		if (reportData == null || reportData.isEmpty()) {
			return new byte[0];
		}
		reportData.sort(Comparator.comparing(dto -> LocalDateTime.parse(dto.getCreatedDate(), formatter),
				Comparator.nullsLast(Comparator.reverseOrder())));

		try (StringWriter sw = new StringWriter();
			 ICSVWriter csvWriter = new CSVWriterBuilder(sw).withSeparator(CSVWriter.DEFAULT_SEPARATOR)
					 .withQuoteChar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
					 .withEscapeChar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					 .build()) {

			// 🔹 Header (manually defined)
			String[] header = { "OUTREACH CLINIC ID", "OUTREACH CLINIC NAME", "CREATED DATE", "CREATED BY",
					"REGISTRATION ID", "CANDIDATE NAME", "MOBILE NO", "GENDER", "AGE", "ADDRESS", "STREET ID",
					"ELIGIBILITY - MARITAL STATUS", "FATHER NAME", "SPOUSE NAME", "ALTERNATE MOBILE NO",
					"MONTHLY INCOME", "HOUSE TYPE", "EDUCATION", "OCCUPATION", "AADHAR", "VOTER ID", "RATION CARD",
					"TOBACCO/ALCOHOL HABITS", "HABITS", "HABIT TYPE", "HABIT DURATION", "HABIT FREQUENCY/DAY", "QUIT",
					"HOW LONG",

					"MEDICAL HISTORY", "BLOOD PRESSURE", "PULSE RATE", "WEIGHT(KGS)", "HISTORY OF SURGERY",
					"HEIGHT(CMS)", "SPO2", "ALLERGY", "OTHER COMPLAINTS",

					"AGE AT MENARCHE", "LAST MENSTRUATION", "ABNORMAL BLEEDING VAGINUM", "AGE AT MARRIAGE",
					"TOTAL PREGNANCIES",

					"AGE AT FIRST CHILD", "AGE AT LAST CHILD", "CURRENTLY PREGNANT", "METHOD OF CONTRACEPTION USED",
					"BREAST FED", "UNDERGONE CERVICAL/BREAST SCREENING", "FAMILY NAME", "FAMILY RELATION",
					"FAMILY GENDER", "FAMILY AGE", "FAMILY TOBACCOORALCOHOLHABITS", "FAMILY MARITALSTATUS",
					"FAMILY MEMBER ID", "FAMILY MEDICAL RELATION", "FAMILY MEDICAL CANCER SITE",
					"FAMILY MEDICAL AGE AT DIAGNOSIS", "FAMILY MEDICAL OUTCOME" };

			csvWriter.writeNext(header, true); // force quotes for all cells

			for (PatientRegReportDTO row : reportData) {

				String[] data = {
						// --- Outreach Clinic Details ---
						convertUpperCaseWithForceText(row.getOutreachClinicID()),
						convertUpperCase(row.getOutreachClinicName()),
						convertUpperCaseWithForceText(row.getCreatedDate()), convertUpperCase(row.getCreatedBy()),

						// --- Candidate Basic Info ---
						convertUpperCaseWithForceText(row.getRegistraionId()), convertUpperCase(row.getName()),
						convertUpperCase(row.getMobileNo()), convertUpperCase(row.getGender()),
						convertUpperCase(row.getAge() != null ? row.getAge().toString() : ""),
						convertUpperCase(row.getAddress()),
						convertUpperCaseWithForceText(
								row.getStreetId() != null ? String.format("%03d", Integer.parseInt(row.getStreetId()))
										: "000"),

						// --- Eligibility & Family ---
						convertUpperCase(row.getEligibility_MaritalStatus()),
						// convertUpperCase(row.getEligibility_HysterectomyDone()),
						convertUpperCase(row.getFatherName()), convertUpperCase(row.getSpouseName()),
						convertUpperCase(row.getAlternateMobileNo()), convertUpperCase(row.getMonthlyIncome()),
						convertUpperCase(row.getHouseType()), convertUpperCase(row.getEducation()),
						convertUpperCase(row.getOccupation()), convertUpperCaseWithForceText(row.getAadhar()),
						convertUpperCaseWithForceText(row.getVoterId()), convertUpperCase(row.getRationCard()),

						// --- Habits ---
						convertUpperCase(row.getSocialHabits() == null ? ""
								: ("true".equals(row.getSocialHabits()) ? "Yes" : "No")),
						convertUpperCase(row.getHabits()), convertUpperCase(row.getType()),
						convertUpperCase(row.getDuration()), convertUpperCase(row.getFrequency()),
						convertUpperCase(row.getQuit() == null ? "" : ("true".equals(row.getQuit()) ? "Yes" : "No")),
						convertUpperCase(row.getHowLong()),

						// --- Medical & Physical ---
						convertUpperCase(row.getMedicalhistory()), convertUpperCase(row.getBloodPressure()),
						convertUpperCase(row.getPulseRate()), convertUpperCase(row.getWeight()),
						convertUpperCase(row.getHistoryOfSurgery()), convertUpperCase(row.getHeight()),
						convertUpperCase(row.getSpo2()), convertUpperCase(row.getAllergy()),
						convertUpperCase(row.getOtherComplaints()),

						// --- Female & Reproductive Health ---

						convertUpperCase(row.getAgeAtMenarche()), convertUpperCase(row.getLastMentrution()),
						convertUpperCase(row.getAbnormalBleedingVaginum()), convertUpperCase(row.getAgeAtMarriage()),
						convertUpperCase(row.getTotalPregnancies()), convertUpperCase(row.getAgeAtFirstChild()),
						convertUpperCase(row.getAgeAtLastChild()),
						convertUpperCase(row.getCurrentlyPregant() == null ? ""
								: (("false".equals(row.getCurrentlyPregant())) ? "No" : "Yes")),
						convertUpperCase(row.getMethodOfContraceptionUsed()), convertUpperCase(row.getBreastFed()),
						convertUpperCase(row.getUndergoneCervicalBreastScrening()),

						// --- Family Details ---
						convertUpperCase(row.getFamily_Name()), convertUpperCase(row.getFamily_Relation()),
						convertUpperCase(row.getFamily_Gender()), convertUpperCase(row.getFamily_Age()),
						convertUpperCase(row.getFamily_TobaccoOrAlcoholHabits()),
						convertUpperCase(row.getFamily_MaritalStatus()),
						convertUpperCaseWithForceText(row.getFamily_registraionId()),

						// --- Family Medical History ---
						convertUpperCase(row.getFamilyMedical_Relation()),
						convertUpperCase(row.getFamilyMedical_CancerSite()),
						convertUpperCase(row.getFamilyMedical_AgeAtDiagnosis()),
						convertUpperCase(row.getFamilyMedical_Outcome()) };

				csvWriter.writeNext(data, true); // force quotes for all cells

			}

			csvWriter.flush();
			return sw.toString().getBytes(StandardCharsets.UTF_8);
		}
	}

	// Helper method to safely convert any object to String
	private String safe(Object val) {
		return val == null ? "" : String.valueOf(val);
	}

	// Helper: Force large numeric IDs to be treated as text in Excel
	private String forceText(String val) {
		// return val == null ? " " : " " + val;

		return val == null ? "" : "\t" + val;
	}

	private String convertUpperCaseWithForceText(String val) {
		return forceText((val != null && !val.isEmpty()) ? val.toUpperCase() : val);
	}

	private String convertUpperCase(String val) {
		return (val != null && !val.isEmpty()) ? val.toUpperCase() : val;
	}
}