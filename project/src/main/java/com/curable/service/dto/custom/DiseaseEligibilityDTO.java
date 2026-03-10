package com.curable.service.dto.custom;

public class DiseaseEligibilityDTO {
	public Long candidateId;
	public String stage;
	public String name;
	public Long diseaseTestId;
	public Long candidateTestId;

	public Long getCandidateTestId() {
		return candidateTestId;
	}

	public void setCandidateTestId(Long candidateTestId) {
		this.candidateTestId = candidateTestId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDiseaseTestId() {
		return diseaseTestId;
	}

	public void setDiseaseTestId(Long diseaseTestId) {
		this.diseaseTestId = diseaseTestId;
	}

	public DiseaseEligibilityDTO(Long candidateId, String stage, String name, Long diseaseTestId,
			Long candidateTestId) {
		super();
		this.candidateId = candidateId;
		this.stage = stage;
		this.name = name;
		this.diseaseTestId = diseaseTestId;
		this.candidateTestId = candidateTestId;
	}

	public DiseaseEligibilityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DiseaseEligibilityDTO [candidateId=" + candidateId + ", stage=" + stage + ", name=" + name
				+ ", diseaseTestId=" + diseaseTestId + "]";
	}

}
