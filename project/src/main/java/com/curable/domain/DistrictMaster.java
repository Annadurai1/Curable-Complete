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
@Table(name = "districtmaster")
public class DistrictMaster extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateMasterId")
	private StateMaster stateMaster;

	public DistrictMaster() {
	}

	public DistrictMaster(Long id, String code, String name, StateMaster stateMaster) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.stateMaster = stateMaster;
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

	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public String toString() {
		return "DistrictMaster [" +
		"id=" + id +
		"code=" + code +
		"name=" + name +
		"stateMaster=" + stateMaster +
		"]";
	}
}
