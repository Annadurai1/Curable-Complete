package com.curable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.CandidateHabit;

@Repository
public interface CandidateHabitRepository
		extends JpaRepository<CandidateHabit, Long>, QuerydslPredicateExecutor<CandidateHabit> {

	List<CandidateHabit> findByCandidate_id(Long candidateId);
	
	void deleteByCandidate_id(Long candidateId);
}
