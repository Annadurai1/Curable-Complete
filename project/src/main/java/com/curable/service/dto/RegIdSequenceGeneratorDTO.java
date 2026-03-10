package com.curable.service.dto;

import com.curable.domain.Camp;

public class RegIdSequenceGeneratorDTO {
	private Long id;

	private Camp camp;

	private Long sequenceNo;

	private Long max;

	private Long increment;

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

	public Long getIncrement() {
		return increment;
	}

	public void setIncrement(Long increment) {
		this.increment = increment;
	}
	
	
}
