package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.Candidate;
import com.curable.service.dto.CandidateDTO;

@Mapper(componentModel = "spring", uses = {CampMapper.class,HospitalMapper.class})
public interface CandidateMapper extends EntityMapper<CandidateDTO, Candidate> {
	@Mapping(source = "camp.id", target = "campId")
	@Mapping(source = "hospital.id", target = "hospitalId")
	CandidateDTO toDto(Candidate candidate);

	@Mapping(source = "campId", target = "camp")
	@Mapping(source = "hospitalId", target = "hospital")
	Candidate toEntity(CandidateDTO candidateDTO);

	default Candidate fromId(Long id) {
		if (id == null) {
			return null;
		}
		Candidate candidate = new Candidate();
		candidate.setId(id);
		return candidate;
	}
}