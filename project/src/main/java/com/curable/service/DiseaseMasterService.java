package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.DiseaseMasterMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.DiseaseMasterRepository;
import com.curable.service.dto.DiseaseMasterDTO;
import com.curable.domain.DiseaseMaster;

@Service
@Transactional
public class DiseaseMasterService {

	@Autowired
	private DiseaseMasterRepository diseaseMasterRepository;

	//@Autowired
	private final DiseaseMasterMapper diseaseMasterMapper;

	public DiseaseMasterService(DiseaseMasterMapper diseaseMasterMapper) {
		this.diseaseMasterMapper = diseaseMasterMapper;
	}

	public List<DiseaseMasterDTO> getAll() {
		return diseaseMasterRepository.findAll().stream().map(diseaseMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<DiseaseMasterDTO> findBy(Long id) {
		//log.debug("Request to get DiseaseMaster : {}", id);
		return diseaseMasterRepository.findById(id).map(diseaseMasterMapper::toDto);
	}

	public DiseaseMasterDTO save(DiseaseMasterDTO diseaseMasterDTO) {
		//log.debug("Request to save DiseaseMaster : {}", diseaseMasterDTO);
		DiseaseMaster diseaseMaster = diseaseMasterMapper.toEntity(diseaseMasterDTO);
		diseaseMaster = diseaseMasterRepository.save(diseaseMaster);
		DiseaseMasterDTO result = diseaseMasterMapper.toDto(diseaseMaster);
		return result;
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		diseaseMasterRepository.deleteById(id);
	}
}
