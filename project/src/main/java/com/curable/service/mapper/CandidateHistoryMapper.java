package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.curable.domain.CandidateHistory;
import com.curable.service.dto.CandidateHistoryDTO;

@Mapper(componentModel = "spring", uses = { CandidateMapper.class, DiseaseTestMasterMapper.class })
public interface CandidateHistoryMapper extends EntityMapper<CandidateHistoryDTO, CandidateHistory> {
	@Mapping(source = "candidate.id", target = "candidateId")
	@Mapping(source = "diseaseTestMaster.id", target = "diseaseTestMasterId")
	CandidateHistoryDTO toDto(CandidateHistory candidateHistory);

	@Mapping(source = "candidateId", target = "candidate")
	@Mapping(source = "diseaseTestMasterId", target = "diseaseTestMaster")
	CandidateHistory toEntity(CandidateHistoryDTO candidateHistoryDTO);

	default CandidateHistory fromId(Long id) {
		if (id == null) {
			return null;
		}
		CandidateHistory candidateHistory = new CandidateHistory();
		candidateHistory.setId(id);
		return candidateHistory;
	}
}