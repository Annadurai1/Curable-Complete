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
@Table(name = "hospitalgroup")
public class HospitalGroup extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "groupName")
	private String groupName;

	@Column(name = "description")
	private String description;

	@Column(name = "licenseNo")
	private String licenseNo;

	public HospitalGroup() {
	}

	public HospitalGroup(Long id, String groupName, String description, String licenseNo) {
		this.id = id;
		this.groupName = groupName;
		this.description = description;
		this.licenseNo = licenseNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String toString() {
		return "HospitalGroup [" +
		"id=" + id +
		"groupName=" + groupName +
		"description=" + description +
		"licenseNo=" + licenseNo +
		"]";
	}
}
