package com.curable.domain;

import java.time.Instant;
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
@Table(name = "hospital")
public class Hospital extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalGroupId")
	private HospitalGroup hospitalGroup;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@Column(name = "licenseNo")
	private String licenseNo;

	public Hospital() {
	}

	public Hospital(Long id, HospitalGroup hospitalGroup, String name, String description, String address, String licenseNo) {
		this.id = id;
		this.hospitalGroup = hospitalGroup;
		this.name = name;
		this.description = description;
		this.address = address;
		this.licenseNo = licenseNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HospitalGroup getHospitalGroup() {
		return hospitalGroup;
	}

	public void setHospitalGroup(HospitalGroup hospitalGroup) {
		this.hospitalGroup = hospitalGroup;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String toString() {
		return "Hospital [" +
		"id=" + id +
		"hospitalGroup=" + hospitalGroup +
		"name=" + name +
		"description=" + description +
		"address=" + address +
		"licenseNo=" + licenseNo +
		"]";
	}
}
