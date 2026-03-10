package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.DiagnosisLookup;

@Repository
public interface DiagnosisLookupRepository extends JpaRepository<DiagnosisLookup, Long>, QuerydslPredicateExecutor<DiagnosisLookup> {

}
