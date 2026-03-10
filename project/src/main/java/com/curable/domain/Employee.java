package com.curable.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "employee")
public class Employee extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeRoleMasterId")
	private EmployeeRoleMaster employeeRoleMaster;

	@Column(name = "name")
	private String name;

	@Column(name = "yearsExp")
	private Double yearsExp;

	@Column(name = "phoneNo")
	private String phoneNo;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;

	@Column(name = "keycloakuserid")
	private String keycloakUserId;

	public String getKeycloakUserId() {
		return keycloakUserId;
	}

	public void setKeycloakUserId(String keycloakUserId) {
		this.keycloakUserId = keycloakUserId;
	}

	public Employee() {
	}

	public Employee(Long id, EmployeeRoleMaster employeeRoleMaster, String name, Double yearsExp, String phoneNo,
			String gender, String email) {
		this.id = id;
		this.employeeRoleMaster = employeeRoleMaster;
		this.name = name;
		this.yearsExp = yearsExp;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.email = email;
	}

	public Employee(Long id, EmployeeRoleMaster employeeRoleMaster, String name, Double yearsExp, String phoneNo,
			String gender, String email, String keycloakUserId) {
		this.id = id;
		this.employeeRoleMaster = employeeRoleMaster;
		this.name = name;
		this.yearsExp = yearsExp;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.email = email;
		this.keycloakUserId = keycloakUserId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmployeeRoleMaster getEmployeeRoleMaster() {
		return employeeRoleMaster;
	}

	public void setEmployeeRoleMaster(EmployeeRoleMaster employeeRoleMaster) {
		this.employeeRoleMaster = employeeRoleMaster;
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", yearsExp=" + yearsExp + ", phoneNo=" + phoneNo + ", gender="
				+ gender + ", email=" + email + ", keycloakUserId=" + keycloakUserId + "]";
	}

}
