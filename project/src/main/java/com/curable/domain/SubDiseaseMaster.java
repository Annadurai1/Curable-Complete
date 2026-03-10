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
@Table(name = "subdiseasemaster")
public class SubDiseaseMaster extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diseaseMasterId")
	private DiseaseMaster diseaseMaster;

	public SubDiseaseMaster() {
	}

	public SubDiseaseMaster(Long id, String name, String description, DiseaseMaster diseaseMaster) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.diseaseMaster = diseaseMaster;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public DiseaseMaster getDiseaseMaster() {
		return diseaseMaster;
	}

	public void setDiseaseMaster(DiseaseMaster diseaseMaster) {
		this.diseaseMaster = diseaseMaster;
	}

	public String toString() {
		return "SubDiseaseMaster [" +
		"id=" + id +
		"name=" + name +
		"description=" + description +
		"diseaseMaster=" + diseaseMaster +
		"]";
	}
}
