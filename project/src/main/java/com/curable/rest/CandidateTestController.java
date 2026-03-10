package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.CandidateTestService;
import com.curable.service.dto.CandidateTestDTO;

@RestController
public class CandidateTestController {

	@Autowired
	private CandidateTestService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/candidatetest")
	public List<CandidateTestDTO> getAllCandidateTests() {
		//log.debug("REST request to get all CandidateTest");
		return service.getAll();
	}

	@GetMapping("/candidatetest/{id}")
	public Optional<CandidateTestDTO> getCandidateTest(@PathVariable Long id) {
		//log.debug("REST request to get CandidateTests : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/candidatetest")
	public void postCandidateTest(@RequestBody CandidateTestDTO candidatetest) {
		//log.debug("REST request to save candidatetest : {}", id);
		service.save(candidatetest);
	
	}
	@GetMapping("/candidatetest/candidate/{id}")
	public List<CandidateTestDTO> getCandidateTestCandidate(@PathVariable Long id) {
		//log.debug("REST request to get CandidateTests : {}", id);
		return service.findByCandidate_IdAndIsRecordDeletedFalse(id);
	}
	@GetMapping("/candidatetest/diseasetestmaster/{id}")
	public List<CandidateTestDTO> getCandidateTestDiseaseTestMaster(@PathVariable Long id) {
		//log.debug("REST request to get CandidateTests : {}", id);
		return service.findByDiseaseTestMaster_IdAndIsRecordDeletedFalse(id);
	}
	@GetMapping("/candidatetest/diagnosislookup/{id}")
	public List<CandidateTestDTO> getCandidateTestDiagnosisLookup(@PathVariable Long id) {
		//log.debug("REST request to get CandidateTests : {}", id);
		return service.findByDiagnosisLookup_IdAndIsRecordDeletedFalse(id);
	}
	@GetMapping("/candidatetest/treatmentlookup/{id}")
	public List<CandidateTestDTO> getCandidateTestTreatmentLookup(@PathVariable Long id) {
		//log.debug("REST request to get CandidateTests : {}", id);
		return service.findByTreatmentLookup_IdAndIsRecordDeletedFalse(id);
	}
	
}
