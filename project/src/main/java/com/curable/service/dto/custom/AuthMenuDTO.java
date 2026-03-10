package com.curable.service.dto.custom;

import java.util.List;

public class AuthMenuDTO {

	private Long userId;
	private String userName;
	private String email;
	private String roleName;
	private Long roleId;
	private Boolean isRecordDeleted;
	private String gender;
	private String phoneNo;
	private List<CustomMenuDTO> customMenuDTOs;
	private String message;
	private Long hospitalId;
	private String tenantName;
	private String keycloakUserId;

	public String getKeycloakUserId() {
		return keycloakUserId;
	}

	public void setKeycloakUserId(String keycloakUserId) {
		this.keycloakUserId = keycloakUserId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Boolean getIsRecordDeleted() {
		return isRecordDeleted;
	}

	public void setIsRecordDeleted(Boolean isRecordDeleted) {
		this.isRecordDeleted = isRecordDeleted;
	}

	public List<CustomMenuDTO> getCustomMenuDTOs() {
		return customMenuDTOs;
	}

	public void setCustomMenuDTOs(List<CustomMenuDTO> customMenuDTOs) {
		this.customMenuDTOs = customMenuDTOs;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public AuthMenuDTO(Long userId, String userName, String email, String roleName, Long roleId,
			Boolean isRecordDeleted, String gender, String phoneNo) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.roleName = roleName;
		this.roleId = roleId;
		this.isRecordDeleted = isRecordDeleted;
		this.gender = gender;
		this.phoneNo = phoneNo;
	}

	public AuthMenuDTO(Long userId, String userName, String email, String roleName, Long roleId,
			Boolean isRecordDeleted, String gender, String phoneNo, String keycloakUserId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.roleName = roleName;
		this.roleId = roleId;
		this.isRecordDeleted = isRecordDeleted;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.keycloakUserId = keycloakUserId;
	}

	public AuthMenuDTO(String message) {
		super();
		this.message = message;
	}

	public AuthMenuDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AuthMenuDTO [userId=" + userId + ", userName=" + userName + ", email=" + email + ", roleName="
				+ roleName + ", roleId=" + roleId + ", customMenuDTOs=" + customMenuDTOs + "]";
	}

}
