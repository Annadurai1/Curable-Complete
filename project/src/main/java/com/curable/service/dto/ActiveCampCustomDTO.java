package com.curable.service.dto;

public interface ActiveCampCustomDTO {
	public String getCampIdPrefix();
	public String getCampName();
	public String getStateName();
	public String getDistrictName();
	public String getTalukName();
	public String getPanchayatName();
	public String getPincode();
	public Long getCampId();
	public String getStartDate();
	public String getEndDate();
	public Long getPanchayatId();
	public String getDisplayStartDate();
	public String getDisplayEndDate();
	
	public Long getNoCampcordinators();
	public Long getNoDoctors();
	public Long getNoNurses();
    public Long getNoProgramCoordinators();
    public Long getNoSocialWorkers();
	
}
