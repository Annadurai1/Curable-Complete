package com.curable.service.dto;

public interface PatientRegReportDTO {
	 String getOutreachClinicID();
	    String getOutreachClinicName();
	    String getCreatedDate();
	    String getCreatedBy();
	    String getRegistraionId();
	    String getName();
	    String getMobileNo();
	    String getGender();
	    Integer getAge();
	    String getAddress();
	    String getStreetId();
	    String getFatherName();
	    String getSpouseName();
	    String getAlternateMobileNo();
	    String getMonthlyIncome();
	    String getHouseType();
	    String getEducation();
	    String getOccupation();
	    String getAadhar();
	    String getVoterId();
	    String getRationCard();

	    // Medical details
	    String getMedicalhistory();
	    String getBloodPressure();
	    String getPulseRate();
	    String getWeight();
	    String getHistoryOfSurgery();
	    String getHeight();
	    String getSpo2();
	    String getAllergy();
	    String getOtherComplaints();

	    // Reproductive details
	    String getAgeAtMenarche();
	    String getAgeAtFirstChild();
	    String getAgeAtLastChild();
	    String getAgeAtMarriage();
	    String getLastMentrution();
	    String getAbnormalBleedingVaginum();
	    String getTotalPregnancies();
	    String getCurrentlyPregant();
	    String getMethodOfContraceptionUsed();
	    String getUndergoneCervicalBreastScrening();
	    String getBreastFed();

	    // Habits
	    String getDuration();
	    String getFrequency();
	    String getHabits();
	    String getHowLong();
	    String getQuit();
	    String getType();
	    String getSocialHabits();

	    // Eligibility
	    String getEligibility_MaritalStatus();
	    String getEligibility_HysterectomyDone();

	    // Family
	    String getFamily_Name();
	    String getFamily_Relation();
	    String getFamily_Gender();
	    String getFamily_Age();
	    String getFamily_TobaccoOrAlcoholHabits();
	    String getFamily_MaritalStatus();
	    String getFamily_registraionId();
	    
	    
	    
	    String getFamilyMedical_Relation();
	    String getFamilyMedical_CancerSite();
	    String getFamilyMedical_AgeAtDiagnosis();
	    String getFamilyMedical_Outcome();
	    Integer getGroupIndex();
}
