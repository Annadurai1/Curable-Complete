package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.TaluqMasterMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.TaluqMasterRepository;
import com.curable.service.dto.TaluqMasterDTO;
import com.curable.domain.TaluqMaster;

@Service
@Transactional
public class TaluqMasterService {

	@Autowired
	private TaluqMasterRepository taluqMasterRepository;

	//@Autowired
	private final TaluqMasterMapper taluqMasterMapper;

	public TaluqMasterService(TaluqMasterMapper taluqMasterMapper) {
		this.taluqMasterMapper = taluqMasterMapper;
	}

	public List<TaluqMasterDTO> getAll() {
		return taluqMasterRepository.findAll().stream().map(taluqMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<TaluqMasterDTO> findBy(Long id) {
		//log.debug("Request to get TaluqMaster : {}", id);
		return taluqMasterRepository.findById(id).map(taluqMasterMapper::toDto);
	}

	public TaluqMasterDTO save(TaluqMasterDTO taluqMasterDTO) {
		//log.debug("Request to save TaluqMaster : {}", taluqMasterDTO);
		TaluqMaster taluqMaster = taluqMasterMapper.toEntity(taluqMasterDTO);
		taluqMaster = taluqMasterRepository.save(taluqMaster);
		TaluqMasterDTO result = taluqMasterMapper.toDto(taluqMaster);
		return result;
	}

	public List<TaluqMasterDTO> findByDistrictMaster_IdAndIsRecordDeletedFalse(Long districtMasterId) {
		//log.debug("Request to get TaluqMaster : {}", districtMasterId);
		return taluqMasterRepository.findByDistrictMaster_IdAndIsRecordDeletedFalseOrderByNameAsc(districtMasterId).stream().map(taluqMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		taluqMasterRepository.deleteById(id);
	}
}
