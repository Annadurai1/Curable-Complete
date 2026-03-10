package com.curable.service.dto;

public class CandidateHabitDTO {
	private Long candidateId;
	private Long id;

	private String habits;

	private String type;

	private Long duration;

	private String frequency;

	private Boolean quit;

	private Long howLong;

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHabits() {
		return habits;
	}

	public void setHabits(String habits) {
		this.habits = habits;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Boolean isQuit() {
		return quit;
	}

	public void setQuit(Boolean quit) {
		this.quit = quit;
	}

	public Long getHowLong() {
		return howLong;
	}

	public void setHowLong(Long howLong) {
		this.howLong = howLong;
	}

	@Override
	public String toString() {
		return "CandidateHabitDTO [candidateId=" + candidateId + ", id=" + id + ", habits=" + habits + ", type=" + type
				+ ", duration=" + duration + ", frequency=" + frequency + ", quit=" + quit + ", howLong=" + howLong
				+ "]";
	}

}
