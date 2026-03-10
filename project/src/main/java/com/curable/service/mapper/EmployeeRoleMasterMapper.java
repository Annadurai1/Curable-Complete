package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.EmployeeRoleMaster;
import com.curable.service.dto.EmployeeRoleMasterDTO;

@Mapper(componentModel = "spring", uses = {})
public interface EmployeeRoleMasterMapper extends EntityMapper<EmployeeRoleMasterDTO, EmployeeRoleMaster> {
	default EmployeeRoleMaster fromId(Long id) {
		if (id == null) {
			return null;
		}
		EmployeeRoleMaster employeeRoleMaster = new EmployeeRoleMaster();
		employeeRoleMaster.setId(id);
		return employeeRoleMaster;
	}
}