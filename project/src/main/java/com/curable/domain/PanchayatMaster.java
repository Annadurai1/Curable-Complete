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
@Table(name = "panchayatmaster")
public class PanchayatMaster extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taluqMasterId")
	private TaluqMaster taluqMaster;

	public PanchayatMaster() {
	}

	public PanchayatMaster(Long id, String code, String name, TaluqMaster taluqMaster) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.taluqMaster = taluqMaster;
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

	public TaluqMaster getTaluqMaster() {
		return taluqMaster;
	}

	public void setTaluqMaster(TaluqMaster taluqMaster) {
		this.taluqMaster = taluqMaster;
	}

	public String toString() {
		return "PanchayatMaster [" +
		"id=" + id +
		"code=" + code +
		"name=" + name +
		"taluqMaster=" + taluqMaster +
		"]";
	}
}
