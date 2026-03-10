package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.PanchayatMaster;
import com.curable.service.dto.PanchayatMasterDTO;

@Mapper(componentModel = "spring", uses = {TaluqMasterMapper.class})
public interface PanchayatMasterMapper extends EntityMapper<PanchayatMasterDTO, PanchayatMaster> {
	@Mapping(source = "taluqMaster.id", target = "taluqMasterId")
	PanchayatMasterDTO toDto(PanchayatMaster panchayatMaster);

	@Mapping(source = "taluqMasterId", target = "taluqMaster")
	PanchayatMaster toEntity(PanchayatMasterDTO panchayatMasterDTO);

	default PanchayatMaster fromId(Long id) {
		if (id == null) {
			return null;
		}
		PanchayatMaster panchayatMaster = new PanchayatMaster();
		panchayatMaster.setId(id);
		return panchayatMaster;
	}
}