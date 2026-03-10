package com.curable.service.dto;

import java.io.Serializable;

import com.curable.domain.enums.Gender;
import com.curable.service.dto.custom.CommonTestParamsDTO;

public class DiseaseTestMasterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private Long diseaseTestId;
	private Boolean defalutTest;
	private Long hospitalId;
	private String stage;
	private Gender gender;

	private CommonTestParamsDTO testMetrics;
	private CommonTestParamsDTO eligibilityMetrics;

	private CommonTestParamsDTO medicalMetrics;

	private CommonTestParamsDTO familyMetrics;

	private CommonTestParamsDTO familyMedicalMetrics;

	private Long diseaseMasterId;

	private String condition;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Long getDiseaseMasterId() {
		return diseaseMasterId;
	}

	public void setDiseaseMasterId(Long diseaseMasterId) {
		this.diseaseMasterId = diseaseMasterId;
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

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Gender getGender() {
		return gender;
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

	public DiseaseTestMasterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiseaseTestMasterDTO(Long id, Boolean defalutTest, String condition) {
		super();
		this.id = id;
		this.defalutTest = defalutTest == null ? Boolean.FALSE : defalutTest;
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "DiseaseTestMasterDTO [id=" + id + ", name=" + name + ", description=" + description + ", diseaseTestId="
				+ diseaseTestId + ", defalutTest=" + defalutTest + ", hospitalId=" + hospitalId + ", stage=" + stage
				+ ", gender=" + gender + ", testMetrics=" + testMetrics + ", eligibilityMetrics=" + eligibilityMetrics
				+ ", medicalMetrics=" + medicalMetrics + ", familyMetrics=" + familyMetrics + ", familyMedicalMetrics="
				+ familyMedicalMetrics + ", diseaseMasterId=" + diseaseMasterId + "]";
	}

}
