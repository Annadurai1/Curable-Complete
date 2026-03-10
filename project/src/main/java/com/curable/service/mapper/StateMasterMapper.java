package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.StateMaster;
import com.curable.service.dto.StateMasterDTO;

@Mapper(componentModel = "spring", uses = {})
public interface StateMasterMapper extends EntityMapper<StateMasterDTO, StateMaster> {
	default StateMaster fromId(Long id) {
		if (id == null) {
			return null;
		}
		StateMaster stateMaster = new StateMaster();
		stateMaster.setId(id);
		return stateMaster;
	}
}