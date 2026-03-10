package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.Hospital;
import com.curable.service.dto.HospitalDTO;

@Mapper(componentModel = "spring", uses = {HospitalGroupMapper.class})
public interface HospitalMapper extends EntityMapper<HospitalDTO, Hospital> {
	@Mapping(source = "hospitalGroup.id", target = "hospitalGroupId")
	HospitalDTO toDto(Hospital hospital);

	@Mapping(source = "hospitalGroupId", target = "hospitalGroup")
	Hospital toEntity(HospitalDTO hospitalDTO);

	default Hospital fromId(Long id) {
		if (id == null) {
			return null;
		}
		Hospital hospital = new Hospital();
		hospital.setId(id);
		return hospital;
	}
}