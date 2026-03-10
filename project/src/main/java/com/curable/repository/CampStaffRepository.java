package com.curable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.CampStaff;
import com.curable.service.dto.EmployeeDTO;

@Repository
public interface CampStaffRepository extends JpaRepository<CampStaff, Long>, QuerydslPredicateExecutor<CampStaff> {

	List<CampStaff> findAllByCampId(Long campId);
	
	List<CampStaff> getHospitalEmployeeByCampId(Long campId);
	
	@Query("select new com.curable.service.dto.EmployeeDTO(b.id,b.name,c.description) from CampStaff a left join a.hospitalEmployee b left join b.employeeRoleMaster c "
			+ "where a.camp.id= :campId")
	List<EmployeeDTO> getStaffDetailsByCampId(Long campId);
	
	void deleteByCampId(Long campId);
}
