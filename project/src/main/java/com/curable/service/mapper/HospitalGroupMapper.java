package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.HospitalGroup;
import com.curable.service.dto.HospitalGroupDTO;

@Mapper(componentModel = "spring", uses = {})
public interface HospitalGroupMapper extends EntityMapper<HospitalGroupDTO, HospitalGroup> {
	default HospitalGroup fromId(Long id) {
		if (id == null) {
			return null;
		}
		HospitalGroup hospitalGroup = new HospitalGroup();
		hospitalGroup.setId(id);
		return hospitalGroup;
	}
}