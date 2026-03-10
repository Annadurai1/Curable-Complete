package com.curable.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.curable.service.dto.custom.CommonTestParamsDTO;

@Audited
@Entity
@Table(name = "candidatetest")
public class CandidateTest extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidateId")
	private Candidate candidate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diseaseTestMasterId")
	private DiseaseTestMaster diseaseTestMaster;

	@Column(name = "testDate")
	private Instant testDate;

	@Column(name = "resultDate")
	private Instant resultDate;

	@Column(name = "result")
	private String result;

	@Column(name = "referral")
	private Boolean referral;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private CommonTestParamsDTO testResult;

	@Column(name = "completed")
	private int completed;

	@Column(name = "dCreatedDate")
	private Instant dCreatedDate;

	@Column(name = "dCreatedBy")
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

	public DiseaseTestMaster getDiseaseTestMaster() {
		return diseaseTestMaster;
	}

	public void setDiseaseTestMaster(DiseaseTestMaster diseaseTestMaster) {
		this.diseaseTestMaster = diseaseTestMaster;
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

	public CandidateTest() {
	}

	@Override
	public String toString() {
		return "CandidateTest [id=" + id + ", testDate=" + testDate + ", resultDate=" + resultDate + ", result="
				+ result + ", referral=" + referral + ", testResult=" + testResult + ", completed=" + completed + "]";
	}

	public CandidateTest(Long id, Candidate candidate, DiseaseTestMaster diseaseTestMaster) {
		super();
		this.id = id;
		this.candidate = candidate;
		this.diseaseTestMaster = diseaseTestMaster;
	}

	public CandidateTest(Long id, Candidate candidate, DiseaseTestMaster diseaseTestMaster, Instant testDate,
			Instant resultDate, String result, Boolean referral, String testResult) {
		this.id = id;
		this.candidate = candidate;
		this.diseaseTestMaster = diseaseTestMaster;
		this.testDate = testDate;
		this.resultDate = resultDate;
		this.result = result;
		this.referral = referral;
	}
}
