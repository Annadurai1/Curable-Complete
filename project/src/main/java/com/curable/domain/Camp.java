package com.curable.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import com.curable.domain.enums.Gender;
import com.curable.domain.enums.MaritalStatus;

@Audited
@Entity
@Table(name = "camp")
public class Camp extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId")
	private Hospital hospital;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayatMasterId")
	private PanchayatMaster panchayatMaster;

	@Column(name = "address")
	private String address;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "startDate")
	private Instant startDate;

	@Column(name = "endDate")
	private Instant endDate;
	
	@Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
	
	@Column(name = "maritalstatus")
    @Enumerated(EnumType.ORDINAL)
    private MaritalStatus maritalstatus;
	
	@Column(name = "noCampcordinators")
	private int noCampcordinators;
	
	@Column(name = "noProgramcordinators")
	private int noProgramcordinators;
	
	@Column(name = "noSocialworkers")
	private int noSocialworkers;
	
	@Column(name = "noNurses")
	private int noNurses;
	
	@Column(name = "noDoctors")
	private int noDoctors;

	@Column(name = "campIdPrefix")
	private String campIdPrefix;

	public Camp() {
	}

	public Camp(Long id, String name, Hospital hospital, PanchayatMaster panchayatMaster, String address, String pincode, Instant startDate, Instant endDate, String campIdPrefix) {
		this.id = id;
		this.name = name;
		this.hospital = hospital;
		this.panchayatMaster = panchayatMaster;
		this.address = address;
		this.pincode = pincode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.campIdPrefix = campIdPrefix;
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

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public PanchayatMaster getPanchayatMaster() {
		return panchayatMaster;
	}

	public void setPanchayatMaster(PanchayatMaster panchayatMaster) {
		
		this.panchayatMaster = panchayatMaster;
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
		if (campIdPrefix != "" || campIdPrefix != null)
		   this.campIdPrefix = campIdPrefix;

	}

	public String toString() {
		return "Camp [" +
		"id=" + id +
		"name=" + name +
		"hospital=" + hospital +
		"panchayatMaster=" + panchayatMaster +
		"address=" + address +
		"pincode=" + pincode +
		"startDate=" + startDate.toString() +
		"endDate=" + endDate.toString() +
		"campIdPrefix=" + campIdPrefix +
		"]";
	}
}
