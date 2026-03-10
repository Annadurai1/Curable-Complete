package com.curable.service.dto;

import org.springframework.format.annotation.DateTimeFormat;

import com.curable.domain.enums.Gender;
import com.curable.domain.enums.MaritalStatus;

import java.io.Serializable;
import java.time.Instant;

public class CampDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Long hospitalId;
	private Long panchayatMasterId;
	private String address;
	private String pincode;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Instant startDate;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Instant endDate;
	private String campIdPrefix;
	private Gender gender;
	private MaritalStatus maritalstatus;
	private int noDoctors;
	private int noNurses;
	private int noSocialworkers;
	private int noProgramcordinators;
	private int noCampcordinators;

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

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Long getPanchayatMasterId() {
		return panchayatMasterId;
	}

	public void setPanchayatMasterId(Long panchayatMasterId) {
		this.panchayatMasterId = panchayatMasterId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public MaritalStatus getMaritalStatus() {
		return maritalstatus;
	}

	public void setMaritalStatus(MaritalStatus maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public int getNoCampcordinators() {
		return noCampcordinators;
	}
	
	public void setNoCampcordinators(int noCampcordinators) {
		this.noCampcordinators = noCampcordinators; 
	}
	
	public int getNoProgramcordinators() {
		return noProgramcordinators;
	}
	
	public void setNoProgramcordinators(int noProgramcordinators) {
		this.noProgramcordinators = noProgramcordinators; 
	}
	
	public int getNoSocialworkers() {
		return noSocialworkers;
	}
	
	public void setNoSocialworkers(int noSocialworkers) {
		this.noSocialworkers = noSocialworkers; 
	}
	
	public int getNoNurses() {
		return noNurses;
	}
	
	public void setNoNurses(int noNurses) {
		this.noNurses = noNurses; 
	}
	
	public int getNoDoctors() {
		return noDoctors;
	}
	
	public void setNoDoctors(int noDoctors) {
		this.noDoctors = noDoctors; 
	}
	public String getCampIdPrefix() {
		return campIdPrefix;
	}

	public void setCampIdPrefix(String campIdPrefix) {
		this.campIdPrefix = campIdPrefix;
	}

	public String toString() {
		return "CampDTO [" +
		"id=" + id +
		"name=" + name +
		"hospitalId=" + hospitalId +
		"panchayatMasterId=" + panchayatMasterId +
		"address=" + address +
		"pincode=" + pincode +
		"startDate=" + startDate +
		"endDate=" + endDate +
		"campIdPrefix=" + campIdPrefix +
		"]";
	}
}
