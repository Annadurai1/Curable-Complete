package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.Camp;
import com.curable.service.dto.CampDTO;

@Mapper(componentModel = "spring", uses = {HospitalMapper.class,PanchayatMasterMapper.class})
public interface CampMapper extends EntityMapper<CampDTO, Camp> {
	@Mapping(source = "hospital.id", target = "hospitalId")
	@Mapping(source = "panchayatMaster.id", target = "panchayatMasterId")
	CampDTO toDto(Camp camp);

	@Mapping(source = "hospitalId", target = "hospital")
	@Mapping(source = "panchayatMasterId", target = "panchayatMaster")
	Camp toEntity(CampDTO campDTO);
	
	default Camp fromId(Long id) {
		if (id == null) {
			return null;
		}
		Camp camp = new Camp();
		camp.setId(id);
		return camp;
	}
}