package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.DiagnosisLookup;
import com.curable.service.dto.DiagnosisLookupDTO;

@Mapper(componentModel = "spring", uses = {})
public interface DiagnosisLookupMapper extends EntityMapper<DiagnosisLookupDTO, DiagnosisLookup> {
	default DiagnosisLookup fromId(Long id) {
		if (id == null) {
			return null;
		}
		DiagnosisLookup diagnosisLookup = new DiagnosisLookup();
		diagnosisLookup.setId(id);
		return diagnosisLookup;
	}
}