package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.curable.domain.CandidateMedicalDetails;

@Repository
public interface CandidateMedicalDetailsRepository extends JpaRepository<CandidateMedicalDetails, Long> {

	@Query(value = "select * from candidatemedicaldetails where candidateId=:candidateID order by createdDate desc limit 1", nativeQuery = true)
	CandidateMedicalDetails Candidate_idAndIsRecordDeletedFalseOrderByCreatedDateDesc(Long candidateID);

}
