package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.Instant;

public class TaluqMasterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String name;
	private Long districtMasterId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDistrictMasterId() {
		return districtMasterId;
	}

	public void setDistrictMasterId(Long districtMasterId) {
		this.districtMasterId = districtMasterId;
	}

	public String toString() {
		return "TaluqMasterDTO [" +
		"id=" + id +
		"code=" + code +
		"name=" + name +
		"districtMasterId=" + districtMasterId +
		"]";
	}
}
