package com.curable.service.dto;

import com.curable.domain.enums.Gender;

public interface CandidateCustomDTO {
	public Long getId();

	public String getName();

	public Integer getAge();

	public String getMobileNo();

	public Long getHospitalId();

	public String getRegistrationId();
	
	public Gender getGender(); 

}
