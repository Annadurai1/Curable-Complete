package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.Instant;

public class HospitalDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long hospitalGroupId;
	private String name;
	private String description;
	private String address;
	private String licenseNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHospitalGroupId() {
		return hospitalGroupId;
	}

	public void setHospitalGroupId(Long hospitalGroupId) {
		this.hospitalGroupId = hospitalGroupId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String toString() {
		return "HospitalDTO [" +
		"id=" + id +
		"hospitalGroupId=" + hospitalGroupId +
		"name=" + name +
		"description=" + description +
		"address=" + address +
		"licenseNo=" + licenseNo +
		"]";
	}
}
