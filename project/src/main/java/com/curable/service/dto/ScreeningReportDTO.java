package com.curable.service.dto;

public interface ScreeningReportDTO {

	// Candidate info
	String getRegistraionId();

	String getCandidateName();

	// Breast Screening
	String getBreastCreatedDate();

	String getBreastcBy();

	String getBreastlBy();

	String getBreastExam();

	String getBreastSymptoms();

	String getRightBreastOnPalpation();

	String getLeftBreastOnPalpation();

	String getBreastNippleDischarge();

	String getBreastReferral();

	String getBreastReferredFor();

	Integer getBreastCompleted();

	// Cervical Screening
	String getCervicalCreatedDate();

	String getCervicalcBy();

	String getCervicallBy();

	String getCervicalScreening();

	String getCervicalReason();

	String getCervicalSymptoms();

	String getCervicalAbnormalBleeding();

	String getCervicalVisualInspection();

	String getCervicalFindingsOnVisualInspection();

	String getCervicalReferral();

	String getCervicalReferredFor();

	// ✅ New fields added
	String getCervicalHRHPVDNA();

	String getCervicalHSampleCollectionDate();

	String getCervicalHSampleType();

	String getCervicalHSampleReportDate();

	String getCervicalHReport();

	String getGenotype();

	String getCervicalCytology();

	String getCervicalCSampleCollectionDate();

	String getCervicalCReportDate();

	String getCervicalCReport();

	String getCervicalVisualTest();

	String getCervicalVReportDate();

	String getCervicalVReport();

	Integer getCervicalCompleted();

	// --- Oral Screening Common Info ---
	String getOralCreatedDate();

	String getOralcBy();

	String getOrallBy();

	// --- Main Screening Fields ---
	String getOralExam();

	String getOralSymptoms();

	String getOralSelectSymptoms();

	String getDentalHygiene();

	String getClinicalFindings();

	String getOralSelectSubtype();

	// --- Leukoplakia ---
	String getOralMalignantLeukoplakia();

	String getLeukoplakiaType();

	String getLesionSite();

	String getLesionSize();

	String getLesionSubtype();

	// --- Multiple Lesions ---
	String getMultipleLesionSite();

	String getMultipleLesionSize();

	String getMultipleLesionSubtype();

	// --- Erythroplakia ---
	String getOralMalignantErythroplakia();

	String getErythroplakiaSite();

	// --- Oral Submucous Fibrosis ---
	String getOralSubmucousFibrosis();

	String getOsmfType();

	// --- Non-Healing Ulcer ---
	String getNonHealingUlcer();

	String getNonHealingUlcerSite();

	String getNonHealingUlcerSize();

	// --- Smoker’s Palate ---
	String getSmokersPalate();

	// --- Lichen Planus ---
	String getLichenPlanus();

	String getLichenPlanusSite();

	// --- Oral Lichenoid Reaction ---
	String getOralLichenoidReaction();

	String getOralLichenoidReactionSite();

	// --- Other Lesions ---
	String getOtherLesionsFlag();

	String getOtherLesionsText();

	// --- Lesion S/O Invasive Cancer ---
	String getLesionInvasiveSite();

	String getLesionInvasiveSize();

	String getLesionInvasiveAppearance();

	Integer getOralCompleted();

	// --- Referral ---
	String getOralReferral();

	String getOralReferredFor();

	String getSympCreatedDate();

	String getSympcBy();

	String getSymplBy();

	String getSympBasedReferral();

	String getSympSymptoms();

	String getSympDuration();

	String getSympKnownCancerPatient();

	String getSympCancerSite();

	String getSympTreatment();

	String getSympReferral();

	String getSympInvestigations();

	String getSympReports();

	String getSympReportFindings();

	String getSympFinalDiagnosis();

	Integer getSymCompleted();
}
