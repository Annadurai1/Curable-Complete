package com.curable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.HospitalEmployeeMap;
import com.curable.service.dto.EmployeeDTO;

@Repository
public interface HospitalEmployeeMapRepository
		extends JpaRepository<HospitalEmployeeMap, Long>, QuerydslPredicateExecutor<HospitalEmployeeMap> {

	List<HospitalEmployeeMap> findByEmployee_IdAndIsRecordDeletedFalse(Long hospitalEmployeeId);

	List<HospitalEmployeeMap> findByHospital_IdAndIsRecordDeletedFalse(Long hospitalId);

	@Query("select new com.curable.service.dto.EmployeeDTO(b.id,b.name,c.description) from HospitalEmployeeMap a left join a.employee b left join b.employeeRoleMaster c "
			+ "where a.hospital.id= :hospitalId")
	List<EmployeeDTO> getStaffDetailsByHospitalId(Long hospitalId);

	
}
