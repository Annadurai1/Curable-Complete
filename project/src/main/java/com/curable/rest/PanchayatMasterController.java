package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.PanchayatMasterService;
import com.curable.service.dto.PanchayatMasterDTO;

@RestController
public class PanchayatMasterController {

	@Autowired
	private PanchayatMasterService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/panchayatmaster")
	public List<PanchayatMasterDTO> getAllPanchayatMasters() {
		//log.debug("REST request to get all PanchayatMaster");
		return service.getAll();
	}

	@GetMapping("/panchayatmaster/{id}")
	public Optional<PanchayatMasterDTO> getPanchayatMaster(@PathVariable Long id) {
		//log.debug("REST request to get PanchayatMasters : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/panchayatmaster")
	public void postPanchayatMaster(@RequestBody PanchayatMasterDTO panchayatmaster) {
		//log.debug("REST request to save panchayatmaster : {}", id);
		service.save(panchayatmaster);
	
	}
	@GetMapping("/panchayatmaster/taluqmaster/{id}")
	public List<PanchayatMasterDTO> getPanchayatMasterTaluqMaster(@PathVariable Long id) {
		//log.debug("REST request to get PanchayatMasters : {}", id);
		return service.findByTaluqMaster_IdAndIsRecordDeletedFalse(id);
	}
	
}
