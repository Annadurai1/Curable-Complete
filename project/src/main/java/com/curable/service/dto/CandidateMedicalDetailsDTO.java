package com.curable.service.dto;

public class CandidateMedicalDetailsDTO {
	private Long id;

	private Long candidateId;

	private String medicalhistory;

	private String bloodPressure;

	private String pulseRate;

	private Double weight;

	private Boolean historyOfSurgery;

	private Double height;

	private Double spo2;

	private String allergy;

	private String otherComplaints;

	private Integer ageAtMenarche;

	private String whenWasLastMentrution;

	private String abnormalBleedingVaginum;

	private Integer ageAtMarriage;

	private Integer totalPregnancies;

	private Integer ageAtFirstChild;

	private Integer ageAtLastChild;

	private Boolean currentlyPregant;

	private String methodOfContraceptionUsed;

	private String noOfBreastFedMonths;

	private Boolean cervicalBreastScrening;
	
	private String undergoneCervicalBreastScrening;
	
	
	

	public String getUndergoneCervicalBreastScrening() {
		return undergoneCervicalBreastScrening;
	}

	public void setUndergoneCervicalBreastScrening(String undergoneCervicalBreastScrening) {
		this.undergoneCervicalBreastScrening = undergoneCervicalBreastScrening;
	}

	public CandidateMedicalDetailsDTO(Long id, Long candidateId) {
		super();
		this.id = id;
		this.candidateId = candidateId;
	}

	public CandidateMedicalDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getMedicalhistory() {
		return medicalhistory;
	}

	public void setMedicalhistory(String medicalhistory) {
		this.medicalhistory = medicalhistory;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(String pulseRate) {
		this.pulseRate = pulseRate;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Boolean getHistoryOfSurgery() {
		return historyOfSurgery;
	}

	public void setHistoryOfSurgery(Boolean historyOfSurgery) {
		this.historyOfSurgery = historyOfSurgery;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getSpo2() {
		return spo2;
	}

	public void setSpo2(Double spo2) {
		this.spo2 = spo2;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getOtherComplaints() {
		return otherComplaints;
	}

	public void setOtherComplaints(String otherComplaints) {
		this.otherComplaints = otherComplaints;
	}

	public Integer getAgeAtMenarche() {
		return ageAtMenarche;
	}

	public void setAgeAtMenarche(Integer ageAtMenarche) {
		this.ageAtMenarche = ageAtMenarche;
	}

	public String getWhenWasLastMentrution() {
		return whenWasLastMentrution;
	}

	public void setWhenWasLastMentrution(String whenWasLastMentrution) {
		this.whenWasLastMentrution = whenWasLastMentrution;
	}

	public String getAbnormalBleedingVaginum() {
		return abnormalBleedingVaginum;
	}

	public void setAbnormalBleedingVaginum(String abnormalBleedingVaginum) {
		this.abnormalBleedingVaginum = abnormalBleedingVaginum;
	}

	public Integer getAgeAtMarriage() {
		return ageAtMarriage;
	}

	public void setAgeAtMarriage(Integer ageAtMarriage) {
		this.ageAtMarriage = ageAtMarriage;
	}

	public Integer getTotalPregnancies() {
		return totalPregnancies;
	}

	public void setTotalPregnancies(Integer totalPregnancies) {
		this.totalPregnancies = totalPregnancies;
	}

	public Integer getAgeAtFirstChild() {
		return ageAtFirstChild;
	}

	public void setAgeAtFirstChild(Integer ageAtFirstChild) {
		this.ageAtFirstChild = ageAtFirstChild;
	}

	public Integer getAgeAtLastChild() {
		return ageAtLastChild;
	}

	public void setAgeAtLastChild(Integer ageAtLastChild) {
		this.ageAtLastChild = ageAtLastChild;
	}

	public Boolean getCurrentlyPregant() {
		return currentlyPregant;
	}

	public void setCurrentlyPregant(Boolean currentlyPregant) {
		this.currentlyPregant = currentlyPregant;
	}

	public String getMethodOfContraceptionUsed() {
		return methodOfContraceptionUsed;
	}

	public void setMethodOfContraceptionUsed(String methodOfContraceptionUsed) {
		this.methodOfContraceptionUsed = methodOfContraceptionUsed;
	}

	public String getNoOfBreastFedMonths() {
		return noOfBreastFedMonths;
	}

	public void setNoOfBreastFedMonths(String noOfBreastFedMonths) {
		this.noOfBreastFedMonths = noOfBreastFedMonths;
	}

	public Boolean getCervicalBreastScrening() {
		return cervicalBreastScrening;
	}

	public void setCervicalBreastScrening(Boolean cervicalBreastScrening) {
		this.cervicalBreastScrening = cervicalBreastScrening;
	}

	@Override
	public String toString() {
		return "CandidateMedicalDetailsDTO [id=" + id + ", candidateId=" + candidateId + ", medicalhistory="
				+ medicalhistory + ", bloodPressure=" + bloodPressure + ", pulseRate=" + pulseRate + ", weight="
				+ weight + ", historyOfSurgery=" + historyOfSurgery + ", height=" + height + ", spo2=" + spo2
				+ ", allergy=" + allergy + ", otherComplaints=" + otherComplaints + ", ageAtMenarche=" + ageAtMenarche
				+ ", whenWasLastMentrution=" + whenWasLastMentrution + ", abnormalBleedingVaginum="
				+ abnormalBleedingVaginum + ", ageAtMarriage=" + ageAtMarriage + ", totalPregnancies="
				+ totalPregnancies + ", ageAtFirstChild=" + ageAtFirstChild + ", ageAtLastChild=" + ageAtLastChild
				+ ", currentlyPregant=" + currentlyPregant + ", methodOfContraceptionUsed=" + methodOfContraceptionUsed
				+ ", noOfBreastFedMonths=" + noOfBreastFedMonths + ", cervicalBreastScrening=" + cervicalBreastScrening
				+ "]";
	}

	
	
	
}
