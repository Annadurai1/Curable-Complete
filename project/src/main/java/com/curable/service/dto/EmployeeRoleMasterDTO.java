package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.Instant;

public class EmployeeRoleMasterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String toString() {
		return "EmployeeRoleMasterDTO [" +
		"id=" + id +
		"name=" + name +
		"description=" + description +
		"]";
	}
}
