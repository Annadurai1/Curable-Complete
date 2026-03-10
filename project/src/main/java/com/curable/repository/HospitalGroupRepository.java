package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.HospitalGroup;

@Repository
public interface HospitalGroupRepository extends JpaRepository<HospitalGroup, Long>, QuerydslPredicateExecutor<HospitalGroup> {

}
