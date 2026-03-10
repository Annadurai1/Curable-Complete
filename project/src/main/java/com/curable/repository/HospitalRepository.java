package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>, QuerydslPredicateExecutor<Hospital> {

	List<Hospital> findByHospitalGroup_IdAndIsRecordDeletedFalse(Long hospitalGroupId);
}
