package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.TaluqMasterService;
import com.curable.service.dto.TaluqMasterDTO;

@RestController
public class TaluqMasterController {

	@Autowired
	private TaluqMasterService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/taluqmaster")
	public List<TaluqMasterDTO> getAllTaluqMasters() {
		//log.debug("REST request to get all TaluqMaster");
		return service.getAll();
	}

	@GetMapping("/taluqmaster/{id}")
	public Optional<TaluqMasterDTO> getTaluqMaster(@PathVariable Long id) {
		//log.debug("REST request to get TaluqMasters : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/taluqmaster")
	public void postTaluqMaster(@RequestBody TaluqMasterDTO taluqmaster) {
		//log.debug("REST request to save taluqmaster : {}", id);
		service.save(taluqmaster);
	
	}
	@GetMapping("/taluqmaster/districtmaster/{id}")
	public List<TaluqMasterDTO> getTaluqMasterDistrictMaster(@PathVariable Long id) {
		//log.debug("REST request to get TaluqMasters : {}", id);
		return service.findByDistrictMaster_IdAndIsRecordDeletedFalse(id);
	}
	
}
