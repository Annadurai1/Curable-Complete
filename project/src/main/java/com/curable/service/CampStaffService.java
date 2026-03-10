package com.curable.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.domain.CampStaff;
import com.curable.repository.CampStaffRepository;
import com.curable.repository.HospitalEmployeeMapRepository;
import com.curable.service.dto.CampStaffDTO;
import com.curable.service.dto.EmployeeDTO;
import com.curable.service.mapper.CampStaffMapper;

@Service
@Transactional
public class CampStaffService {

	@Autowired
	private CampStaffRepository campStaffRepository;

	// @Autowired
	private final CampStaffMapper campStaffMapper;

	@Autowired
	private HospitalEmployeeMapRepository employeeMapRepository;

	public CampStaffService(CampStaffMapper campStaffMapper) {
		this.campStaffMapper = campStaffMapper;
	}

	public List<CampStaffDTO> getAll() {
		return campStaffRepository.findAll().stream().map(campStaffMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<CampStaffDTO> findBy(Long id) {
		// log.debug("Request to get CampStaff : {}", id);
		return campStaffRepository.findById(id).map(campStaffMapper::toDto);
	}

	public CampStaffDTO save(CampStaffDTO campStaffDTO) {
		// log.debug("Request to save CampStaff : {}", campStaffDTO);
		CampStaff campStaff = campStaffMapper.toEntity(campStaffDTO);
		campStaff = campStaffRepository.save(campStaff);
		CampStaffDTO result = campStaffMapper.toDto(campStaff);
		return result;
	}

	public Map<String, List<EmployeeDTO>> getEmployeeswithRoles(Long campId) {
	    Map<String, List<EmployeeDTO>> campemployees = new HashMap<>();

	    try {
	        campemployees = campStaffRepository.getStaffDetailsByCampId(campId).stream()
	            .collect(Collectors.groupingBy(EmployeeDTO::getRole))
	            .entrySet().stream()
	            // sort each group (A–Z by name)
	            .collect(Collectors.toMap(
	                Map.Entry::getKey,
	                e -> e.getValue().stream()
	                        .sorted(Comparator.comparing(EmployeeDTO::getName, String.CASE_INSENSITIVE_ORDER))
	                        .collect(Collectors.toList())
	            ));

	        return campemployees;
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        campemployees = null;
	    }
	}



	public Map<String, List<EmployeeDTO>> getStaffDetailsByUserId(Long hospitalId) {
		Map<String, List<EmployeeDTO>> hospotalEmployees = new HashMap<String, List<EmployeeDTO>>();
		try {
			hospotalEmployees =
	                employeeMapRepository.getStaffDetailsByHospitalId(hospitalId).stream()
	                    .collect(Collectors.groupingBy(EmployeeDTO::getRole))
	                    .entrySet().stream()
	                    .collect(Collectors.toMap(
	                        Map.Entry::getKey,
	                        e -> e.getValue().stream()
	                                .sorted(Comparator.comparing(
	                                    emp -> emp.getName().trim().toLowerCase()
	                                ))
	                                .collect(Collectors.toList())
	                    ));

			return hospotalEmployees;
		} catch (Exception e) {
			throw e;
		} finally {
			hospotalEmployees = null;
		}
	}

	public void delete(Long id) {
		// log.debug("Request to delete : {}",id );
		campStaffRepository.deleteById(id);
	}
}
