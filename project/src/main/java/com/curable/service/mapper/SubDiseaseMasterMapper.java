package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.SubDiseaseMaster;
import com.curable.service.dto.SubDiseaseMasterDTO;

@Mapper(componentModel = "spring", uses = {DiseaseMasterMapper.class})
public interface SubDiseaseMasterMapper extends EntityMapper<SubDiseaseMasterDTO, SubDiseaseMaster> {
	@Mapping(source = "diseaseMaster.id", target = "diseaseMasterId")
	SubDiseaseMasterDTO toDto(SubDiseaseMaster subDiseaseMaster);

	@Mapping(source = "diseaseMasterId", target = "diseaseMaster")
	SubDiseaseMaster toEntity(SubDiseaseMasterDTO subDiseaseMasterDTO);

	default SubDiseaseMaster fromId(Long id) {
		if (id == null) {
			return null;
		}
		SubDiseaseMaster subDiseaseMaster = new SubDiseaseMaster();
		subDiseaseMaster.setId(id);
		return subDiseaseMaster;
	}
}