package com.curable.service.dto;

public class SearchCustomDTO {

	private Long userId;
	private String search;
	private Long hospitalId;
	private int stage;
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "SearchCustomDTO [userId=" + userId + ", search=" + search + ", hospitalId=" + hospitalId + ", stage="
				+ stage + ", roleId=" + roleId + "]";
	}

}
