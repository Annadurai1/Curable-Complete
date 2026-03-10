package com.curable.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.CandidateMedicalDetailsRepository;
import com.curable.repository.CandidateRepository;
import com.curable.service.dto.CandidateMedicalDetailsDTO;
import com.curable.service.mapper.CandidateMedicalDetailsMapper;

@Service
@Transactional
public class CandidateMedicalDetailsService {

	@Autowired
	CandidateMedicalDetailsRepository candidateMedicalDetailsRepository;

	@Autowired
	CandidateMedicalDetailsMapper candidateMedicalDetailsMapper;

	@Autowired
	CandidateRepository candidateRepository;

	public Long saveMedicalHistoryForCandidate(CandidateMedicalDetailsDTO candidateMedicalDetailsDTO) {
		if ((candidateMedicalDetailsDTO.getMedicalhistory() != null
				&& candidateMedicalDetailsDTO.getMedicalhistory().toLowerCase().contains("cancer"))
				|| (candidateMedicalDetailsDTO.getCurrentlyPregant() != null
						&& candidateMedicalDetailsDTO.getCurrentlyPregant())) {
			candidateRepository.markAsNoEvaluationNeeded(candidateMedicalDetailsDTO.getCandidateId(),
					"CurrentlyPregant");

		}

		return candidateMedicalDetailsRepository
				.save(candidateMedicalDetailsMapper.toEntity(candidateMedicalDetailsDTO)).getId();

	}

}
