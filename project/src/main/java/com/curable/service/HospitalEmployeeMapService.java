package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.HospitalEmployeeMapMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.HospitalEmployeeMapRepository;
import com.curable.service.dto.HospitalEmployeeMapDTO;
import com.curable.domain.HospitalEmployeeMap;

@Service
@Transactional
public class HospitalEmployeeMapService {

	@Autowired
	private HospitalEmployeeMapRepository hospitalEmployeeMapRepository;

	//@Autowired
	private final HospitalEmployeeMapMapper hospitalEmployeeMapMapper;

	public HospitalEmployeeMapService(HospitalEmployeeMapMapper hospitalEmployeeMapMapper) {
		this.hospitalEmployeeMapMapper = hospitalEmployeeMapMapper;
	}

	public List<HospitalEmployeeMapDTO> getAll() {
		return hospitalEmployeeMapRepository.findAll().stream().map(hospitalEmployeeMapMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<HospitalEmployeeMapDTO> findBy(Long id) {
		//log.debug("Request to get HospitalEmployeeMap : {}", id);
		return hospitalEmployeeMapRepository.findById(id).map(hospitalEmployeeMapMapper::toDto);
	}

	public HospitalEmployeeMapDTO save(HospitalEmployeeMapDTO hospitalEmployeeMapDTO) {
		//log.debug("Request to save HospitalEmployeeMap : {}", hospitalEmployeeMapDTO);
		HospitalEmployeeMap hospitalEmployeeMap = hospitalEmployeeMapMapper.toEntity(hospitalEmployeeMapDTO);
		hospitalEmployeeMap = hospitalEmployeeMapRepository.save(hospitalEmployeeMap);
		HospitalEmployeeMapDTO result = hospitalEmployeeMapMapper.toDto(hospitalEmployeeMap);
		return result;
	}

	public List<HospitalEmployeeMapDTO> findByHospitalEmployee_IdAndIsRecordDeletedFalse(Long hospitalEmployeeId) {
		//log.debug("Request to get HospitalEmployeeMap : {}", hospitalEmployeeId);
		return hospitalEmployeeMapRepository.findByEmployee_IdAndIsRecordDeletedFalse(hospitalEmployeeId).stream().map(hospitalEmployeeMapMapper::toDto).collect(Collectors.toCollection(LinkedList::new));}
	public List<HospitalEmployeeMapDTO> findByHospital_IdAndIsRecordDeletedFalse(Long hospitalId) {
		//log.debug("Request to get HospitalEmployeeMap : {}", hospitalId);
		return hospitalEmployeeMapRepository.findByHospital_IdAndIsRecordDeletedFalse(hospitalId).stream().map(hospitalEmployeeMapMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		hospitalEmployeeMapRepository.deleteById(id);
	}
}
