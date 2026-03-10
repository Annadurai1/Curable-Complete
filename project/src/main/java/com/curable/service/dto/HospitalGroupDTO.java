package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.Instant;

public class HospitalGroupDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String groupName;
	private String description;
	private String licenseNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String toString() {
		return "HospitalGroupDTO [" +
		"id=" + id +
		"groupName=" + groupName +
		"description=" + description +
		"licenseNo=" + licenseNo +
		"]";
	}
}
