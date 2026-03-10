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
@Table(name = "campstaff")
public class CampStaff extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campId")
	private Camp camp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee hospitalEmployee;

	public CampStaff() {
	}

	public CampStaff(Long id, Camp camp, Employee hospitalEmployee) {
		this.id = id;
		this.camp = camp;
		this.hospitalEmployee = hospitalEmployee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Camp getCamp() {
		return camp;
	}

	public void setCamp(Camp camp) {
		this.camp = camp;
	}

	public Employee getHospitalEmployee() {
		return hospitalEmployee;
	}

	public void setHospitalEmployee(Employee hospitalEmployee) {
		this.hospitalEmployee = hospitalEmployee;
	}

	public String toString() {
		return "CampStaff [" +
		"id=" + id +
		"camp=" + camp +
		"hospitalEmployee=" + hospitalEmployee +
		"]";
	}
}
