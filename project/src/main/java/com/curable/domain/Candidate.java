package com.curable.domain;

import java.time.Instant;
import java.time.LocalDate;

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

@Audited
@Entity
@Table(name = "candidate")
public class Candidate extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "registraionId")
	private String registraionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campId")
	private Camp camp;

	@Column(name = "optionalId")
	private Long optionalId;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "age")
	private Integer age;

	@Column(name = "maritalStatus")
	private String maritalStatus;

	@Column(name = "spouseName")
	private String spouseName;

	@Column(name = "mobileNo")
	private String mobileNo;

	@Column(name = "aadhar")
	private String aadhar;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "tobaccoUser")
	private Boolean tobaccoUser;

	@Column(name = "parentCandidateId")
	private Long parentCandidateId;

	@Column(name = "surveyStatus")
	private String surveyStatus;

	@Column(name = "consentDate")
	private Instant consentDate;

	@Column(name = "consentSign")
	private String consentSign;

	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "streetId")
	private Integer streetId;

	@Column(name = "pinCode")
	private String pinCode;

	@Column(name = "monthlyIncome")
	private Integer monthlyIncome;

	@Column(name = "houseType")
	private String houseType;

	@Column(name = "voterId")
	private String voterId;

	@Column(name = "education")
	private String education;

	@Column(name = "rationCard")
	private String rationCard;

	@Column(name = "fatherName")
	private String fatherName;

	@Column(name = "alternateMobileNo")
	private String alternateMobileNo;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "reason")
	private String reason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId")
	private Hospital hospital;

	@Column(name = "relation")
	private String relation;

	@Column(name = "enrolled", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean enrolled = false;

	@Column(name = "socialHabits")
	private Boolean socialHabits;

	@Column(name = "tobaccoHabbits")
	private String tobaccoHabbits;

	public String getTobaccoHabbits() {
		return tobaccoHabbits;
	}

	public void setTobaccoHabbits(String tobaccoHabbits) {
		this.tobaccoHabbits = tobaccoHabbits;
	}

	public Boolean getSocialHabits() {
		return socialHabits;
	}

	public void setSocialHabits(Boolean socialHabits) {
		this.socialHabits = socialHabits;
	}

	public Boolean getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(Boolean enrolled) {
		this.enrolled = enrolled;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Candidate() {
	}

	public Candidate(Long id, String registraionId, Camp camp, Long optionalId, String name, Gender gender, Integer age,
			String maritalStatus, String spouseName, String mobileNo, String aadhar, String address, String email,
			Boolean tobaccoUser, Long parentCandidateId, String surveyStatus, Instant consentDate, String consentSign) {
		this.id = id;
		this.registraionId = registraionId;
		this.camp = camp;
		this.optionalId = optionalId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.maritalStatus = maritalStatus;
		this.spouseName = spouseName;
		this.mobileNo = mobileNo;
		this.aadhar = aadhar;
		this.address = address;
		this.email = email;
		this.tobaccoUser = tobaccoUser;
		this.parentCandidateId = parentCandidateId;
		this.surveyStatus = surveyStatus;
		this.consentDate = consentDate;
		this.consentSign = consentSign;
	}

	public Candidate(Camp camp, String address, Integer streetId, String regId) {
		super();
		this.camp = camp;
		this.address = address;
		this.streetId = streetId;
		this.registraionId = regId;
	}

	public Candidate(Long id, String registraionId, Camp camp, Long parentCandidateId, Integer streetId,
			Hospital hospital, String address) {
		super();
		this.id = id;
		this.registraionId = registraionId;
		this.camp = camp;
		this.parentCandidateId = parentCandidateId;
		this.streetId = streetId;
		this.hospital = hospital;
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Integer getStreetId() {
		return streetId;
	}

	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Integer getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Integer monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getRationCard() {
		return rationCard;
	}

	public void setRationCard(String rationCard) {
		this.rationCard = rationCard;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistraionId() {
		return registraionId;
	}

	public void setRegistraionId(String registraionId) {
		this.registraionId = registraionId;
	}

	public Camp getCamp() {
		return camp;
	}

	public void setCamp(Camp camp) {
		this.camp = camp;
	}

	public Long getOptionalId() {
		return optionalId;
	}

	public void setOptionalId(Long optionalId) {
		this.optionalId = optionalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}

	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getTobaccoUser() {
		return tobaccoUser;
	}

	public void setTobaccoUser(Boolean tobaccoUser) {
		this.tobaccoUser = tobaccoUser;
	}

	public Long getParentCandidateId() {
		return parentCandidateId;
	}

	public void setParentCandidateId(Long parentCandidateId) {
		this.parentCandidateId = parentCandidateId;
	}

	public String getSurveyStatus() {
		return surveyStatus;
	}

	public void setSurveyStatus(String surveyStatus) {
		this.surveyStatus = surveyStatus;
	}

	public Instant getConsentDate() {
		return consentDate;
	}

	public void setConsentDate(Instant consentDate) {
		this.consentDate = consentDate;
	}

	public String getConsentSign() {
		return consentSign;
	}

	public void setConsentSign(String consentSign) {
		this.consentSign = consentSign;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", registraionId=" + registraionId + ", optionalId=" + optionalId + ", name="
				+ name + ", gender=" + gender + ", age=" + age + ", maritalStatus=" + maritalStatus + ", spouseName="
				+ spouseName + ", mobileNo=" + mobileNo + ", aadhar=" + aadhar + ", address=" + address + ", email="
				+ email + ", tobaccoUser=" + tobaccoUser + ", parentCandidateId=" + parentCandidateId
				+ ", surveyStatus=" + surveyStatus + ", consentDate=" + consentDate + ", consentSign=" + consentSign
				+ ", dob=" + dob + ", streetId=" + streetId + ", pinCode=" + pinCode + ", monthlyIncome="
				+ monthlyIncome + ", houseType=" + houseType + ", voterId=" + voterId + ", education=" + education
				+ ", rationCard=" + rationCard + ", fatherName=" + fatherName + ", alternateMobileNo="
				+ alternateMobileNo + ", occupation=" + occupation + ", reason=" + reason + "]";
	}
}
