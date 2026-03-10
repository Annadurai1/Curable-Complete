package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.HospitalGroupMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.HospitalGroupRepository;
import com.curable.service.dto.HospitalGroupDTO;
import com.curable.domain.HospitalGroup;

@Service
@Transactional
public class HospitalGroupService {

	@Autowired
	private HospitalGroupRepository hospitalGroupRepository;

	//@Autowired
	private final HospitalGroupMapper hospitalGroupMapper;

	public HospitalGroupService(HospitalGroupMapper hospitalGroupMapper) {
		this.hospitalGroupMapper = hospitalGroupMapper;
	}

	public List<HospitalGroupDTO> getAll() {
		return hospitalGroupRepository.findAll().stream().map(hospitalGroupMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<HospitalGroupDTO> findBy(Long id) {
		//log.debug("Request to get HospitalGroup : {}", id);
		return hospitalGroupRepository.findById(id).map(hospitalGroupMapper::toDto);
	}

	public HospitalGroupDTO save(HospitalGroupDTO hospitalGroupDTO) {
		//log.debug("Request to save HospitalGroup : {}", hospitalGroupDTO);
		HospitalGroup hospitalGroup = hospitalGroupMapper.toEntity(hospitalGroupDTO);
		hospitalGroup = hospitalGroupRepository.save(hospitalGroup);
		HospitalGroupDTO result = hospitalGroupMapper.toDto(hospitalGroup);
		return result;
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		hospitalGroupRepository.deleteById(id);
	}
}
