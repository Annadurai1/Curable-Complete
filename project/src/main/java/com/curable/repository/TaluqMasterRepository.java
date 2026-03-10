package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.TaluqMaster;

@Repository
public interface TaluqMasterRepository extends JpaRepository<TaluqMaster, Long>, QuerydslPredicateExecutor<TaluqMaster> {

	@Query("SELECT p FROM TaluqMaster p WHERE p.districtMaster.id = :districtMasterId AND p.isRecordDeleted = false ORDER BY p.name ASC")
	List<TaluqMaster> findByDistrictMaster_IdAndIsRecordDeletedFalseOrderByNameAsc(Long districtMasterId);
}
