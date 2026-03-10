package com.curable.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

//import lombok.Data;

@Entity
//@Data
public class GeneralSeqGenerator {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "my_seq", allocationSize = 1, initialValue = 1)
	private long seqNumber;

	@Column(name = "hospitalId")
	private Long hospitalId;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public void setSeqNumber(long seqNumber) {
		this.seqNumber = seqNumber;
	}

	public Long getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(Long id) {
		this.seqNumber = id;
	}

	public GeneralSeqGenerator(Long hospitalId) {
		super();
		this.hospitalId = hospitalId;
	}

	public GeneralSeqGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
