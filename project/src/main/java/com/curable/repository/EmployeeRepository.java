package com.curable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.Employee;
import com.curable.service.dto.custom.AuthMenuDTO;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {

	@Query("select new com.curable.service.dto.custom.AuthMenuDTO(a.id,a.name,a.email,b.name,b.id,a.isRecordDeleted,a.gender,a.phoneNo) from Employee a left join EmployeeRoleMaster b on a.employeeRoleMaster.id=b.id "
			+ " where a.email=:login")
	AuthMenuDTO getUserDetailsByLogin(String login);

	List<Employee> findByEmployeeRoleMaster_IdAndIsRecordDeletedFalse(Long employeeRoleMasterId);

	@Query(value = "select concat(a.hospitalId,'#',b.name) from hospitalemployeemap a left join hospital b on b.id=a.hospitalId where a.employeeId= :userId limit 1", nativeQuery = true)
	String getHospitalIdByUser(Long userId);

	@Modifying(flushAutomatically = true)
	@Query("update Employee set keycloakUserId =:keycloakId where id = :userId ")
	int updateKeycolakId(String keycloakId, Long userId);

	@Query("select id from Employee where email=:email and employeeRoleMaster.id=:roleType")
	Long getUserIdByLogginUser(String email, Long roleType);

	@Query("select new com.curable.service.dto.custom.AuthMenuDTO(a.id,a.name,a.email,b.name,b.id,a.isRecordDeleted,a.gender,a.phoneNo,a.keycloakUserId) from HospitalEmployeeMap c left join Employee a on a.id=c.employee.id left join EmployeeRoleMaster b on a.employeeRoleMaster.id=b.id "
			+ " where c.hospital.id=:hospitalId and (a.email like %:search% or a.name like %:search%)")
	List<AuthMenuDTO> getUserDetailsBySearch(String search, Long hospitalId);

}
