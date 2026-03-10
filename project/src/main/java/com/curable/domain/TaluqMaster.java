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
@Table(name = "taluqmaster")
public class TaluqMaster extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "districtMasterId")
	private DistrictMaster districtMaster;

	public TaluqMaster() {
	}

	public TaluqMaster(Long id, String code, String name, DistrictMaster districtMaster) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.districtMaster = districtMaster;
	}

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

	public DistrictMaster getDistrictMaster() {
		return districtMaster;
	}

	public void setDistrictMaster(DistrictMaster districtMaster) {
		this.districtMaster = districtMaster;
	}

	public String toString() {
		return "TaluqMaster [" +
		"id=" + id +
		"code=" + code +
		"name=" + name +
		"districtMaster=" + districtMaster +
		"]";
	}
}
