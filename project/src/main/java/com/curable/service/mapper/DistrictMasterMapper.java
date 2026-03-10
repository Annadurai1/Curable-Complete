package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.DistrictMaster;
import com.curable.service.dto.DistrictMasterDTO;

@Mapper(componentModel = "spring", uses = {StateMasterMapper.class})
public interface DistrictMasterMapper extends EntityMapper<DistrictMasterDTO, DistrictMaster> {
	@Mapping(source = "stateMaster.id", target = "stateMasterId")
	DistrictMasterDTO toDto(DistrictMaster districtMaster);

	@Mapping(source = "stateMasterId", target = "stateMaster")
	DistrictMaster toEntity(DistrictMasterDTO districtMasterDTO);

	default DistrictMaster fromId(Long id) {
		if (id == null) {
			return null;
		}
		DistrictMaster districtMaster = new DistrictMaster();
		districtMaster.setId(id);
		return districtMaster;
	}
}