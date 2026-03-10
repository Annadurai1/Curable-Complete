package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.domain.CandidateTest;
import com.curable.repository.CandidateTestRepository;
import com.curable.service.dto.CandidateTestDTO;
import com.curable.service.dto.custom.CandidateSearchCustomDTO;
import com.curable.service.mapper.CandidateTestMapper;

@Service
@Transactional
public class CandidateTestService {

	@Autowired
	private CandidateTestRepository candidateTestRepository;

	// @Autowired
	private final CandidateTestMapper candidateTestMapper;

	public CandidateTestService(CandidateTestMapper candidateTestMapper) {
		this.candidateTestMapper = candidateTestMapper;
	}

	public List<CandidateTestDTO> getAll() {
		return candidateTestRepository.findAll().stream().map(candidateTestMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<CandidateTestDTO> findBy(Long id) {
		// log.debug("Request to get CandidateTest : {}", id);
		return candidateTestRepository.findById(id).map(candidateTestMapper::toDto);
	}

	public CandidateTestDTO save(CandidateTestDTO candidateTestDTO) {
		// log.debug("Request to save CandidateTest : {}", candidateTestDTO);
		CandidateTest candidateTest = candidateTestMapper.toEntity(candidateTestDTO);
		candidateTest = candidateTestRepository.save(candidateTest);
		CandidateTestDTO result = candidateTestMapper.toDto(candidateTest);
		return result;
	}

	public List<CandidateTestDTO> findByCandidate_IdAndIsRecordDeletedFalse(Long candidateId) {
		// log.debug("Request to get CandidateTest : {}", candidateId);
		return candidateTestRepository.findByCandidate_IdAndIsRecordDeletedFalse(candidateId).stream()
				.map(candidateTestMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public List<CandidateTestDTO> findByDiseaseTestMaster_IdAndIsRecordDeletedFalse(Long diseaseTestMasterId) {
		// log.debug("Request to get CandidateTest : {}", diseaseTestMasterId);
		return candidateTestRepository.findByDiseaseTestMaster_IdAndIsRecordDeletedFalse(diseaseTestMasterId).stream()
				.map(candidateTestMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public List<CandidateTestDTO> findByDiagnosisLookup_IdAndIsRecordDeletedFalse(Long diagnosisLookupId) {
		// log.debug("Request to get CandidateTest : {}", diagnosisLookupId);
		return null;
	}

	public List<CandidateTestDTO> findByTreatmentLookup_IdAndIsRecordDeletedFalse(Long treatmentLookupId) {
		// log.debug("Request to get CandidateTest : {}", treatmentLookupId);
		return null;
	}

	public void delete(Long id) {
		// log.debug("Request to delete : {}",id );
		candidateTestRepository.deleteById(id);
	}

	public CandidateTestDTO getScreeningDetailsByType(CandidateSearchCustomDTO candidateSearchCustomDTO) {
		return candidateTestRepository.getScreeningOrClinicalData(candidateSearchCustomDTO.getCandidateId(),
				candidateSearchCustomDTO.getDiseaseTypeId());
	}
}
