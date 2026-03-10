package com.curable.domain;

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
@Table(name = "hospitalemployeemap")
public class HospitalEmployeeMap extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId")
	private Hospital hospital;

	public HospitalEmployeeMap() {
	}

	public HospitalEmployeeMap(Long id, Employee hospitalEmployee, Hospital hospital) {
		this.id = id;
		this.employee = hospitalEmployee;
		this.hospital = hospital;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getHospitalEmployee() {
		return employee;
	}

	public void setHospitalEmployee(Employee hospitalEmployee) {
		this.employee = hospitalEmployee;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String toString() {
		return "HospitalEmployeeMap [" +
		"id=" + id +
		"hospitalEmployee=" + employee +
		"hospital=" + hospital +
		"]";
	}
}
