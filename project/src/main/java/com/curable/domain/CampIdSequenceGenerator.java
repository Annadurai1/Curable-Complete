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
@Table(name = "campIdSequenceGenerator")
public class CampIdSequenceGenerator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "hospitalId")
	private Hospital hospital;

	@Column(name = "sequenceNo")
	private Long sequenceNo;

	@Column(name = "max")
	private Long max;

	@Column(name = "increment")
	private Long increment;

	@Column(name = "isRecordDeleted", columnDefinition = "boolean default false", nullable = false)
	@JsonIgnore
	private Boolean isRecordDeleted = false;

}
