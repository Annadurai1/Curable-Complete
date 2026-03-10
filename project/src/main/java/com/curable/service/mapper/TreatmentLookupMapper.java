package com.curable.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.curable.domain.TreatmentLookup;
import com.curable.service.dto.TreatmentLookupDTO;

@Mapper(componentModel = "spring", uses = {})
public interface TreatmentLookupMapper extends EntityMapper<TreatmentLookupDTO, TreatmentLookup> {
	default TreatmentLookup fromId(Long id) {
		if (id == null) {
			return null;
		}
		TreatmentLookup treatmentLookup = new TreatmentLookup();
		treatmentLookup.setId(id);
		return treatmentLookup;
	}
}