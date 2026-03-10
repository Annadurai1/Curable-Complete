package com.curable.service.dto.custom;

import java.util.List;

import com.curable.service.dto.CampDTO;
import com.curable.service.dto.CampStaffDTO;

public class CampCreationDTO {
	public CampDTO campDTO;
	public List<CampStaffDTO> campStaffs;

	public CampDTO getCampDTO() {
		return campDTO;
	}

	public void setCampDTO(CampDTO campDTO) {
		this.campDTO = campDTO;
	}

	public List<CampStaffDTO> getCampStaffs() {
		return campStaffs;
	}

	public void setCampStaffs(List<CampStaffDTO> campStaffs) {
		this.campStaffs = campStaffs;
	}

}
