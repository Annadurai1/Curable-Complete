package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.DiseaseMaster;
import com.curable.service.dto.DiseaseMasterDTO;

@Mapper(componentModel = "spring", uses = {})
public interface DiseaseMasterMapper extends EntityMapper<DiseaseMasterDTO, DiseaseMaster> {
	default DiseaseMaster fromId(Long id) {
		if (id == null) {
			return null;
		}
		DiseaseMaster diseaseMaster = new DiseaseMaster();
		diseaseMaster.setId(id);
		return diseaseMaster;
	}
}