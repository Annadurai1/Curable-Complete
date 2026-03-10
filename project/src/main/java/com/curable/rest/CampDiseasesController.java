package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.CampDiseasesService;
import com.curable.service.dto.CampDiseasesDTO;

@RestController
public class CampDiseasesController {

	@Autowired
	private CampDiseasesService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/campdiseases")
	public List<CampDiseasesDTO> getAllCampDiseasess() {
		//log.debug("REST request to get all CampDiseases");
		return service.getAll();
	}

	@GetMapping("/campdiseases/{id}")
	public Optional<CampDiseasesDTO> getCampDiseases(@PathVariable Long id) {
		//log.debug("REST request to get CampDiseasess : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/campdiseases")
	public void postCampDiseases(@RequestBody CampDiseasesDTO campdiseases) {
		//log.debug("REST request to save campdiseases : {}", id);
		service.save(campdiseases);
	
	}
	@GetMapping("/campdiseases/camp/{id}")
	public List<CampDiseasesDTO> getCampDiseasesCamp(@PathVariable Long id) {
		//log.debug("REST request to get CampDiseasess : {}", id);
		return service.findByCamp_IdAndIsRecordDeletedFalse(id);
	}
	@GetMapping("/campdiseases/subdiseasemaster/{id}")
	public List<CampDiseasesDTO> getCampDiseasesSubDiseaseMaster(@PathVariable Long id) {
		//log.debug("REST request to get CampDiseasess : {}", id);
		return service.findBySubDiseaseMaster_IdAndIsRecordDeletedFalse(id);
	}
	
}
