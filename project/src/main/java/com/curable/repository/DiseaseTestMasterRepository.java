package com.curable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.DiseaseTestMaster;
import com.curable.domain.enums.Gender;
import com.curable.service.dto.DiseaseTestMasterDTO;

@Repository
public interface DiseaseTestMasterRepository
		extends JpaRepository<DiseaseTestMaster, Long>, QuerydslPredicateExecutor<DiseaseTestMaster> {

	@Query("select id from DiseaseTestMaster where gender=:gender and name in :stage and hospital.id=:hospitalId")
	List<Long> getDiseaseTestMasterIdByGender(Gender gender, List<String> stage, Long hospitalId);

	DiseaseTestMaster findByNameAndGenderAndHospital_idAndIsRecordDeletedFalse(String stage, Gender gender,
			Long hospitalId);

	DiseaseTestMaster findByIdAndIsRecordDeletedFalse(Long id);

	@Query("select max(id) from DiseaseTestMaster where stage=:stage and name=:name")
	Long getDiseaseTestMasterBasedOnScreening(String stage, String name);

	@Query("select id from DiseaseTestMaster where name =:stage and hospital.id=:hospitalId")
	List<Long> getDefaultTestByHospital(Long hospitalId, String stage);

	DiseaseTestMaster findByNameAndGender(String stage, Gender gender);

	@Query("select id from DiseaseTestMaster where gender=:gender and name in :stage")
	List<Long> getDiseaseTestMasterIdByGender(Gender gender, List<String> stage);

	@Query("select new com.curable.service.dto.DiseaseTestMasterDTO(id,defalutTest,condition) from DiseaseTestMaster where  name =:stage and hospital.id=:hospitalId and (gender=:gender or gender is null)")
	List<DiseaseTestMasterDTO> getTestByHospital(Gender gender, String stage, Long hospitalId);

	DiseaseTestMaster findByStageAndHospital_idAndIsRecordDeletedFalse(String stage, Long hospitalId);

}
