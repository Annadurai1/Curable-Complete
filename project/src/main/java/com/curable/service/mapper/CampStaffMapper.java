package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.CampStaff;
import com.curable.service.dto.CampStaffDTO;

@Mapper(componentModel = "spring", uses = {CampMapper.class,EmployeeMapper.class})
public interface CampStaffMapper extends EntityMapper<CampStaffDTO, CampStaff> {
	@Mapping(source = "camp.id", target = "campId")
	@Mapping(source = "hospitalEmployee.id", target = "hospitalEmployeeId")
	CampStaffDTO toDto(CampStaff campStaff);

	@Mapping(source = "campId", target = "camp")
	@Mapping(source = "hospitalEmployeeId", target = "hospitalEmployee")
	CampStaff toEntity(CampStaffDTO campStaffDTO);

	default CampStaff fromId(Long id) {
		if (id == null) {
			return null;
		}
		CampStaff campStaff = new CampStaff();
		campStaff.setId(id);
		return campStaff;
	}
}