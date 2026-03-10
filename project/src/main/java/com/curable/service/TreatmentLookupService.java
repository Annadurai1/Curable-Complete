package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.TreatmentLookupMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.TreatmentLookupRepository;
import com.curable.service.dto.TreatmentLookupDTO;
import com.curable.domain.TreatmentLookup;

@Service
@Transactional
public class TreatmentLookupService {

	@Autowired
	private TreatmentLookupRepository treatmentLookupRepository;

	//@Autowired
	private final TreatmentLookupMapper treatmentLookupMapper;

	public TreatmentLookupService(TreatmentLookupMapper treatmentLookupMapper) {
		this.treatmentLookupMapper = treatmentLookupMapper;
	}

	public List<TreatmentLookupDTO> getAll() {
		return treatmentLookupRepository.findAll().stream().map(treatmentLookupMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<TreatmentLookupDTO> findBy(Long id) {
		//log.debug("Request to get TreatmentLookup : {}", id);
		return treatmentLookupRepository.findById(id).map(treatmentLookupMapper::toDto);
	}

	public TreatmentLookupDTO save(TreatmentLookupDTO treatmentLookupDTO) {
		//log.debug("Request to save TreatmentLookup : {}", treatmentLookupDTO);
		TreatmentLookup treatmentLookup = treatmentLookupMapper.toEntity(treatmentLookupDTO);
		treatmentLookup = treatmentLookupRepository.save(treatmentLookup);
		TreatmentLookupDTO result = treatmentLookupMapper.toDto(treatmentLookup);
		return result;
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		treatmentLookupRepository.deleteById(id);
	}
}
