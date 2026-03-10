package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.Instant;

public class HospitalEmployeeMapDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long hospitalEmployeeId;
	private Long hospitalId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHospitalEmployeeId() {
		return hospitalEmployeeId;
	}

	public void setHospitalEmployeeId(Long hospitalEmployeeId) {
		this.hospitalEmployeeId = hospitalEmployeeId;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String toString() {
		return "HospitalEmployeeMapDTO [" +
		"id=" + id +
		"hospitalEmployeeId=" + hospitalEmployeeId +
		"hospitalId=" + hospitalId +
		"]";
	}
}
