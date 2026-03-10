package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.EmployeeRoleMasterService;
import com.curable.service.dto.EmployeeRoleMasterDTO;

@RestController
public class EmployeeRoleMasterController {

	@Autowired
	private EmployeeRoleMasterService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/employeerolemaster")
	public List<EmployeeRoleMasterDTO> getAllEmployeeRoleMasters() {
		//log.debug("REST request to get all EmployeeRoleMaster");
		return service.getAll();
	}

	@GetMapping("/employeerolemaster/{id}")
	public Optional<EmployeeRoleMasterDTO> getEmployeeRoleMaster(@PathVariable Long id) {
		//log.debug("REST request to get EmployeeRoleMasters : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/employeerolemaster")
	public void postEmployeeRoleMaster(@RequestBody EmployeeRoleMasterDTO employeerolemaster) {
		//log.debug("REST request to save employeerolemaster : {}", id);
		service.save(employeerolemaster);
	
	}
	
}
