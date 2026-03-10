package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.HospitalEmployeeMapService;
import com.curable.service.dto.HospitalEmployeeMapDTO;

@RestController
public class HospitalEmployeeMapController {

	@Autowired
	private HospitalEmployeeMapService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/hospitalemployeemap")
	public List<HospitalEmployeeMapDTO> getAllHospitalEmployeeMaps() {
		//log.debug("REST request to get all HospitalEmployeeMap");
		return service.getAll();
	}

	@GetMapping("/hospitalemployeemap/{id}")
	public Optional<HospitalEmployeeMapDTO> getHospitalEmployeeMap(@PathVariable Long id) {
		//log.debug("REST request to get HospitalEmployeeMaps : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/hospitalemployeemap")
	public void postHospitalEmployeeMap(@RequestBody HospitalEmployeeMapDTO hospitalemployeemap) {
		//log.debug("REST request to save hospitalemployeemap : {}", id);
		service.save(hospitalemployeemap);
	
	}
	@GetMapping("/hospitalemployeemap/hospitalemployee/{id}")
	public List<HospitalEmployeeMapDTO> getHospitalEmployeeMapHospitalEmployee(@PathVariable Long id) {
		//log.debug("REST request to get HospitalEmployeeMaps : {}", id);
		return service.findByHospitalEmployee_IdAndIsRecordDeletedFalse(id);
	}
	@GetMapping("/hospitalemployeemap/hospital/{id}")
	public List<HospitalEmployeeMapDTO> getHospitalEmployeeMapHospital(@PathVariable Long id) {
		//log.debug("REST request to get HospitalEmployeeMaps : {}", id);
		return service.findByHospital_IdAndIsRecordDeletedFalse(id);
	}
	
}
