package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.SubDiseaseMaster;

@Repository
public interface SubDiseaseMasterRepository extends JpaRepository<SubDiseaseMaster, Long>, QuerydslPredicateExecutor<SubDiseaseMaster> {

	List<SubDiseaseMaster> findByDiseaseMaster_IdAndIsRecordDeletedFalse(Long diseaseMasterId);
}
