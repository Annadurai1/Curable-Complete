package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.TaluqMaster;
import com.curable.service.dto.TaluqMasterDTO;

@Mapper(componentModel = "spring", uses = {DistrictMasterMapper.class})
public interface TaluqMasterMapper extends EntityMapper<TaluqMasterDTO, TaluqMaster> {
	@Mapping(source = "districtMaster.id", target = "districtMasterId")
	TaluqMasterDTO toDto(TaluqMaster taluqMaster);

	@Mapping(source = "districtMasterId", target = "districtMaster")
	TaluqMaster toEntity(TaluqMasterDTO taluqMasterDTO);

	default TaluqMaster fromId(Long id) {
		if (id == null) {
			return null;
		}
		TaluqMaster taluqMaster = new TaluqMaster();
		taluqMaster.setId(id);
		return taluqMaster;
	}
}