package com.curable.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curable.config.SecurityUtils;
import com.curable.domain.Candidate;
import com.curable.domain.CandidateHistory;
import com.curable.domain.CandidateTest;
import com.curable.domain.enums.Gender;
import com.curable.repository.CandidateHabitRepository;
import com.curable.repository.CandidateHistoryRepository;
import com.curable.repository.CandidateMedicalDetailsRepository;
import com.curable.repository.CandidateRepository;
import com.curable.repository.CandidateTestRepository;
import com.curable.repository.DiseaseTestMasterRepository;
import com.curable.service.dto.CandidateDTO;
import com.curable.service.dto.CandidateHistoryDTO;
import com.curable.service.dto.custom.CandidateSearchCustomDTO;
import com.curable.service.dto.custom.CommonTestParamsDTO;
import com.curable.service.dto.custom.TestParam;
import com.curable.service.mapper.CandidateHabitMapper;
import com.curable.service.mapper.CandidateHistoryMapper;
import com.curable.service.mapper.CandidateMapper;
import com.curable.service.mapper.CandidateMedicalDetailsMapper;
import com.curable.service.mapper.DiseaseTestMasterMapper;
import com.curable.util.CommonUtil;
import com.curable.util.Constant;

@Service
@Transactional
public class CandidateHistoryService {

	@Autowired
	private CandidateHistoryRepository candidateHistoryRepository;

	// @Autowired
	private final CandidateHistoryMapper candidateHistoryMapper;

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	CandidateTestRepository testRepository;

	@Autowired
	CandidateMapper candidateMapper;

	@Autowired
	DiseaseTestMasterMapper diseaseTestMasterMapper;

	@Autowired
	DiseaseTestMasterRepository diseaseTestMasterRepository;

	@Autowired
	CandidateMedicalDetailsRepository candidateMedicalDetailsRepository;

	@Autowired
	CandidateMedicalDetailsMapper candidateMedicalDetailsMapper;

	@Autowired
	CandidateHabitRepository candidateHabitRepository;

	@Autowired
	CandidateHabitMapper candidateHabitMapper;

	public CandidateHistoryService(CandidateHistoryMapper candidateHistoryMapper) {
		this.candidateHistoryMapper = candidateHistoryMapper;
	}

	public List<CandidateHistoryDTO> getAll() {
		return candidateHistoryRepository.findAll().stream().map(candidateHistoryMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<CandidateHistoryDTO> findBy(Long id) {
		// log.debug("Request to get CandidateHistory : {}", id);
		return candidateHistoryRepository.findById(id).map(candidateHistoryMapper::toDto);
	}

	@Transactional(rollbackFor = Exception.class)
	public CandidateHistoryDTO save(CandidateHistoryDTO candidateHistoryDTO) {
		// log.debug("Request to save CandidateHistory : {}", candidateHistoryDTO);
		candidateHistoryDTO.setId(null);
		if (candidateHistoryDTO.getType() == 1 && candidateHistoryDTO.getTestMetrics() != null) {
			long match = candidateHistoryDTO.getTestMetrics().params.stream().filter(
							a -> (a.getTestName().equalsIgnoreCase("Referral") && a.getSelectedValues().contains("Yes")))
					.count();
			List<String> targetTests = Arrays.asList("Oral Examination", "Cervical Screening", "Breast Examination",
					"Symptoms Based Referral", "Is patient willing for Breast  Screening",
					"Is patient willing for Cervical Screening", "Is patient willing for Prostate Screening");
// {testName: "Breast Examination", subtestName: "NONE", selectedValues: ["No"]}

			boolean notNow = candidateHistoryDTO.getTestMetrics().params.stream()
					.anyMatch(a -> targetTests.contains(a.getTestName()) && a.getSelectedValues().contains("Not now"));

			boolean condition = match > 0 ? true : false;
			// If diseaseTestMasterId is 33 or 34, override condition to false
			if (candidateHistoryDTO.getDiseaseTestMasterId() == 33
					|| candidateHistoryDTO.getDiseaseTestMasterId() == 34) {
				condition = false;
			}
			testRepository.updateFieldByCandidateId(candidateHistoryDTO.getCandidateId(), notNow ? null : condition,
					candidateHistoryDTO.getTestMetrics(), candidateHistoryDTO.getDiseaseTestMasterId(), Instant.now(),
					SecurityUtils.getCurrentUserLogin(), notNow ? 0 : candidateHistoryDTO.getCompleted());

			if (condition && candidateHistoryDTO.getCompleted() == 1) {
				testRepository.save(new CandidateTest(null,
						candidateMapper.fromId(candidateHistoryDTO.getCandidateId()),
						diseaseTestMasterMapper.fromId(getValidClinicalTestId(candidateHistoryDTO.getStage()))));
				candidateRepository.updateCandidate(candidateHistoryDTO.getCandidateId(), 1L);
			}
			// update screening is done
			if (testRepository.isScreeningOrClinicalCompleted(candidateHistoryDTO.getCandidateId(), "SCREENING") == 0) {

				candidateRepository.updateCandidateById(candidateHistoryDTO.getCandidateId(), 1L);
			}

			return candidateHistoryDTO;
		} else if (candidateHistoryDTO.getType() == 2 && candidateHistoryDTO.getCandidateId() != null) {
			candidateHistoryDTO.setEligibleTests(
					testRepository.completedTestBasedOnCandidateId(candidateHistoryDTO.getCandidateId()));
		} else if (candidateHistoryDTO.getType() == 3) {
			testRepository.updateFieldByCandidateId(candidateHistoryDTO.getCandidateId(),
					candidateHistoryDTO.getTestMetrics(), candidateHistoryDTO.getDiseaseTestMasterId(), Instant.now(),
					SecurityUtils.getCurrentUserLogin(), candidateHistoryDTO.getCompleted());
			if (testRepository.isScreeningOrClinicalCompleted(candidateHistoryDTO.getCandidateId(), "CLINICAL") == 0) {
				candidateRepository.updateCandidate(candidateHistoryDTO.getCandidateId(), 2L);
			}
			return candidateHistoryDTO;
		} else if (candidateHistoryDTO.getEligibilityMetrics() != null) {
			long match = candidateHistoryDTO.getEligibilityMetrics().params.stream()
					.filter(a -> (a.getTestName().equalsIgnoreCase("Hysterectomy Done")
							&& a.getSelectedValues().contains("Yes")))
					.count();
			if (match > 0) {
				testRepository.deleteByCandidateIdAndDiseaseTestMaster_Id(candidateHistoryDTO.getCandidateId(),
						Constant.CERVICAL_SCREENING_TEST_ID);
			} /*
			 * else if (match == 0) { long alreadyHave = testRepository.
			 * countByCandidateIdAndDiseaseTestMaster_IdAndIsRecordDeletedFalse(
			 * candidateHistoryDTO.getCandidateId(), Constant.CERVICAL_SCREENING_TEST_ID);
			 * if (alreadyHave == 0) { testRepository .save(new CandidateTest(null,
			 * candidateMapper.fromId(candidateHistoryDTO.getCandidateId()),
			 * diseaseTestMasterMapper.fromId(Constant.CERVICAL_SCREENING_TEST_ID))); } }
			 */
		} else if (candidateHistoryDTO.getType() == 1 && candidateHistoryDTO.getFamilyMetrics() != null
				&& !candidateHistoryDTO.getFamilyMetrics().isEmpty()) {
			List<Candidate> toBeSave = new ArrayList<Candidate>();
			Candidate parentCandidateDetails = candidateRepository.findById(candidateHistoryDTO.getCandidateId())
					.orElse(null);
			candidateRepository.deleteByParentCandidateId(candidateHistoryDTO.getCandidateId());
			for (int i = 0; i < candidateHistoryDTO.getFamilyMetrics().size(); i++) {
				char suffix = (char) ('A' + i);
				CommonTestParamsDTO mem = candidateHistoryDTO.getFamilyMetrics().get(i);
				Candidate c = new Candidate(mem.getId(),
						parentCandidateDetails.getParentCandidateId() == null
								? (parentCandidateDetails.getRegistraionId() + "_" + suffix)
								: null,
						parentCandidateDetails.getCamp(), parentCandidateDetails.getId(),
						parentCandidateDetails.getStreetId(), parentCandidateDetails.getHospital(),
						parentCandidateDetails.getAddress());

				for (TestParam fields : mem.getParams()) {
					if (fields.getTestName().equals("Name")) {
						c.setName(CommonUtil.objectToString(fields.getSelectedValues()));
					} else if (fields.getTestName().equals("Relation ")) {
						c.setRelation(CommonUtil.objectToString(fields.getSelectedValues()));
					} else if (fields.getTestName().equals("Gender")) {
						c.setGender(
								Gender.valueOf(CommonUtil.objectToString(fields.getSelectedValues()).toUpperCase()));
					} else if (fields.getTestName().equals("Age")) {
						c.setAge(CommonUtil.objectToInt(fields.getSelectedValues()));
						c.setDob(CommonUtil.getApproxDob(c.getAge()));
					} else if (fields.getTestName().equals("Tobacco/Alcohol Habits")) {
						c.setTobaccoHabbits(CommonUtil.objectToString(fields.getSelectedValues()));

					} else if (fields.getTestName().equals("Marital Status")) {
						c.setMaritalStatus(CommonUtil.objectToString(fields.getSelectedValues()));
					}
				}
				c.setIsRecordDeleted(true);
				c.setReason("Family Personal Details");
				c.setSurveyStatus(parentCandidateDetails.getParentCandidateId() == null ? null : "NA");
				toBeSave.add(c);
			}
			if (!toBeSave.isEmpty())
				candidateRepository.saveAll(toBeSave);

		}
		CandidateHistory candidateHistory = candidateHistoryMapper.toEntity(candidateHistoryDTO);
		candidateHistory = candidateHistoryRepository.save(candidateHistory);
		CandidateHistoryDTO result = candidateHistoryMapper.toDto(candidateHistory);
		return result;
	}

	public CandidateHistoryDTO conversion(List<CandidateDTO> candidates) {
		CandidateHistoryDTO candidateHistoryDTO = new CandidateHistoryDTO();
		List<CommonTestParamsDTO> result = new ArrayList<>();
		if (candidates != null && !candidates.isEmpty()) {
			for (CandidateDTO candidate : candidates) {
				CommonTestParamsDTO dto = new CommonTestParamsDTO();
				List<TestParam> params = new ArrayList<>();
				// TEst cmds
				params.add(new TestParam("Name", Arrays.asList(candidate.getName())));
				params.add(new TestParam("Relation ", Arrays.asList(candidate.getRelation())));
				params.add(new TestParam("Gender", Arrays.asList(candidate.getGender())));
				params.add(new TestParam("Age",
						Arrays.asList(candidate.getAge() == null ? "0" : candidate.getAge().toString())));
				params.add(new TestParam("Marital Status", Arrays.asList(candidate.getMaritalStatus())));
				params.add(new TestParam("Tobacco/Alcohol Habits", Arrays.asList(candidate.getTobaccoHabbits())));
				dto.setParams(params);
				dto.setId(candidate.getId());
				result.add(dto);
			}
			candidateHistoryDTO.setFamilyMetrics(result);
		}

		return candidateHistoryDTO;
	}

	public List<CandidateHistoryDTO> findByCandidate_IdAndIsRecordDeletedFalse(Long candidateId) {
		// log.debug("Request to get CandidateHistory : {}", candidateId);
		return candidateHistoryRepository.findByCandidate_IdAndIsRecordDeletedFalse(candidateId).stream()
				.map(candidateHistoryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		// log.debug("Request to delete : {}",id );
		candidateHistoryRepository.deleteById(id);
	}

	public Long getValidClinicalTestId(String stage) {
		return diseaseTestMasterRepository.getDiseaseTestMasterBasedOnScreening(stage, Constant.CLINICAL);

	}

	public ResponseEntity<?> getCandidateDetails(CandidateSearchCustomDTO candidateSearchCustomDTO) {
		try {
			Long candidateId = candidateSearchCustomDTO.getCandidateId();
			int type = candidateSearchCustomDTO.getType();
			// PageRequest pageable = PageRequest.of(0, 1);
			if (type == 1) {
				return ResponseEntity.ok(candidateHistoryMapper.toDto(candidateHistoryRepository
						.Candidate_IdAndEligibilityMetricsIsNotNullAndIsRecordDeletedFalseOrderByCreatedDateDesc(
								candidateId)));
			} else if (type == 2) {
				CandidateDTO data = candidateRepository.getCandidate(candidateId);
				// habits
				data.setCandidateHabitDTOs(
						candidateHabitMapper.toDto(candidateHabitRepository.findByCandidate_id(candidateId)));
				return ResponseEntity.ok(data);
			} else if (type == 3) {
				return ResponseEntity.ok(candidateMedicalDetailsMapper.toDto(candidateMedicalDetailsRepository
						.Candidate_idAndIsRecordDeletedFalseOrderByCreatedDateDesc(candidateId)));
			} else if (type == 4) {

				/*
				 * return
				 * ResponseEntity.ok(candidateHistoryMapper.toDto(candidateHistoryRepository
				 * .Candidate_IdAndFamilyMetricsIsNotNullAndIsRecordDeletedFalseOrderByCreatedDateDesc(
				 * candidateId)));
				 */
				return ResponseEntity.ok(conversion(candidateRepository.getChildCandidate(candidateId)));
			} else if (type == 5) {
				return ResponseEntity.ok(candidateHistoryMapper.toDto(candidateHistoryRepository
						.Candidate_IdAndFamilyMedicalMetricsIsNotNullAndIsRecordDeletedFalseOrderByCreatedDateDesc(
								candidateId)));
			} else if (type == 6) {
				return ResponseEntity.ok(candidateMapper.toDto(candidateRepository.findById(candidateId).orElse(null)));
			} else if (type == 7) {
				return ResponseEntity.ok(testRepository.getScreeningOrClinicalData(candidateId,
						candidateSearchCustomDTO.getDiseaseTypeId()));
			}
			return null;
		} catch (Exception e) {
			throw e;
		}

	}

}
