package com.curable.service.dto.custom;

public class CandidateSearchCustomDTO {
	private Long candidateId;
	private int type;
	private Long diseaseTypeId;

	public Long getDiseaseTypeId() {
		return diseaseTypeId;
	}

	public void setDiseaseTypeId(Long diseaseTypeId) {
		this.diseaseTypeId = diseaseTypeId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CandidateSearchCustomDTO [candidateId=" + candidateId + ", type=" + type + ", diseaseTypeId="
				+ diseaseTypeId + "]";
	}

}
