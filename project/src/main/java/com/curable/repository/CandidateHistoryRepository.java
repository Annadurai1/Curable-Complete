package com.curable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.CandidateHistory;

@Repository
public interface CandidateHistoryRepository
		extends JpaRepository<CandidateHistory, Long>, QuerydslPredicateExecutor<CandidateHistory> {

	List<CandidateHistory> findByCandidate_IdAndIsRecordDeletedFalse(Long candidateId);

	@Query(value = "select * from candidatehistory where candidateId=:candidateId and eligibilityMetrics is not null order by createdDate desc limit 1", nativeQuery = true)
	CandidateHistory Candidate_IdAndEligibilityMetricsIsNotNullAndIsRecordDeletedFalseOrderByCreatedDateDesc(
			Long candidateId);

	@Query(value = "select * from candidatehistory where candidateId=:candidateId and familyMetrics is not null order by createdDate desc limit 1", nativeQuery = true)
	CandidateHistory Candidate_IdAndFamilyMetricsIsNotNullAndIsRecordDeletedFalseOrderByCreatedDateDesc(
			Long candidateId);

	@Query(value = "select * from candidatehistory where candidateId=:candidateId and familyMedicalMetrics is not null order by createdDate desc limit 1", nativeQuery = true)
	CandidateHistory Candidate_IdAndFamilyMedicalMetricsIsNotNullAndIsRecordDeletedFalseOrderByCreatedDateDesc(
			Long candidateId);

}
