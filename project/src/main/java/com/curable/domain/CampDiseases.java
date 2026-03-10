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
@Table(name = "campdiseases")
public class CampDiseases extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campId")
	private Camp camp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subDiseaseMasterId")
	private SubDiseaseMaster subDiseaseMaster;

	public CampDiseases() {
	}

	public CampDiseases(Long id, Camp camp, SubDiseaseMaster subDiseaseMaster) {
		this.id = id;
		this.camp = camp;
		this.subDiseaseMaster = subDiseaseMaster;
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

	public SubDiseaseMaster getSubDiseaseMaster() {
		return subDiseaseMaster;
	}

	public void setSubDiseaseMaster(SubDiseaseMaster subDiseaseMaster) {
		this.subDiseaseMaster = subDiseaseMaster;
	}

	public String toString() {
		return "CampDiseases [" +
		"id=" + id +
		"camp=" + camp +
		"subDiseaseMaster=" + subDiseaseMaster +
		"]";
	}
}
