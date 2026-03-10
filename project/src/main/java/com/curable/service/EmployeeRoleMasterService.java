package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.EmployeeRoleMasterMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.EmployeeRoleMasterRepository;
import com.curable.service.dto.EmployeeRoleMasterDTO;
import com.curable.domain.EmployeeRoleMaster;

@Service
@Transactional
public class EmployeeRoleMasterService {

	@Autowired
	private EmployeeRoleMasterRepository employeeRoleMasterRepository;

	// @Autowired
	private final EmployeeRoleMasterMapper employeeRoleMasterMapper;

	public EmployeeRoleMasterService(EmployeeRoleMasterMapper employeeRoleMasterMapper) {
		this.employeeRoleMasterMapper = employeeRoleMasterMapper;
	}

	public List<EmployeeRoleMasterDTO> getAll() {
		return employeeRoleMasterRepository.findByIsRecordDeletedFalse().stream().map(employeeRoleMasterMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<EmployeeRoleMasterDTO> findBy(Long id) {
		// log.debug("Request to get EmployeeRoleMaster : {}", id);
		return employeeRoleMasterRepository.findById(id).map(employeeRoleMasterMapper::toDto);
	}

	public EmployeeRoleMasterDTO save(EmployeeRoleMasterDTO employeeRoleMasterDTO) {
		// log.debug("Request to save EmployeeRoleMaster : {}", employeeRoleMasterDTO);
		EmployeeRoleMaster employeeRoleMaster = employeeRoleMasterMapper.toEntity(employeeRoleMasterDTO);
		employeeRoleMaster = employeeRoleMasterRepository.save(employeeRoleMaster);
		EmployeeRoleMasterDTO result = employeeRoleMasterMapper.toDto(employeeRoleMaster);
		return result;
	}

	public void delete(Long id) {
		// log.debug("Request to delete : {}",id );
		employeeRoleMasterRepository.deleteById(id);
	}
}
