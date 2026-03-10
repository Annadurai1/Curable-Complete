package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.Instant;

public class CampDiseasesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long campId;
	private Long subDiseaseMasterId;

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

	public Long getSubDiseaseMasterId() {
		return subDiseaseMasterId;
	}

	public void setSubDiseaseMasterId(Long subDiseaseMasterId) {
		this.subDiseaseMasterId = subDiseaseMasterId;
	}

	public String toString() {
		return "CampDiseasesDTO [" +
		"id=" + id +
		"campId=" + campId +
		"subDiseaseMasterId=" + subDiseaseMasterId +
		"]";
	}
}
