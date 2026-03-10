package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.curable.domain.CandidateMedicalDetails;
import com.curable.service.dto.CandidateMedicalDetailsDTO;

@Mapper(componentModel = "spring", uses = { CandidateMapper.class })
public interface CandidateMedicalDetailsMapper
		extends EntityMapper<CandidateMedicalDetailsDTO, CandidateMedicalDetails> {

	@Mapping(source = "candidate.id", target = "candidateId")
	CandidateMedicalDetailsDTO toDto(CandidateMedicalDetails candidateMedicalDetails);

	@Mapping(source = "candidateId", target = "candidate")
	CandidateMedicalDetails toEntity(CandidateMedicalDetailsDTO candidateMedicalDetailsDTO);

	default CandidateMedicalDetails fromId(Long id) {
		if (id == null) {
			return null;
		}
		CandidateMedicalDetails candidateMedicalDetails = new CandidateMedicalDetails();
		candidateMedicalDetails.setId(id);
		return candidateMedicalDetails;
	}
}
