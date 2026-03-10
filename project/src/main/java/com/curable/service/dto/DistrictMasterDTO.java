package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.Instant;

public class DistrictMasterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String name;
	private Long stateMasterId;

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

	public Long getStateMasterId() {
		return stateMasterId;
	}

	public void setStateMasterId(Long stateMasterId) {
		this.stateMasterId = stateMasterId;
	}

	public String toString() {
		return "DistrictMasterDTO [" +
		"id=" + id +
		"code=" + code +
		"name=" + name +
		"stateMasterId=" + stateMasterId +
		"]";
	}
}
