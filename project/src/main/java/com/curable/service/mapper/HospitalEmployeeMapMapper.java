package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.HospitalEmployeeMap;
import com.curable.service.dto.HospitalEmployeeMapDTO;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class,HospitalMapper.class})
public interface HospitalEmployeeMapMapper extends EntityMapper<HospitalEmployeeMapDTO, HospitalEmployeeMap> {
	@Mapping(source = "hospitalEmployee.id", target = "hospitalEmployeeId")
	@Mapping(source = "hospital.id", target = "hospitalId")
	HospitalEmployeeMapDTO toDto(HospitalEmployeeMap hospitalEmployeeMap);

	@Mapping(source = "hospitalEmployeeId", target = "hospitalEmployee")
	@Mapping(source = "hospitalId", target = "hospital")
	HospitalEmployeeMap toEntity(HospitalEmployeeMapDTO hospitalEmployeeMapDTO);

	default HospitalEmployeeMap fromId(Long id) {
		if (id == null) {
			return null;
		}
		HospitalEmployeeMap hospitalEmployeeMap = new HospitalEmployeeMap();
		hospitalEmployeeMap.setId(id);
		return hospitalEmployeeMap;
	}
}