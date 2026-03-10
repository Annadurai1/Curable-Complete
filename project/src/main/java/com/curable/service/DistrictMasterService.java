package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.DistrictMasterMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.DistrictMasterRepository;
import com.curable.service.dto.DistrictMasterDTO;
import com.curable.domain.DistrictMaster;

@Service
@Transactional
public class DistrictMasterService {

	@Autowired
	private DistrictMasterRepository districtMasterRepository;

	//@Autowired
	private final DistrictMasterMapper districtMasterMapper;

	public DistrictMasterService(DistrictMasterMapper districtMasterMapper) {
		this.districtMasterMapper = districtMasterMapper;
	}

	public List<DistrictMasterDTO> getAll() {
		return districtMasterRepository.findAll().stream().map(districtMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<DistrictMasterDTO> findBy(Long id) {
		//log.debug("Request to get DistrictMaster : {}", id);
		return districtMasterRepository.findById(id).map(districtMasterMapper::toDto);
	}

	public DistrictMasterDTO save(DistrictMasterDTO districtMasterDTO) {
		//log.debug("Request to save DistrictMaster : {}", districtMasterDTO);
		DistrictMaster districtMaster = districtMasterMapper.toEntity(districtMasterDTO);
		districtMaster = districtMasterRepository.save(districtMaster);
		DistrictMasterDTO result = districtMasterMapper.toDto(districtMaster);
		return result;
	}

	public List<DistrictMasterDTO> findByStateMaster_idAndIsRecordDeletedFalse(Long stateMasterId) {
		//log.debug("Request to get DistrictMaster : {}", stateMasterId);
		return districtMasterRepository.findByStateMaster_idAndIsRecordDeletedFalse(stateMasterId).stream().map(districtMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		districtMasterRepository.deleteById(id);
	}
}
