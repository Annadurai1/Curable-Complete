package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.domain.Employee;
import com.curable.repository.EmployeeRepository;
import com.curable.service.dto.EmployeeDTO;
import com.curable.service.mapper.EmployeeMapper;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository hospitalEmployeeRepository;

	// @Autowired
	private final EmployeeMapper hospitalEmployeeMapper;

	@Autowired
	KeycloakService keycloakService;

	public EmployeeService(EmployeeMapper hospitalEmployeeMapper) {
		this.hospitalEmployeeMapper = hospitalEmployeeMapper;
	}

	public List<EmployeeDTO> getAll() {
		return hospitalEmployeeRepository.findAll().stream().map(hospitalEmployeeMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<EmployeeDTO> findBy(Long id) {
		// log.debug("Request to get HospitalEmployee : {}", id);
		return hospitalEmployeeRepository.findById(id).map(hospitalEmployeeMapper::toDto);
	}

	public EmployeeDTO save(EmployeeDTO hospitalEmployeeDTO) {

		if (hospitalEmployeeDTO.getId() != null) {
			Optional<Employee> emp = hospitalEmployeeRepository.findById(hospitalEmployeeDTO.getId());
			if (emp.isPresent()) {
				Employee user = emp.get();
				user.setName(hospitalEmployeeDTO.getName());
				user.setPhoneNo(hospitalEmployeeDTO.getPhoneNo());
				user.setGender(hospitalEmployeeDTO.getGender());
				if (hospitalEmployeeDTO.getPassword() != null && !hospitalEmployeeDTO.getPassword().isEmpty()) {
					keycloakService.updatePassword(hospitalEmployeeDTO.getKeycloakUserId(),
							hospitalEmployeeDTO.getPassword());
				}
				return hospitalEmployeeDTO;
			}
		} else {
			// log.debug("Request to save HospitalEmployee : {}", hospitalEmployeeDTO);
			Employee hospitalEmployee = hospitalEmployeeMapper.toEntity(hospitalEmployeeDTO);
			hospitalEmployee = hospitalEmployeeRepository.save(hospitalEmployee);
			EmployeeDTO result = hospitalEmployeeMapper.toDto(hospitalEmployee);

			return result;
		}
		return hospitalEmployeeDTO;
	}

	public List<EmployeeDTO> findByEmployeeRoleMaster_IdAndIsRecordDeletedFalse(Long employeeRoleMasterId) {
		// log.debug("Request to get HospitalEmployee : {}", employeeRoleMasterId);
		return hospitalEmployeeRepository.findByEmployeeRoleMaster_IdAndIsRecordDeletedFalse(employeeRoleMasterId)
				.stream().map(hospitalEmployeeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		// log.debug("Request to delete : {}",id );
		hospitalEmployeeRepository.deleteById(id);
	}
}
