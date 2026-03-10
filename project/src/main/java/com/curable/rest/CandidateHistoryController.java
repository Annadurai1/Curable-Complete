package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.curable.service.CandidateHistoryService;
import com.curable.service.dto.CandidateHistoryDTO;
import com.curable.service.dto.custom.CandidateSearchCustomDTO;

@RestController
public class CandidateHistoryController {

	@Autowired
	private CandidateHistoryService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/candidatehistory")
	public List<CandidateHistoryDTO> getAllCandidateHistorys() {
		// log.debug("REST request to get all CandidateHistory");
		return service.getAll();
	}

	@GetMapping("/candidatehistory/{id}")
	public Optional<CandidateHistoryDTO> getCandidateHistory(@PathVariable Long id) {
		// log.debug("REST request to get CandidateHistorys : {}", id);
		return service.findBy(id);
	}

	@PostMapping("/candidatehistory")
	public void postCandidateHistory(@RequestBody CandidateHistoryDTO candidatehistory) {
		// log.debug("REST request to save candidatehistory : {}", id);
		service.save(candidatehistory);

	}

	@GetMapping("/candidatehistory/candidate/{id}")
	public List<CandidateHistoryDTO> getCandidateHistoryCandidate(@PathVariable Long id) {
		// log.debug("REST request to get CandidateHistorys : {}", id);
		return service.findByCandidate_IdAndIsRecordDeletedFalse(id);
	}

	@PostMapping("/candidatehistoryForPrefil")
	public ResponseEntity<?> getCandidateDetails(@RequestBody CandidateSearchCustomDTO candidateSearchCustomDTO) {
		// log.debug("REST request to get CandidateHistorys : {}", id);
		return service.getCandidateDetails(candidateSearchCustomDTO);
	}

}
