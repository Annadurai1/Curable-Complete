package com.curable.domain;

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
@Table(name = "candidatehabit")
public class CandidateHabit extends AbstractAuditingEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "habits")
	private String habits;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "duration")
	private Long duration;
	
	@Column(name = "frequency")
	private String frequency;
	
	@Column(name = "quit")
	private Boolean quit;
	
	@Column(name = "howLong")
	private Long howLong;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidateId")
	private Candidate candidate;

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

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "CandidateHabit [id=" + id + ", habits=" + habits + ", type=" + type + ", duration=" + duration
				+ ", frequency=" + frequency + ", quit=" + quit + ", howLong=" + howLong + "]";
	}
	
	
}
