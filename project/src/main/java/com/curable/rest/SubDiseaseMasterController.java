package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.SubDiseaseMasterService;
import com.curable.service.dto.SubDiseaseMasterDTO;

@RestController
public class SubDiseaseMasterController {

	@Autowired
	private SubDiseaseMasterService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/subdiseasemaster")
	public List<SubDiseaseMasterDTO> getAllSubDiseaseMasters() {
		//log.debug("REST request to get all SubDiseaseMaster");
		return service.getAll();
	}

	@GetMapping("/subdiseasemaster/{id}")
	public Optional<SubDiseaseMasterDTO> getSubDiseaseMaster(@PathVariable Long id) {
		//log.debug("REST request to get SubDiseaseMasters : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/subdiseasemaster")
	public void postSubDiseaseMaster(@RequestBody SubDiseaseMasterDTO subdiseasemaster) {
		//log.debug("REST request to save subdiseasemaster : {}", id);
		service.save(subdiseasemaster);
	
	}
	@GetMapping("/subdiseasemaster/diseasemaster/{id}")
	public List<SubDiseaseMasterDTO> getSubDiseaseMasterDiseaseMaster(@PathVariable Long id) {
		//log.debug("REST request to get SubDiseaseMasters : {}", id);
		return service.findByDiseaseMaster_IdAndIsRecordDeletedFalse(id);
	}
	
}
