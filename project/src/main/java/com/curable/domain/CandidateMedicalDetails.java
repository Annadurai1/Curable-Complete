package com.curable.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "candidatemedicaldetails")
public class CandidateMedicalDetails extends AbstractAuditingEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidateId")
	private Candidate candidate;
	
	@Column(name = "medicalhistory")
	private String medicalhistory;
	
	@Column(name = "bloodPressure")
	private String bloodPressure;
	
	@Column(name = "pulseRate")
	private String pulseRate;
	
	@Column(name = "weight")
	private Double weight;
	
	@Column(name = "historyOfSurgery")
	private Boolean historyOfSurgery;
	
	
	@Column(name = "height")
	private Double height;
	
	@Column(name = "spo2")
	private Double spo2;
	
	
	@Column(name = "allergy")
	private String allergy;
	
	@Column(name = "otherComplaints")
	private String otherComplaints;
	
	@Column(name = "ageAtMenarche")
	private Integer ageAtMenarche;
	
	@Column(name = "lastMentrution")
	private String whenWasLastMentrution;
	
	@Column(name = "abnormalBleedingVaginum")
	private String abnormalBleedingVaginum;
	

	@Column(name = "ageAtMarriage")
	private Integer ageAtMarriage;
	
	@Column(name = "totalPregnancies")
	private Integer totalPregnancies;
	
	@Column(name = "ageAtFirstChild")
	private Integer ageAtFirstChild;
	
	@Column(name = "ageAtLastChild")
	private Integer ageAtLastChild;
	
	
	@Column(name = "currentlyPregant")
	private Boolean currentlyPregant;
	
	@Column(name = "methodOfContraceptionUsed")
	private String methodOfContraceptionUsed;
	
	@Column(name = "methodOfContraception")
	private String noOfBreastFedMonths;
	
	@Column(name = "cervicalBreastScrening")
	private Boolean cervicalBreastScrening;
	
	@Column(name = "undergoneCervicalBreastScrening")
	private String undergoneCervicalBreastScrening;


	public String getUndergoneCervicalBreastScrening() {
		return undergoneCervicalBreastScrening;
	}

	public void setUndergoneCervicalBreastScrening(String undergoneCervicalBreastScrening) {
		this.undergoneCervicalBreastScrening = undergoneCervicalBreastScrening;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
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
		return "CandidateMedicalDetails [id=" + id + ", medicalhistory=" + medicalhistory + ", bloodPressure="
				+ bloodPressure + ", pulseRate=" + pulseRate + ", weight=" + weight + ", historyOfSurgery="
				+ historyOfSurgery + ", height=" + height + ", spo2=" + spo2 + ", allergy=" + allergy
				+ ", otherComplaints=" + otherComplaints + ", ageAtMenarche=" + ageAtMenarche
				+ ", whenWasLastMentrution=" + whenWasLastMentrution + ", abnormalBleedingVaginum="
				+ abnormalBleedingVaginum + ", ageAtMarriage=" + ageAtMarriage + ", totalPregnancies="
				+ totalPregnancies + ", ageAtFirstChild=" + ageAtFirstChild + ", ageAtLastChild=" + ageAtLastChild
				+ ", currentlyPregant=" + currentlyPregant + ", methodOfContraceptionUsed=" + methodOfContraceptionUsed
				+ ", noOfBreastFedMonths=" + noOfBreastFedMonths + ", cervicalBreastScrening=" + cervicalBreastScrening
				+ ", undergoneCervicalBreastScrening=" + undergoneCervicalBreastScrening + "]";
	}

	
	
	

}
