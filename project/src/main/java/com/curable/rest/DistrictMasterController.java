package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.DistrictMasterService;
import com.curable.service.dto.DistrictMasterDTO;

@RestController
public class DistrictMasterController {

	@Autowired
	private DistrictMasterService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/districtmaster")
	public List<DistrictMasterDTO> getAllDistrictMasters() {
		//log.debug("REST request to get all DistrictMaster");
		return service.getAll();
	}

	@GetMapping("/districtmaster/{id}")
	public Optional<DistrictMasterDTO> getDistrictMaster(@PathVariable Long id) {
		//log.debug("REST request to get DistrictMasters : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/districtmaster")
	public void postDistrictMaster(@RequestBody DistrictMasterDTO districtmaster) {
		//log.debug("REST request to save districtmaster : {}", id);
		service.save(districtmaster);
	
	}
	@GetMapping("/districtmaster/statemaster/{id}")
	public List<DistrictMasterDTO> getDistrictMasterStateMaster(@PathVariable Long id) {
		//log.debug("REST request to get DistrictMasters : {}", id);
		return service.findByStateMaster_idAndIsRecordDeletedFalse(id);
	}
	
}
