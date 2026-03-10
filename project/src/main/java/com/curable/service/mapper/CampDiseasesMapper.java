package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.CampDiseases;
import com.curable.service.dto.CampDiseasesDTO;

@Mapper(componentModel = "spring", uses = {CampMapper.class,SubDiseaseMasterMapper.class})
public interface CampDiseasesMapper extends EntityMapper<CampDiseasesDTO, CampDiseases> {
	@Mapping(source = "camp.id", target = "campId")
	@Mapping(source = "subDiseaseMaster.id", target = "subDiseaseMasterId")
	CampDiseasesDTO toDto(CampDiseases campDiseases);

	@Mapping(source = "campId", target = "camp")
	@Mapping(source = "subDiseaseMasterId", target = "subDiseaseMaster")
	CampDiseases toEntity(CampDiseasesDTO campDiseasesDTO);

	default CampDiseases fromId(Long id) {
		if (id == null) {
			return null;
		}
		CampDiseases campDiseases = new CampDiseases();
		campDiseases.setId(id);
		return campDiseases;
	}
}