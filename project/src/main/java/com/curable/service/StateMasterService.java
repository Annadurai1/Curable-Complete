package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.StateMasterMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.StateMasterRepository;
import com.curable.service.dto.StateMasterDTO;
import com.curable.domain.StateMaster;

@Service
@Transactional
public class StateMasterService {

	@Autowired
	private StateMasterRepository stateMasterRepository;

	// @Autowired
	private final StateMasterMapper stateMasterMapper;

	public StateMasterService(StateMasterMapper stateMasterMapper) {
		this.stateMasterMapper = stateMasterMapper;
	}

	public List<StateMasterDTO> getAll() {
		return stateMasterRepository.findAllByIsRecordDeletedFalse().stream().map(stateMasterMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<StateMasterDTO> findBy(Long id) {
		// log.debug("Request to get StateMaster : {}", id);
		return stateMasterRepository.findById(id).map(stateMasterMapper::toDto);
	}

	public StateMasterDTO save(StateMasterDTO stateMasterDTO) {
		// log.debug("Request to save StateMaster : {}", stateMasterDTO);
		StateMaster stateMaster = stateMasterMapper.toEntity(stateMasterDTO);
		stateMaster = stateMasterRepository.save(stateMaster);
		StateMasterDTO result = stateMasterMapper.toDto(stateMaster);
		return result;
	}

	public void delete(Long id) {
		// log.debug("Request to delete : {}",id );
		stateMasterRepository.deleteById(id);
	}
}
