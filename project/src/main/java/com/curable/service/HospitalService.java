package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.HospitalMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.HospitalRepository;
import com.curable.service.dto.HospitalDTO;
import com.curable.domain.Hospital;

@Service
@Transactional
public class HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	//@Autowired
	private final HospitalMapper hospitalMapper;

	public HospitalService(HospitalMapper hospitalMapper) {
		this.hospitalMapper = hospitalMapper;
	}

	public List<HospitalDTO> getAll() {
		return hospitalRepository.findAll().stream().map(hospitalMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<HospitalDTO> findBy(Long id) {
		//log.debug("Request to get Hospital : {}", id);
		return hospitalRepository.findById(id).map(hospitalMapper::toDto);
	}

	public HospitalDTO save(HospitalDTO hospitalDTO) {
		//log.debug("Request to save Hospital : {}", hospitalDTO);
		Hospital hospital = hospitalMapper.toEntity(hospitalDTO);
		hospital = hospitalRepository.save(hospital);
		HospitalDTO result = hospitalMapper.toDto(hospital);
		return result;
	}

	public List<HospitalDTO> findByHospitalGroup_IdAndIsRecordDeletedFalse(Long hospitalGroupId) {
		//log.debug("Request to get Hospital : {}", hospitalGroupId);
		return hospitalRepository.findByHospitalGroup_IdAndIsRecordDeletedFalse(hospitalGroupId).stream().map(hospitalMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		hospitalRepository.deleteById(id);
	}
}
