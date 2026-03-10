package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.HospitalService;
import com.curable.service.dto.HospitalDTO;

@RestController
public class HospitalController {

	@Autowired
	private HospitalService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/hospital")
	public List<HospitalDTO> getAllHospitals() {
		//log.debug("REST request to get all Hospital");
		return service.getAll();
	}

	@GetMapping("/hospital/{id}")
	public Optional<HospitalDTO> getHospital(@PathVariable Long id) {
		//log.debug("REST request to get Hospitals : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/hospital")
	public void postHospital(@RequestBody HospitalDTO hospital) {
		//log.debug("REST request to save hospital : {}", id);
		service.save(hospital);
	
	}
	@GetMapping("/hospital/hospitalgroup/{id}")
	public List<HospitalDTO> getHospitalHospitalGroup(@PathVariable Long id) {
		//log.debug("REST request to get Hospitals : {}", id);
		return service.findByHospitalGroup_IdAndIsRecordDeletedFalse(id);
	}
	
}
