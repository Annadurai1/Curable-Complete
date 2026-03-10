package com.curable.service.dto;

public interface ClinicalReportDTO {
	String getRegistrationId();

	String getCandidateName();

	// Breast
	String getBreastCreatedDate();

	String getBreastcBy();

	String getBreastlBy();

	String getBreastClinicalExam();

	String getBreastUhid();

	String getBreastScrOpdNumber();

	String getBreastMammogram();

	String getBreastMammogramRight();

	String getBreastMammogramLeft();

	String getBreastMammogramReportDate();

	String getBreastUltrasound();

	String getBreastUltrasoundRight();

	String getBreastUltrasoundLeft();

	String getBreastUltrasoundReportDate();

	String getBreastCytology();

	String getBreastCytologyReportDate();

	String getBreastCytologyImage();

	String getBreastOtherFindings(); // cytology

	String getBreastBiopsy();

	String getBreastBiopsyReportDate();

	String getBreastBiopsyReport();

	String getBreastAdditionalComments();

	String getBreastFinalDiagnosis();

	String getBreastTreatment();

	String getBreastFollowupAdvice();

	// Cervical
	String getCervicalCreatedDate();

	String getCervicalcBy();

	String getCervicallBy();

	String getCervicalClinicalExam();

	String getCervicalUhid();

	String getCervicalOpdNumber();

	String getCervicalColposcopy();

	String getCervicalImage();

	String getCervicalColposcopyReport();

	String getCervicalColposcopyReportDate();

	String getCervicalColpoBiopsy();

	String getCervicalColpoBiopsyReportDate();

	String getCervicalColpoBiopsyImage();

	String getCervicalAdditionalComments();

	String getCervicalTreatmentPlan();

	String getCervicalTreatment();

	String getCervicalFollowupAdvice();

	// Oral
	String getOralCreatedDate();

	String getOralcBy();

	String getOrallBy();

	String getOralClinicalEvaluation();

	String getOralUhid();

	String getOralOpdNumber();

	String getOralTobaccoCounselling();

	String getOralTobaccoOutcome();

	String getOralTobaccoReportDate();

	String getOralHeadNeckOpd();

	String getOralHeadNeckReportDate();

	String getOralHeadNeckOutcome();

	String getOralCytology();

	String getOralCytologyReport();

	String getOralCytologyReportDate();

	String getOralBiopsy();

	String getOralBiopsyReport();

	String getOralBiopsyReportDate();

	String getOralAdditionalComments();

	String getOralFinalDiagnosis();

	String getOralTreatment();

	String getOralFollowupAdvice();
}
