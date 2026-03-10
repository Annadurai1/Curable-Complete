package com.curable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.StateMaster;

@Repository
public interface StateMasterRepository
		extends JpaRepository<StateMaster, Long>, QuerydslPredicateExecutor<StateMaster> {
	List<StateMaster> findAllByIsRecordDeletedFalse();

}
