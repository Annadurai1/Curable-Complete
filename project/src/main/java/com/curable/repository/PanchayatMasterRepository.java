package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.PanchayatMaster;

@Repository
public interface PanchayatMasterRepository extends JpaRepository<PanchayatMaster, Long>, QuerydslPredicateExecutor<PanchayatMaster> {

	@Query("SELECT p FROM PanchayatMaster p WHERE p.taluqMaster.id = :taluqMasterId AND p.isRecordDeleted = false ORDER BY p.name ASC")
	List<PanchayatMaster> findByTaluqMaster_IdAndIsRecordDeletedFalseOrderByNameAsc(Long taluqMasterId);
}
