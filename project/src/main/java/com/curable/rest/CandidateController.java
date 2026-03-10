package com.curable.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curable.service.CandidateService;
import com.curable.service.dto.CandidateDTO;
import com.curable.service.dto.CandidateTestDTO;
import com.curable.service.dto.SearchCustomDTO;

@RestController
public class CandidateController {

	@Autowired
	private CandidateService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/candidate")
	public List<CandidateDTO> getAllCandidates() {
		//log.debug("REST request to get all Candidate");
		return service.getAll();
	}

	@GetMapping("/candidate/{id}")
	public Optional<CandidateDTO> getCandidate(@PathVariable Long id) {
		//log.debug("REST request to get Candidates : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/candidate")
	public CandidateDTO postCandidate(@RequestBody CandidateDTO candidate) {
		//log.debug("REST request to save candidate : {}", id);
		return service.save(candidate);
	
	}
	@GetMapping("/candidate/camp/{id}")
	public List<CandidateDTO> getCandidateCamp(@PathVariable Long id) {
		//log.debug("REST request to get Candidates : {}", id);
		return service.findByCamp_IdAndIsRecordDeletedFalse(id);
	}
	
	@PostMapping("/getCandidatesList")
	public List<CandidateDTO> getCandidatesList(@RequestBody SearchCustomDTO searchCustomDTO ) {
		return service.getCandidateBasedOnType(searchCustomDTO);
	}
	
	@PostMapping("/updateCandidate")
	public boolean updateCandidate(@RequestBody CandidateTestDTO dto ) {
		return service.updateReferral(dto.getCandidateId(),dto.getReferral());
	}
	
	@PostMapping("/saveCandidate")
	public CandidateDTO saveCandidate(@RequestBody CandidateDTO candidate) {
		//log.debug("REST request to save candidate : {}", id);
		return service.save(candidate);
	
	}
	
	@GetMapping("/getHabitTypes/{habit}")
	public List<String> getHabitTypes(@PathVariable String habit) {
		//log.debug("REST request to get Candidates : {}", id);
		return service.getHabitTypes(habit);
	}
}
