package com.curable.repository;



import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.curable.domain.RegIdSequenceGenerator;

@Repository
public interface RegIdSequenceGeneratorRepository extends JpaRepository<RegIdSequenceGenerator, Long> {

	RegIdSequenceGenerator findByCamp_idAndStreetIdAndIsRecordDeletedFalse(Long campId, Long streetId);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	RegIdSequenceGenerator findByCamp_idAndIsRecordDeletedFalse(Long campId);

	RegIdSequenceGenerator findByHospital_idAndIsRecordDeletedFalse(Long hospitalId);

}
