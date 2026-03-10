package com.curable.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.Camp;
import com.curable.service.dto.ActiveCampCustomDTO;
import com.curable.service.dto.CampsReportDTO;

@Repository
public interface CampRepository extends JpaRepository<Camp, Long>, QuerydslPredicateExecutor<Camp> {

	List<Camp> findAllByEndDateGreaterThanEqual(Instant date);

	@Query(value = "call getActiveCampsByFilter(:roleId,:search,:condition)", nativeQuery = true)
	List<ActiveCampCustomDTO> getActiveCampsByFilter(Long roleId, String search, String condition);

	@Query(value = "call CampsReport(:startDate,:endDate,:campIds,:hospitalId)", nativeQuery = true)
	List<CampsReportDTO> report(String startDate, String endDate, String campIds, Long hospitalId);

	Long countByCampIdPrefixAndHospital_idAndIsRecordDeletedFalse(String prefix, Long hospitalId);
	
	@Query(value = "call getActiveCampsForReport(:roleId,:search,:condition)", nativeQuery = true)
	List<ActiveCampCustomDTO> getActiveCampsForReport(Long roleId, String search, String condition);
}
