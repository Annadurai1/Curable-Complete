package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.TreatmentLookup;

@Repository
public interface TreatmentLookupRepository extends JpaRepository<TreatmentLookup, Long>, QuerydslPredicateExecutor<TreatmentLookup> {

}
