package com.curable.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curable.service.CandidateMedicalDetailsService;
import com.curable.service.dto.CandidateMedicalDetailsDTO;

@RestController
public class CandidateMedicalDetailsController {

	@Autowired
	CandidateMedicalDetailsService candidateMedicalDetailsService;

	@PostMapping("/createMedicalHistory")
	public Long createMedicalHistory(@RequestBody CandidateMedicalDetailsDTO candidateMedicalDetailsDTO) {
		return candidateMedicalDetailsService.saveMedicalHistoryForCandidate(candidateMedicalDetailsDTO);
	}

}
