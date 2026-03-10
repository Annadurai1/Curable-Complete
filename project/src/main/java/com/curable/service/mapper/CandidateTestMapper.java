package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.CandidateTest;
import com.curable.service.dto.CandidateTestDTO;

@Mapper(componentModel = "spring", uses = {CandidateMapper.class,DiseaseTestMasterMapper.class,DiagnosisLookupMapper.class,TreatmentLookupMapper.class})
public interface CandidateTestMapper extends EntityMapper<CandidateTestDTO, CandidateTest> {
	@Mapping(source = "candidate.id", target = "candidateId")
	@Mapping(source = "diseaseTestMaster.id", target = "diseaseTestMasterId")
	CandidateTestDTO toDto(CandidateTest candidateTest);

	@Mapping(source = "candidateId", target = "candidate")
	@Mapping(source = "diseaseTestMasterId", target = "diseaseTestMaster")
	CandidateTest toEntity(CandidateTestDTO candidateTestDTO);

	default CandidateTest fromId(Long id) {
		if (id == null) {
			return null;
		}
		CandidateTest candidateTest = new CandidateTest();
		candidateTest.setId(id);
		return candidateTest;
	}
}