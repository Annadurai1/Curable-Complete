package com.curable.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.curable.service.dto.custom.CommonTestParamsDTO;

public class CandidateHistoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Instant historyDate;
	private Long candidateId;
	private CommonTestParamsDTO eligibilityMetrics;

	private CommonTestParamsDTO medicalMetrics;

	private List<CommonTestParamsDTO> familyMetrics;

	private List<CommonTestParamsDTO> familyMedicalMetrics;

	private CommonTestParamsDTO testMetrics;

	private Long diseaseTestMasterId;

	private int type; // type 1 for update referral , 2 for finish

	private List<String> eligibleTests;

	private Long candidateTestId;
	private int completed;
	private String stage;

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public Long getCandidateTestId() {
		return candidateTestId;
	}

	public void setCandidateTestId(Long candidateTestId) {
		this.candidateTestId = candidateTestId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getHistoryDate() {
		return historyDate;
	}

	public void setHistoryDate(Instant historyDate) {
		this.historyDate = historyDate;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public CommonTestParamsDTO getEligibilityMetrics() {
		return eligibilityMetrics;
	}

	public void setEligibilityMetrics(CommonTestParamsDTO eligibilityMetrics) {
		this.eligibilityMetrics = eligibilityMetrics;
	}

	public CommonTestParamsDTO getMedicalMetrics() {
		return medicalMetrics;
	}

	public void setMedicalMetrics(CommonTestParamsDTO medicalMetrics) {
		this.medicalMetrics = medicalMetrics;
	}

	public List<CommonTestParamsDTO> getFamilyMetrics() {
		return familyMetrics;
	}

	public void setFamilyMetrics(List<CommonTestParamsDTO> familyMetrics) {
		this.familyMetrics = familyMetrics;
	}

	public List<CommonTestParamsDTO> getFamilyMedicalMetrics() {
		return familyMedicalMetrics;
	}

	public void setFamilyMedicalMetrics(List<CommonTestParamsDTO> familyMedicalMetrics) {
		this.familyMedicalMetrics = familyMedicalMetrics;
	}

	public CommonTestParamsDTO getTestMetrics() {
		return testMetrics;
	}

	public void setTestMetrics(CommonTestParamsDTO testMetrics) {
		this.testMetrics = testMetrics;
	}

	public Long getDiseaseTestMasterId() {
		return diseaseTestMasterId;
	}

	public void setDiseaseTestMasterId(Long diseaseTestMasterId) {
		this.diseaseTestMasterId = diseaseTestMasterId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<String> getEligibleTests() {
		return eligibleTests;
	}

	public void setEligibleTests(List<String> eligibleTests) {
		this.eligibleTests = eligibleTests;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CandidateHistoryDTO [id=" + id + ", historyDate=" + historyDate + ", candidateId=" + candidateId + "]";
	}

}
