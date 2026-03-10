package com.curable.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.domain.DiseaseTestMaster;
import com.curable.domain.enums.Gender;
import com.curable.repository.DiseaseTestMasterRepository;
import com.curable.service.dto.DiseaseTestMasterDTO;
import com.curable.service.mapper.DiseaseTestMasterMapper;

@Service
@Transactional
public class DiseaseTestMasterService {

	@Autowired
	private DiseaseTestMasterRepository diseaseTestMasterRepository;

	// @Autowired
	private final DiseaseTestMasterMapper diseaseTestMasterMapper;

	public DiseaseTestMasterService(DiseaseTestMasterMapper diseaseTestMasterMapper) {
		this.diseaseTestMasterMapper = diseaseTestMasterMapper;
	}

	public List<DiseaseTestMasterDTO> getAll() {
		return diseaseTestMasterRepository.findAll().stream().map(diseaseTestMasterMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<DiseaseTestMasterDTO> findBy(Long id) {
		// log.debug("Request to get DiseaseTestMaster : {}", id);
		return diseaseTestMasterRepository.findById(id).map(diseaseTestMasterMapper::toDto);
	}

	public DiseaseTestMasterDTO save(DiseaseTestMasterDTO diseaseTestMasterDTO) {
		// log.debug("Request to save DiseaseTestMaster : {}", diseaseTestMasterDTO);
		DiseaseTestMaster diseaseTestMaster = diseaseTestMasterMapper.toEntity(diseaseTestMasterDTO);
		diseaseTestMaster = diseaseTestMasterRepository.save(diseaseTestMaster);
		DiseaseTestMasterDTO result = diseaseTestMasterMapper.toDto(diseaseTestMaster);
		return result;
	}

	public void delete(Long id) {
		// log.debug("Request to delete : {}",id );
		diseaseTestMasterRepository.deleteById(id);
	}

	public DiseaseTestMasterDTO getMetrics(String type, Long hospitalId) {
		return diseaseTestMasterMapper
				.toDto(diseaseTestMasterRepository.findByStageAndHospital_idAndIsRecordDeletedFalse(type, hospitalId));
	}

	public DiseaseTestMasterDTO getMetrics(String type, Gender gender, Long hospitalId) {
		if (gender.equals(Gender.OTHER))
			gender = (Gender.FEMALE);
		return diseaseTestMasterMapper.toDto(diseaseTestMasterRepository
				.findByNameAndGenderAndHospital_idAndIsRecordDeletedFalse(type, gender, hospitalId));
	}

	public DiseaseTestMasterDTO getMetricsById(Long id) {
		return diseaseTestMasterMapper.toDto(diseaseTestMasterRepository.findByIdAndIsRecordDeletedFalse(id));
	}

}
