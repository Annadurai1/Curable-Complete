package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.DiseaseMasterService;
import com.curable.service.dto.DiseaseMasterDTO;

@RestController
public class DiseaseMasterController {

	@Autowired
	private DiseaseMasterService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/diseasemaster")
	public List<DiseaseMasterDTO> getAllDiseaseMasters() {
		//log.debug("REST request to get all DiseaseMaster");
		return service.getAll();
	}

	@GetMapping("/diseasemaster/{id}")
	public Optional<DiseaseMasterDTO> getDiseaseMaster(@PathVariable Long id) {
		//log.debug("REST request to get DiseaseMasters : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/diseasemaster")
	public void postDiseaseMaster(@RequestBody DiseaseMasterDTO diseasemaster) {
		//log.debug("REST request to save diseasemaster : {}", id);
		service.save(diseasemaster);
	
	}
	
}
