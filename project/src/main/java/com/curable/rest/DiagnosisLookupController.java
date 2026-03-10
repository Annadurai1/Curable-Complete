package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.DiagnosisLookupService;
import com.curable.service.dto.DiagnosisLookupDTO;

@RestController
public class DiagnosisLookupController {

	@Autowired
	private DiagnosisLookupService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/diagnosislookup")
	public List<DiagnosisLookupDTO> getAllDiagnosisLookups() {
		//log.debug("REST request to get all DiagnosisLookup");
		return service.getAll();
	}

	@GetMapping("/diagnosislookup/{id}")
	public Optional<DiagnosisLookupDTO> getDiagnosisLookup(@PathVariable Long id) {
		//log.debug("REST request to get DiagnosisLookups : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/diagnosislookup")
	public void postDiagnosisLookup(@RequestBody DiagnosisLookupDTO diagnosislookup) {
		//log.debug("REST request to save diagnosislookup : {}", id);
		service.save(diagnosislookup);
	
	}
	
}
