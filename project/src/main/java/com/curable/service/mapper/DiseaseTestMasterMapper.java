package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.curable.domain.DiseaseTestMaster;
import com.curable.service.dto.DiseaseTestMasterDTO;

@Mapper(componentModel = "spring", uses = { HospitalMapper.class,DiseaseMasterMapper.class })
public interface DiseaseTestMasterMapper extends EntityMapper<DiseaseTestMasterDTO, DiseaseTestMaster> {

	@Mapping(source = "hospital.id", target = "hospitalId")
	@Mapping(source = "diseaseMaster.id", target = "diseaseMasterId")
	DiseaseTestMasterDTO toDto(DiseaseTestMaster diseaseTestMaster);

	@Mapping(source = "hospitalId", target = "hospital")
	@Mapping(source = "diseaseMasterId", target = "diseaseMaster")
	DiseaseTestMaster toEntity(DiseaseTestMasterDTO diseaseTestMasterDTO);

	default DiseaseTestMaster fromId(Long id) {
		if (id == null) {
			return null;
		}
		DiseaseTestMaster diseaseTestMaster = new DiseaseTestMaster();
		diseaseTestMaster.setId(id);
		return diseaseTestMaster;
	}
}