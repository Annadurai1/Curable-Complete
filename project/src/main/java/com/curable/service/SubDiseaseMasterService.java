package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.SubDiseaseMasterMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.SubDiseaseMasterRepository;
import com.curable.service.dto.SubDiseaseMasterDTO;
import com.curable.domain.SubDiseaseMaster;

@Service
@Transactional
public class SubDiseaseMasterService {

	@Autowired
	private SubDiseaseMasterRepository subDiseaseMasterRepository;

	//@Autowired
	private final SubDiseaseMasterMapper subDiseaseMasterMapper;

	public SubDiseaseMasterService(SubDiseaseMasterMapper subDiseaseMasterMapper) {
		this.subDiseaseMasterMapper = subDiseaseMasterMapper;
	}

	public List<SubDiseaseMasterDTO> getAll() {
		return subDiseaseMasterRepository.findAll().stream().map(subDiseaseMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<SubDiseaseMasterDTO> findBy(Long id) {
		//log.debug("Request to get SubDiseaseMaster : {}", id);
		return subDiseaseMasterRepository.findById(id).map(subDiseaseMasterMapper::toDto);
	}

	public SubDiseaseMasterDTO save(SubDiseaseMasterDTO subDiseaseMasterDTO) {
		//log.debug("Request to save SubDiseaseMaster : {}", subDiseaseMasterDTO);
		SubDiseaseMaster subDiseaseMaster = subDiseaseMasterMapper.toEntity(subDiseaseMasterDTO);
		subDiseaseMaster = subDiseaseMasterRepository.save(subDiseaseMaster);
		SubDiseaseMasterDTO result = subDiseaseMasterMapper.toDto(subDiseaseMaster);
		return result;
	}

	public List<SubDiseaseMasterDTO> findByDiseaseMaster_IdAndIsRecordDeletedFalse(Long diseaseMasterId) {
		//log.debug("Request to get SubDiseaseMaster : {}", diseaseMasterId);
		return subDiseaseMasterRepository.findByDiseaseMaster_IdAndIsRecordDeletedFalse(diseaseMasterId).stream().map(subDiseaseMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		subDiseaseMasterRepository.deleteById(id);
	}
}
