package com.curable.service.dto;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.format.annotation.DateTimeFormat;

import com.curable.service.dto.custom.CommonTestParamsDTO;

public class CandidateTestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long candidateId;
	private Long diseaseTestMasterId;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Instant testDate;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Instant resultDate;
	private String result;
	private Boolean referral;
	private CommonTestParamsDTO testResult;
	private int completed;
	private Instant dCreatedDate;
	private String dCreatedBy;

	public Instant getdCreatedDate() {
		return dCreatedDate;
	}

	public void setdCreatedDate(Instant dCreatedDate) {
		this.dCreatedDate = dCreatedDate;
	}

	public String getdCreatedBy() {
		return dCreatedBy;
	}

	public void setdCreatedBy(String dCreatedBy) {
		this.dCreatedBy = dCreatedBy;
	}

	public CandidateTestDTO(Long id, Long candidateId, Long diseaseTestMasterId, Object testResult) {
		super();
		this.id = id;
		this.candidateId = candidateId;
		this.diseaseTestMasterId = diseaseTestMasterId;
		this.testResult = (CommonTestParamsDTO) testResult;
	}

	public CandidateTestDTO() {
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

	public Long getDiseaseTestMasterId() {
		return diseaseTestMasterId;
	}

	public void setDiseaseTestMasterId(Long diseaseTestMasterId) {
		this.diseaseTestMasterId = diseaseTestMasterId;
	}

	public Instant getTestDate() {
		return testDate;
	}

	public void setTestDate(Instant testDate) {
		this.testDate = testDate;
	}

	public Instant getResultDate() {
		return resultDate;
	}

	public void setResultDate(Instant resultDate) {
		this.resultDate = resultDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Boolean getReferral() {
		return referral;
	}

	public void setReferral(Boolean referral) {
		this.referral = referral;
	}

	public CommonTestParamsDTO getTestResult() {
		return testResult;
	}

	public void setTestResult(CommonTestParamsDTO testResult) {
		this.testResult = testResult;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CandidateTestDTO [id=" + id + ", candidateId=" + candidateId + ", diseaseTestMasterId="
				+ diseaseTestMasterId + ", testDate=" + testDate + ", resultDate=" + resultDate + ", result=" + result
				+ ", referral=" + referral + ", testResult=" + testResult + ", completed=" + completed + "]";
	}

}
