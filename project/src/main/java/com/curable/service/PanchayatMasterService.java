package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.PanchayatMasterMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.PanchayatMasterRepository;
import com.curable.service.dto.PanchayatMasterDTO;
import com.curable.domain.PanchayatMaster;

@Service
@Transactional
public class PanchayatMasterService {

	@Autowired
	private PanchayatMasterRepository panchayatMasterRepository;

	//@Autowired
	private final PanchayatMasterMapper panchayatMasterMapper;

	public PanchayatMasterService(PanchayatMasterMapper panchayatMasterMapper) {
		this.panchayatMasterMapper = panchayatMasterMapper;
	}

	public List<PanchayatMasterDTO> getAll() {
		return panchayatMasterRepository.findAll().stream().map(panchayatMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<PanchayatMasterDTO> findBy(Long id) {
		//log.debug("Request to get PanchayatMaster : {}", id);
		return panchayatMasterRepository.findById(id).map(panchayatMasterMapper::toDto);
	}

	public PanchayatMasterDTO save(PanchayatMasterDTO panchayatMasterDTO) {
		//log.debug("Request to save PanchayatMaster : {}", panchayatMasterDTO);
		PanchayatMaster panchayatMaster = panchayatMasterMapper.toEntity(panchayatMasterDTO);
		panchayatMaster = panchayatMasterRepository.save(panchayatMaster);
		PanchayatMasterDTO result = panchayatMasterMapper.toDto(panchayatMaster);
		return result;
	}

	public List<PanchayatMasterDTO> findByTaluqMaster_IdAndIsRecordDeletedFalse(Long taluqMasterId) {
		//log.debug("Request to get PanchayatMaster : {}", taluqMasterId);
		return panchayatMasterRepository.findByTaluqMaster_IdAndIsRecordDeletedFalseOrderByNameAsc(taluqMasterId).stream().map(panchayatMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		panchayatMasterRepository.deleteById(id);
	}
}
