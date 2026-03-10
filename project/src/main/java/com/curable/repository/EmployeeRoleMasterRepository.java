package com.curable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.EmployeeRoleMaster;

@Repository
public interface EmployeeRoleMasterRepository
		extends JpaRepository<EmployeeRoleMaster, Long>, QuerydslPredicateExecutor<EmployeeRoleMaster> {
	List<EmployeeRoleMaster> findByIsRecordDeletedFalse();

}
