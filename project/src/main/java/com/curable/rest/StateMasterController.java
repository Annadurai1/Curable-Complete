package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.StateMasterService;
import com.curable.service.dto.StateMasterDTO;

@RestController
public class StateMasterController {

	@Autowired
	private StateMasterService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/statemaster")
	public List<StateMasterDTO> getAllStateMasters() {
		//log.debug("REST request to get all StateMaster");
		return service.getAll();
	}

	@GetMapping("/statemaster/{id}")
	public Optional<StateMasterDTO> getStateMaster(@PathVariable Long id) {
		//log.debug("REST request to get StateMasters : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/statemaster")
	public void postStateMaster(@RequestBody StateMasterDTO statemaster) {
		//log.debug("REST request to save statemaster : {}", id);
		service.save(statemaster);
	
	}
	
}
