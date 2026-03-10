package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.curable.domain.CandidateHabit;
import com.curable.service.dto.CandidateHabitDTO;

@Mapper(componentModel = "spring", uses = { CandidateMapper.class })
public interface CandidateHabitMapper extends EntityMapper<CandidateHabitDTO, CandidateHabit> {

	@Mapping(source = "candidate.id", target = "candidateId")
	CandidateHabitDTO toDto(CandidateHabit candidateHabit);

	@Mapping(source = "candidateId", target = "candidate")
	CandidateHabit toEntity(CandidateHabitDTO candidateHabitDTO);

	default CandidateHabit fromId(Long id) {
		if (id == null) {
			return null;
		}
		CandidateHabit candidateHabit = new CandidateHabit();
		candidateHabit.setId(id);
		return candidateHabit;
	}
}
