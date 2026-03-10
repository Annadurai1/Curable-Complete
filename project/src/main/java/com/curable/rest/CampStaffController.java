package com.curable.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curable.service.CampStaffService;
import com.curable.service.dto.CampStaffDTO;
import com.curable.service.dto.EmployeeDTO;

@RestController
public class CampStaffController {

	@Autowired
	private CampStaffService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/campstaff")
	public List<CampStaffDTO> getAllCampStaffs() {
		// log.debug("REST request to get all CampStaff");
		return service.getAll();
	}

	@GetMapping("/campstaff/{id}")
	public Optional<CampStaffDTO> getCampStaff(@PathVariable Long id) {
		// log.debug("REST request to get CampStaffs : {}", id);
		return service.findBy(id);
	}

	@PostMapping("/campstaff")
	public void postCampStaff(@RequestBody CampStaffDTO campstaff) {
		// log.debug("REST request to save campstaff : {}", id);
		service.save(campstaff);

	}

	@GetMapping("/campstaffdetails/{campId}")
	public Map<String, List<EmployeeDTO>> getEmployeeswithRoles(@PathVariable Long campId) {
		return service.getEmployeeswithRoles(campId);
	}

	@GetMapping("/hospitalstaffdetails/{hospitalId}")
	public Map<String, List<EmployeeDTO>> getEmployeeswithHospital(@PathVariable Long hospitalId) {
		return service.getStaffDetailsByUserId(hospitalId);
	}

}
