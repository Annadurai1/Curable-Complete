package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.DiagnosisLookupMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.DiagnosisLookupRepository;
import com.curable.service.dto.DiagnosisLookupDTO;
import com.curable.domain.DiagnosisLookup;

@Service
@Transactional
public class DiagnosisLookupService {

	@Autowired
	private DiagnosisLookupRepository diagnosisLookupRepository;

	//@Autowired
	private final DiagnosisLookupMapper diagnosisLookupMapper;

	public DiagnosisLookupService(DiagnosisLookupMapper diagnosisLookupMapper) {
		this.diagnosisLookupMapper = diagnosisLookupMapper;
	}

	public List<DiagnosisLookupDTO> getAll() {
		return diagnosisLookupRepository.findAll().stream().map(diagnosisLookupMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<DiagnosisLookupDTO> findBy(Long id) {
		//log.debug("Request to get DiagnosisLookup : {}", id);
		return diagnosisLookupRepository.findById(id).map(diagnosisLookupMapper::toDto);
	}

	public DiagnosisLookupDTO save(DiagnosisLookupDTO diagnosisLookupDTO) {
		//log.debug("Request to save DiagnosisLookup : {}", diagnosisLookupDTO);
		DiagnosisLookup diagnosisLookup = diagnosisLookupMapper.toEntity(diagnosisLookupDTO);
		diagnosisLookup = diagnosisLookupRepository.save(diagnosisLookup);
		DiagnosisLookupDTO result = diagnosisLookupMapper.toDto(diagnosisLookup);
		return result;
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		diagnosisLookupRepository.deleteById(id);
	}
}
