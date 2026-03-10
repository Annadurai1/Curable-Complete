package com.curable.service.dto;

import java.io.Serializable;
import java.util.List;

public class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long employeeRoleMasterId;
	private String name;
	private Double yearsExp;
	private String phoneNo;
	private String gender;
	private String email;
	private String role;
	private String keycloakUserId;
	private List<Long> hospitalId;
	private String password;
	private Boolean isRecordDeleted;

	public Boolean getIsRecordDeleted() {
		return isRecordDeleted;
	}

	public void setIsRecordDeleted(Boolean isRecordDeleted) {
		this.isRecordDeleted = isRecordDeleted;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeycloakUserId() {
		return keycloakUserId;
	}

	public void setKeycloakUserId(String keycloakUserId) {
		this.keycloakUserId = keycloakUserId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Long> getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(List<Long> hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeRoleMasterId() {
		return employeeRoleMasterId;
	}

	public void setEmployeeRoleMasterId(Long employeeRoleMasterId) {
		this.employeeRoleMasterId = employeeRoleMasterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getYearsExp() {
		return yearsExp;
	}

	public void setYearsExp(Double yearsExp) {
		this.yearsExp = yearsExp;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmployeeDTO(Long id, String name, String role) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
	}

	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", employeeRoleMasterId=" + employeeRoleMasterId + ", name=" + name
				+ ", yearsExp=" + yearsExp + ", phoneNo=" + phoneNo + ", gender=" + gender + ", email=" + email
				+ ", keycloakUserId=" + keycloakUserId + "]";
	}
}
