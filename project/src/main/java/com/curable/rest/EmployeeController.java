package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.EmployeeService;
import com.curable.service.dto.EmployeeDTO;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/hospitalemployee")
	public List<EmployeeDTO> getAllHospitalEmployees() {
		//log.debug("REST request to get all HospitalEmployee");
		return service.getAll();
	}

	@GetMapping("/hospitalemployee/{id}")
	public Optional<EmployeeDTO> getHospitalEmployee(@PathVariable Long id) {
		//log.debug("REST request to get HospitalEmployees : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/hospitalemployee")
	public void postHospitalEmployee(@RequestBody EmployeeDTO hospitalemployee) {
		//log.debug("REST request to save hospitalemployee : {}", id);
		service.save(hospitalemployee);
	
	}
	@GetMapping("/hospitalemployee/employeerolemaster/{id}")
	public List<EmployeeDTO> getHospitalEmployeeEmployeeRoleMaster(@PathVariable Long id) {
		//log.debug("REST request to get HospitalEmployees : {}", id);
		return service.findByEmployeeRoleMaster_IdAndIsRecordDeletedFalse(id);
	}
	
}
