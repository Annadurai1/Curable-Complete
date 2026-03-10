package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.TreatmentLookupService;
import com.curable.service.dto.TreatmentLookupDTO;

@RestController
public class TreatmentLookupController {

	@Autowired
	private TreatmentLookupService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/treatmentlookup")
	public List<TreatmentLookupDTO> getAllTreatmentLookups() {
		//log.debug("REST request to get all TreatmentLookup");
		return service.getAll();
	}

	@GetMapping("/treatmentlookup/{id}")
	public Optional<TreatmentLookupDTO> getTreatmentLookup(@PathVariable Long id) {
		//log.debug("REST request to get TreatmentLookups : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/treatmentlookup")
	public void postTreatmentLookup(@RequestBody TreatmentLookupDTO treatmentlookup) {
		//log.debug("REST request to save treatmentlookup : {}", id);
		service.save(treatmentlookup);
	
	}
	
}
