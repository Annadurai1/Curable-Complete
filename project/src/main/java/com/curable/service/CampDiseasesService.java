package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.curable.service.mapper.CampDiseasesMapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.CampDiseasesRepository;
import com.curable.service.dto.CampDiseasesDTO;
import com.curable.domain.CampDiseases;

@Service
@Transactional
public class CampDiseasesService {

	@Autowired
	private CampDiseasesRepository campDiseasesRepository;

	//@Autowired
	private final CampDiseasesMapper campDiseasesMapper;

	public CampDiseasesService(CampDiseasesMapper campDiseasesMapper) {
		this.campDiseasesMapper = campDiseasesMapper;
	}

	public List<CampDiseasesDTO> getAll() {
		return campDiseasesRepository.findAll().stream().map(campDiseasesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<CampDiseasesDTO> findBy(Long id) {
		//log.debug("Request to get CampDiseases : {}", id);
		return campDiseasesRepository.findById(id).map(campDiseasesMapper::toDto);
	}

	public CampDiseasesDTO save(CampDiseasesDTO campDiseasesDTO) {
		//log.debug("Request to save CampDiseases : {}", campDiseasesDTO);
		CampDiseases campDiseases = campDiseasesMapper.toEntity(campDiseasesDTO);
		campDiseases = campDiseasesRepository.save(campDiseases);
		CampDiseasesDTO result = campDiseasesMapper.toDto(campDiseases);
		return result;
	}

	public List<CampDiseasesDTO> findByCamp_IdAndIsRecordDeletedFalse(Long campId) {
		//log.debug("Request to get CampDiseases : {}", campId);
		return campDiseasesRepository.findByCamp_IdAndIsRecordDeletedFalse(campId).stream().map(campDiseasesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}
	public List<CampDiseasesDTO> findBySubDiseaseMaster_IdAndIsRecordDeletedFalse(Long subDiseaseMasterId) {
		//log.debug("Request to get CampDiseases : {}", subDiseaseMasterId);
		return campDiseasesRepository.findBySubDiseaseMaster_IdAndIsRecordDeletedFalse(subDiseaseMasterId).stream().map(campDiseasesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		//log.debug("Request to delete : {}",id );
		campDiseasesRepository.deleteById(id);
	}
}
