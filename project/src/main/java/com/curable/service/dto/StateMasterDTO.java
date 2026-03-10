package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.Instant;

public class StateMasterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String name;

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

	public String toString() {
		return "StateMasterDTO [" +
		"id=" + id +
		"code=" + code +
		"name=" + name +
		"]";
	}
}
