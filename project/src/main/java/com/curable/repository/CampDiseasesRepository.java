package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.CampDiseases;

@Repository
public interface CampDiseasesRepository extends JpaRepository<CampDiseases, Long>, QuerydslPredicateExecutor<CampDiseases> {

	List<CampDiseases> findByCamp_IdAndIsRecordDeletedFalse(Long campId);
	List<CampDiseases> findBySubDiseaseMaster_IdAndIsRecordDeletedFalse(Long subDiseaseMasterId);
}
