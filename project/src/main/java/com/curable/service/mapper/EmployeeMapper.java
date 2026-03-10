package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.Employee;
import com.curable.service.dto.EmployeeDTO;

@Mapper(componentModel = "spring", uses = {EmployeeRoleMasterMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {
	@Mapping(source = "employeeRoleMaster.id", target = "employeeRoleMasterId")
	EmployeeDTO toDto(Employee hospitalEmployee);

	@Mapping(source = "employeeRoleMasterId", target = "employeeRoleMaster")
	Employee toEntity(EmployeeDTO hospitalEmployeeDTO);

	default Employee fromId(Long id) {
		if (id == null) {
			return null;
		}
		Employee hospitalEmployee = new Employee();
		hospitalEmployee.setId(id);
		return hospitalEmployee;
	}
}