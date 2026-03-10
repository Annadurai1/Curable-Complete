package com.curable.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curable.repository.CandidateHabitRepository;
import com.curable.service.dto.CandidateHabitDTO;
import com.curable.service.mapper.CandidateHabitMapper;

@Service
@Transactional
public class CandidateHabitService {
	@Autowired
	CandidateHabitRepository candidateHabitRepository;

	@Autowired
	CandidateHabitMapper candidateHabitMapper;

	public void saveHabits(List<CandidateHabitDTO> candidateHabitDTOs) {
		candidateHabitRepository.deleteByCandidate_id(candidateHabitDTOs.get(0).getCandidateId());
		candidateHabitRepository.saveAll(candidateHabitMapper.toEntity(candidateHabitDTOs));

	}

}
