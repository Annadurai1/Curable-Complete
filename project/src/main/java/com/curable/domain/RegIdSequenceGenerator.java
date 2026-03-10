package com.curable.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "regIdSequenceGenerator")
public class RegIdSequenceGenerator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "campId")
	private Camp camp;

	@Column(name = "sequenceNo")
	private Long sequenceNo;

	@Column(name = "max")
	private Long max;

	@Column(name = "increment")
	private Long increment;

	@Column(name = "isRecordDeleted", columnDefinition = "boolean default false", nullable = false)
	@JsonIgnore
	private Boolean isRecordDeleted = false;

	@Column(name = "streetId")
	private Long streetId;

	@OneToOne
	@JoinColumn(name = "hospitalId")
	private Hospital hospital;

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "RegIdSequenceGenerator [id=" + id + ", sequenceNo=" + sequenceNo + ", max=" + max + ", increment="
				+ increment + ", isRecordDeleted=" + isRecordDeleted + ", streetId=" + streetId + "]";
	}

	public Long getStreetId() {
		return streetId;
	}

	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}

	public Boolean getIsRecordDeleted() {
		return isRecordDeleted;
	}

	public void setIsRecordDeleted(Boolean isRecordDeleted) {
		this.isRecordDeleted = isRecordDeleted;
	}

	public Long getIncrement() {
		return increment;
	}

	public void setIncrement(Long increment) {
		this.increment = increment;
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

	public Long getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public RegIdSequenceGenerator(Long id, Camp camp, Long sequenceNo, Long max, Long increment, Long streetId) {
		super();
		this.id = id;
		this.camp = camp;
		this.sequenceNo = sequenceNo;
		this.max = max;
		this.increment = increment;
		this.streetId = streetId;
	}

	public RegIdSequenceGenerator(Long id, Hospital hospital, Long sequenceNo, Long max, Long increment) {
		super();
		this.id = id;
		this.hospital = hospital;
		this.sequenceNo = sequenceNo;
		this.max = max;
		this.increment = increment;

	}

	public RegIdSequenceGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

}
