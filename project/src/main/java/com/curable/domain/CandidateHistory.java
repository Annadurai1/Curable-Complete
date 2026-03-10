package com.curable.domain;

import java.time.Instant;
import java.util.List;

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
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;

import com.curable.service.dto.custom.CommonTestParamsDTO;

import io.hypersistence.utils.hibernate.type.json.JsonType;

@Audited
@Entity
@Table(name = "candidatehistory")
@TypeDef(name = "json", typeClass = JsonType.class)
public class CandidateHistory extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "historyDate")
	private Instant historyDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidateId")
	private Candidate candidate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diseaseTestMasterId")
	private DiseaseTestMaster diseaseTestMaster;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private CommonTestParamsDTO eligibilityMetrics;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private CommonTestParamsDTO medicalMetrics;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private List<CommonTestParamsDTO> familyMetrics;
	@Type(type = "json")
	@Column(columnDefinition = "json")
	private List<CommonTestParamsDTO> familyMedicalMetrics;

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

	@Override
	public String toString() {
		return "CandidateHistory [id=" + id + ", historyDate=" + historyDate + ", eligibilityMetrics="
				+ eligibilityMetrics + ", medicalMetrics=" + medicalMetrics + ", familyMetrics=" + familyMetrics
				+ ", familyMedicalMetrics=" + familyMedicalMetrics + "]";
	}

}
