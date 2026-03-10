package com.curable.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.envers.RelationTargetAuditMode;

import com.curable.domain.enums.Gender;
import com.curable.service.dto.custom.CommonTestParamsDTO;

import io.hypersistence.utils.hibernate.type.json.JsonType;

@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Entity
@Table(name = "diseasetestmaster")
@TypeDef(name = "json", typeClass = JsonType.class)
public class DiseaseTestMaster extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "diseaseTestId")
	private Long diseaseTestId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diseaseMasterId")
	private DiseaseMaster diseaseMaster;

	@Column(name = "defalutTest")
	private Boolean defalutTest;

	@Column(name = "stage")
	private String stage;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private CommonTestParamsDTO testMetrics;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private CommonTestParamsDTO eligibilityMetrics;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private CommonTestParamsDTO medicalMetrics;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private CommonTestParamsDTO familyMetrics;
	@Type(type = "json")
	@Column(columnDefinition = "json")
	private CommonTestParamsDTO familyMedicalMetrics;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId")
	private Hospital hospital;

	@Column(name = "rule")
	private String condition;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public DiseaseMaster getDiseaseMaster() {
		return diseaseMaster;
	}

	public void setDiseaseMaster(DiseaseMaster diseaseMaster) {
		this.diseaseMaster = diseaseMaster;
	}

	public DiseaseTestMaster() {
	}

	public DiseaseTestMaster(Long id, String name, String description, Long diseaseTestId, Boolean genderValid,
			CommonTestParamsDTO testMetrics, String stage) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.diseaseTestId = diseaseTestId;
		this.defalutTest = genderValid;
		this.testMetrics = testMetrics;
		this.stage = stage;
	}

	public Gender getGender() {
		return gender;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDiseaseTestId() {
		return diseaseTestId;
	}

	public void setDiseaseTestId(Long diseaseTestId) {
		this.diseaseTestId = diseaseTestId;
	}

	public Boolean getDefalutTest() {
		return defalutTest;
	}

	public void setDefalutTest(Boolean defalutTest) {
		this.defalutTest = defalutTest;
	}

	public CommonTestParamsDTO getTestMetrics() {
		return testMetrics;
	}

	public void setTestMetrics(CommonTestParamsDTO testMetrics) {
		this.testMetrics = testMetrics;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
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

	public CommonTestParamsDTO getFamilyMetrics() {
		return familyMetrics;
	}

	public void setFamilyMetrics(CommonTestParamsDTO familyMetrics) {
		this.familyMetrics = familyMetrics;
	}

	public CommonTestParamsDTO getFamilyMedicalMetrics() {
		return familyMedicalMetrics;
	}

	public void setFamilyMedicalMetrics(CommonTestParamsDTO familyMedicalMetrics) {
		this.familyMedicalMetrics = familyMedicalMetrics;
	}

	@Override
	public String toString() {
		return "DiseaseTestMaster [id=" + id + ", name=" + name + ", description=" + description + ", diseaseTestId="
				+ diseaseTestId + ", defalutTest=" + defalutTest + ", stage=" + stage + "]";
	}

}
