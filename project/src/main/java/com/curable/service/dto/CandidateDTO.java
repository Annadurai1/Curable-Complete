package com.curable.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.curable.domain.enums.Gender;
import com.curable.service.dto.custom.DiseaseEligibilityDTO;

public class CandidateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String registraionId;
	private Long campId;
	private Long optionalId;
	private String name;
	private Gender gender;
	private Integer age;
	private String maritalStatus;
	private Boolean socialHabits;
	private String spouseName;
	private String mobileNo;
	private String aadhar;
	private String address;
	private String email;
	private Boolean tobaccoUser;
	private Long parentCandidateId;
	private String surveyStatus;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Instant consentDate;
	private String consentSign;
	private LocalDate dob;
	private Integer streetId;
	private String fatherName;
	private String alternateMobileNo;
	private String occupation;
	private Integer monthlyIncome;
	private String houseType;
	private String voterId;
	private String education;
	private String rationCard;
	private Long hospitalId;
	private String reason;
	private List<DiseaseEligibilityDTO> eligibleDiseases;
	private List<CandidateHabitDTO> candidateHabitDTOs;
	private int screenId;
	private Boolean enrolled;
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

	private String relation;

	public int getScreenId() {
		return screenId;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public List<CandidateHabitDTO> getCandidateHabitDTOs() {
		return candidateHabitDTOs;
	}

	public void setCandidateHabitDTOs(List<CandidateHabitDTO> candidateHabitDTOs) {
		this.candidateHabitDTOs = candidateHabitDTOs;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<DiseaseEligibilityDTO> getEligibleDiseases() {
		return eligibleDiseases;
	}

	public void setEligibleDiseases(List<DiseaseEligibilityDTO> eligibleDiseases) {
		this.eligibleDiseases = eligibleDiseases;
	}

	public CandidateDTO(Long id, String name, Gender gender, Integer age, String mobileNo, Long hospitalId,
			String registraionId) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.mobileNo = mobileNo;
		this.hospitalId = hospitalId;
		this.registraionId = registraionId;
	}

	public CandidateDTO(Long id, String name, Gender gender, Integer age, String mobileNo, Long hospitalId,
			String registraionId, String spouseName, String fatherName) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.mobileNo = mobileNo;
		this.hospitalId = hospitalId;
		this.registraionId = registraionId;
		this.spouseName = spouseName;
		this.fatherName = fatherName;
	}

	public CandidateDTO(Long id, String name, Gender gender, Integer age, String maritalStatus, String tobaccoHabbits,
			Long parentCandidateId, String relation) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.maritalStatus = maritalStatus;
		this.tobaccoHabbits = tobaccoHabbits;
		this.parentCandidateId = parentCandidateId;
		this.relation = relation;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
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

	public Long getCampId() {
		return campId;
	}

	public void setCampId(Long campId) {
		this.campId = campId;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}

	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CandidateDTO(String fatherName, String spouseName, String alternateMobileNo, Integer monthlyIncome,
			String houseType, String education, String occupation, String aadhar, String voterId, String rationCard,
			Boolean tobaccoUser, Boolean socialHabit) {
		super();
		this.spouseName = spouseName;
		this.aadhar = aadhar;
		this.tobaccoUser = tobaccoUser;
		this.fatherName = fatherName;
		this.alternateMobileNo = alternateMobileNo;
		this.occupation = occupation;
		this.monthlyIncome = monthlyIncome;
		this.houseType = houseType;
		this.voterId = voterId;
		this.education = education;
		this.rationCard = rationCard;
		this.socialHabits = socialHabit;
	}

	public CandidateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CandidateDTO [id=" + id + ", registraionId=" + registraionId + ", campId=" + campId + ", optionalId="
				+ optionalId + ", name=" + name + ", gender=" + gender + ", age=" + age + ", maritalStatus="
				+ maritalStatus + ", spouseName=" + spouseName + ", mobileNo=" + mobileNo + ", aadhar=" + aadhar
				+ ", address=" + address + ", email=" + email + ", tobaccoUser=" + tobaccoUser + ", parentCandidateId="
				+ parentCandidateId + ", surveyStatus=" + surveyStatus + ", consentDate=" + consentDate
				+ ", consentSign=" + consentSign + ", dob=" + dob + ", streetId=" + streetId + ", fatherName="
				+ fatherName + ", alternateMobileNo=" + alternateMobileNo + ", occupation=" + occupation + "]";
	}

}
