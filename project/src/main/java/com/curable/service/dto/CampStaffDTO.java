package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.Instant;

public class CampStaffDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long campId;
	private Long hospitalEmployeeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCampId() {
		return campId;
	}

	public void setCampId(Long campId) {
		this.campId = campId;
	}

	public Long getHospitalEmployeeId() {
		return hospitalEmployeeId;
	}

	public void setHospitalEmployeeId(Long hospitalEmployeeId) {
		this.hospitalEmployeeId = hospitalEmployeeId;
	}

	public String toString() {
		return "CampStaffDTO [" +
		"id=" + id +
		"campId=" + campId +
		"hospitalEmployeeId=" + hospitalEmployeeId +
		"]";
	}
}
