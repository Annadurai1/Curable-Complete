package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.DistrictMaster;

@Repository
public interface DistrictMasterRepository extends JpaRepository<DistrictMaster, Long>, QuerydslPredicateExecutor<DistrictMaster> {

	List<DistrictMaster> findByStateMaster_idAndIsRecordDeletedFalse(Long stateMasterId);
}
