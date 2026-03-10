package com.curable.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curable.config.SecurityUtils;
import com.curable.domain.Candidate;
import com.curable.domain.CandidateTest;
import com.curable.domain.RegIdSequenceGenerator;
import com.curable.domain.enums.Gender;
import com.curable.repository.CampRepository;
import com.curable.repository.CandidateRepository;
import com.curable.repository.CandidateTestRepository;
import com.curable.repository.DiseaseTestMasterRepository;
import com.curable.repository.RegIdSequenceGeneratorRepository;
import com.curable.service.dto.CandidateCustomDTO;
import com.curable.service.dto.CandidateDTO;
import com.curable.service.dto.DiseaseTestMasterDTO;
import com.curable.service.dto.SearchCustomDTO;
import com.curable.service.dto.custom.DiseaseEligibilityDTO;
import com.curable.service.mapper.CampMapper;
import com.curable.service.mapper.CandidateMapper;
import com.curable.service.mapper.DiseaseTestMasterMapper;
import com.curable.service.mapper.HospitalMapper;
import com.curable.util.CommonUtil;
import com.curable.util.Constant;

@Service
@Transactional
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	// @Autowired
	private final CandidateMapper candidateMapper;

	@Autowired
	DiseaseTestMasterMapper diseaseTestMasterMapper;

	@Autowired
	CandidateTestRepository candidateTestRepository;

	@Autowired
	DiseaseTestMasterRepository diseaseTestMasterRepository;

	@Autowired
	CampRepository campRepository;

	@Autowired
	CampMapper campMapper;

	@Autowired
	CandidateHabitService candidateHabitService;

	@Autowired
	HospitalMapper hospitalMapper;

	@Autowired
	RegIdSequenceGeneratorRepository regIdSequenceGeneratorRepository;

	public CandidateService(CandidateMapper candidateMapper) {
		this.candidateMapper = candidateMapper;
	}

	public List<CandidateDTO> getAll() {
		return candidateRepository.findAll().stream().map(candidateMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<CandidateDTO> findBy(Long id) {
		// log.debug("Request to get Candidate : {}", id);
		return candidateRepository.findById(id).map(candidateMapper::toDto);
	}

	@Transactional
	public String generateRegSequenceBasedOnCamp(Long campId) {
		try {
			long resetKey = 0;
			RegIdSequenceGenerator reg = regIdSequenceGeneratorRepository.findByCamp_idAndIsRecordDeletedFalse(campId);
			if (reg == null) {
				reg = regIdSequenceGeneratorRepository
						.save(new RegIdSequenceGenerator(null, campMapper.fromId(campId), 1L, 6L, 1L, null));
				resetKey = reg.getSequenceNo();
			} else {
				resetKey = reg.getSequenceNo() + reg.getIncrement();
				reg.setSequenceNo(resetKey);
			}

			String sequence = String.format("%0" + reg.getMax() + "d", resetKey);
			regIdSequenceGeneratorRepository.save(reg);
			return sequence;
		} catch (Exception e) {
			throw e;
		}
	}

	public String generateRegSequenceBasedOnCampAndStreetId(Long campId, Long streetId) {
		try {
			long resetKey = 0;
			RegIdSequenceGenerator reg = regIdSequenceGeneratorRepository
					.findByCamp_idAndStreetIdAndIsRecordDeletedFalse(campId, streetId);
			if (reg == null) {
				reg = regIdSequenceGeneratorRepository
						.save(new RegIdSequenceGenerator(null, campMapper.fromId(campId), 1L, 6L, 1L, streetId));
				resetKey = reg.getSequenceNo();
			} else {
				resetKey = reg.getSequenceNo() + reg.getIncrement();
				reg.setSequenceNo(resetKey);
			}

			String sequence = String.format("%0" + reg.getMax() + "d", resetKey);
			return sequence;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public CandidateDTO saveV2(CandidateDTO candidateDTO) {
		boolean isNew = candidateDTO.getId() == null;

		if (isNew) {
			prepareNewCandidateRegistration(candidateDTO);
			Candidate savedCandidate = candidateRepository.save(candidateMapper.toEntity(candidateDTO));
			CandidateDTO result = candidateMapper.toDto(savedCandidate);

			if (shouldAssignTests(candidateDTO)) {
				assignScreeningTests(savedCandidate, candidateDTO.getGender());
			}

			return result;
		}
		updateCandidateIfRequired(candidateDTO);
		if (shouldAssignTests(candidateDTO) && !isAlreadyEnrolled(candidateDTO.getId())) {
			assignScreeningTests(candidateMapper.fromId(candidateDTO.getId()),
					normalizeGender(candidateDTO.getGender()));
		}
		return candidateDTO;
	}

	private boolean isAlreadyEnrolled(Long candidateId) {
		return candidateTestRepository.countByCandidate_IdAndIsRecordDeletedFalse(candidateId) > 0;
	}

	private boolean shouldAssignTests(CandidateDTO dto) {
		return dto.getScreenId() == 1;
	}

	private Gender normalizeGender(Gender gender) {
		return Gender.OTHER.equals(gender) ? Gender.FEMALE : gender;
	}

	private void assignScreeningTests(Candidate candidate, Gender gender) {
		List<Long> testIds = diseaseTestMasterRepository.getDiseaseTestMasterIdByGender(gender, List.of("SCREENING"),
				candidate.getHospital().getId());

		if (testIds != null && !testIds.isEmpty()) {
			List<CandidateTest> tests = testIds.stream()
					.map(testId -> new CandidateTest(null, candidate, diseaseTestMasterMapper.fromId(testId)))
					.collect(Collectors.toList());
			candidateTestRepository.saveAll(tests);
		}
	}

	private void updateCandidateIfRequired(CandidateDTO dto) {
		if (dto.getScreenId() == 2) {
			candidateRepository.updateCandidateById(dto.getId(), dto.getFatherName(), dto.getSpouseName(),
					dto.getAlternateMobileNo(), dto.getMonthlyIncome(), dto.getHouseType(), dto.getEducation(),
					dto.getOccupation(), dto.getVoterId(), dto.getRationCard(), dto.getAadhar(), dto.getTobaccoUser(),
					dto.getSocialHabits());
			if (dto.getCandidateHabitDTOs() != null && !dto.getCandidateHabitDTOs().isEmpty()) {
				candidateHabitService.saveHabits(dto.getCandidateHabitDTOs());
			}
		}
	}

	private void prepareNewCandidateRegistration(CandidateDTO dto) {
		String campPrefix = campRepository.findById(dto.getCampId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid camp ID")).getCampIdPrefix();

		String streetId = dto.getStreetId() != null ? String.format("%03d", dto.getStreetId()) : "000";

		dto.setRegistraionId(campPrefix + streetId + generateRegSequenceBasedOnCamp(dto.getCampId()));
		dto.setGender(normalizeGender(dto.getGender()));
	}

	@Transactional(rollbackFor = Exception.class)
	public CandidateDTO save(CandidateDTO candidateDTO) {
		CandidateDTO result;
		try {

			if (candidateDTO.getId() == null) {
				String campprefix = campRepository.findById(candidateDTO.getCampId()).get().getCampIdPrefix();

				String streetId = candidateDTO.getStreetId() != null ? String.format("%03d", candidateDTO.getStreetId())
						: "000";
				candidateDTO.setRegistraionId(
						campprefix + streetId + generateRegSequenceBasedOnCamp(candidateDTO.getCampId()));
				candidateDTO.setDob(CommonUtil.getApproxDob(candidateDTO.getAge() == null ? 0 : candidateDTO.getAge()));
				candidateDTO.setEnrolled(true);

				Candidate candidate = candidateMapper.toEntity(candidateDTO);
				// Save so enrolled as false
				if (candidateDTO.getScreenId() == 2) {
					candidate.setIsRecordDeleted(true);
					candidateDTO.setEnrolled(false);
				} /*
					 * else if (candidateDTO.getScreenId() == 1 &&
					 * !isAgeBetween30And60(candidateDTO.getAge())) {
					 * candidate.setIsRecordDeleted(true); }
					 */

				candidate = candidateRepository.save(candidate);
				result = candidateMapper.toDto(candidate);

				if (result.getGender() != null) {
					if (candidateDTO.getScreenId() == 1) {
						candidate.setIsRecordDeleted(false);
						buildAndSaveCandidateTests(candidate.getId(), candidateDTO);
					}

				}
				return result;
			} else if (candidateDTO.getId() != null) {
				if (candidateDTO.getScreenId() == 3) {
					candidateRepository.updateCandidateById(candidateDTO.getId(), candidateDTO.getFatherName(),
							candidateDTO.getSpouseName(), candidateDTO.getAlternateMobileNo(),
							candidateDTO.getMonthlyIncome(), candidateDTO.getHouseType(), candidateDTO.getEducation(),
							candidateDTO.getOccupation(), candidateDTO.getVoterId(), candidateDTO.getRationCard(),
							candidateDTO.getAadhar(), candidateDTO.getTobaccoUser(), candidateDTO.getSocialHabits());
					if (candidateDTO.getCandidateHabitDTOs() != null
							&& !candidateDTO.getCandidateHabitDTOs().isEmpty()) {
						candidateHabitService.saveHabits(candidateDTO.getCandidateHabitDTOs());
					}
				} else if (candidateDTO.getScreenId() == 1) {
					long alreadyEntrolled = candidateTestRepository
							.countByCandidate_IdAndIsRecordDeletedFalse(candidateDTO.getId());

					if (alreadyEntrolled == 0) {
						buildAndSaveCandidateTests(candidateDTO.getId(), candidateDTO);

					}
					candidateRepository.markAsActive(candidateDTO.getId(), candidateDTO.getName(),
							candidateDTO.getMobileNo(), candidateDTO.getGender(), candidateDTO.getAge(),
							candidateDTO.getAddress(), candidateDTO.getStreetId(),
							CommonUtil.getApproxDob(candidateDTO.getAge()), false, true);
				} else if (candidateDTO.getScreenId() == 2) {
					candidateRepository.markAsActiveV1(candidateDTO.getId(), candidateDTO.getName(),
							candidateDTO.getMobileNo(), candidateDTO.getGender(), candidateDTO.getAge(),
							candidateDTO.getAddress(), candidateDTO.getStreetId(),
							CommonUtil.getApproxDob(candidateDTO.getAge()), true, false, candidateDTO.getReason());
				}
			}
			return candidateDTO;
		} catch (Exception e) {
			throw e;
		} finally {
			result = null;
		}
	}

	public List<CandidateDTO> findByCamp_IdAndIsRecordDeletedFalse(Long campId) {
		// log.debug("Request to get Candidate : {}", campId);
		return candidateRepository.findByCamp_IdAndIsRecordDeletedFalse(campId).stream().map(candidateMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public void delete(Long id) {
		// log.debug("Request to delete : {}",id );
		candidateRepository.deleteById(id);
	}

	public List<CandidateDTO> getCandidateBasedOnType(SearchCustomDTO searchCustomDTO) {
		try {
			String testType = searchCustomDTO.getStage() == 1 ? "SCREENING" : "CLINICAL";
			String search = searchCustomDTO.getSearch() != null ? searchCustomDTO.getSearch().trim()
					: searchCustomDTO.getSearch();

			if (searchCustomDTO.getStage() == 3) {
				if (Constant.PROGRAM_COORDINATOR == searchCustomDTO.getRoleId()) {
					return candidateRepository.getCandidateData(searchCustomDTO.getHospitalId(), search);
				} else if (Constant.CAMP_COORDINATOR == searchCustomDTO.getRoleId()) {
					return candidateRepository.getCandidateForOtherRole(searchCustomDTO.getUserId(), search,
							searchCustomDTO.getHospitalId());
					/*
					 * return
					 * candidateRepository.getCandidateForCampCoordinator(searchCustomDTO.getUserId(
					 * ), search, searchCustomDTO.getHospitalId(),
					 * SecurityUtils.getCurrentUserJWT());
					 */
				} else {
					return candidateRepository.getCandidateForOtherRole(searchCustomDTO.getUserId(), search,
							searchCustomDTO.getHospitalId());
				}

			}
			// Fetch data based on stage̥̥
			List<CandidateCustomDTO> data = (searchCustomDTO.getStage() == 1)
					? candidateRepository.getScreeningDataProcedure(searchCustomDTO.getHospitalId(), search,
							searchCustomDTO.getRoleId(), searchCustomDTO.getUserId(),
							SecurityUtils.getCurrentUserLogin())
					: candidateRepository.getClinicalDataProcedure(searchCustomDTO.getHospitalId(), search,
							searchCustomDTO.getRoleId(), searchCustomDTO.getUserId(),
							SecurityUtils.getCurrentUserLogin());

			// Collect candidate IDs
			List<Long> cIdList = data.stream().map(a -> a.getId()).collect(Collectors.toList());

			// Fetch and map eligible diseases
			Map<Object, List<DiseaseEligibilityDTO>> mappedData = (searchCustomDTO.getStage() == 1)
					? candidateTestRepository.getEligibleTestScreeningOrClinic(cIdList, testType).stream()
							.collect(Collectors.groupingBy(DiseaseEligibilityDTO::getCandidateId))
					: candidateTestRepository.getEligibleTestScreeningOrClinic(cIdList, testType).stream()
							.collect(Collectors.groupingBy(DiseaseEligibilityDTO::getCandidateId));
			List<CandidateDTO> result = mapTo(data);
			// Set eligible diseases for each candidate
			result.forEach(d -> d.setEligibleDiseases(mappedData.getOrDefault(d.getId(), null)));

			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<CandidateDTO> mapTo(List<CandidateCustomDTO> data) {
		return data.stream().map(this::toCandidateDTO).collect(Collectors.toList());
	}

	private CandidateDTO toCandidateDTO(CandidateCustomDTO customDTO) {
		CandidateDTO dto = new CandidateDTO();
		dto.setId(customDTO.getId());
		dto.setName(customDTO.getName());
		dto.setAge(customDTO.getAge());
		dto.setMobileNo(customDTO.getMobileNo());
		dto.setHospitalId(customDTO.getHospitalId());
		dto.setGender(customDTO.getGender());
		dto.setRegistraionId(customDTO.getRegistrationId());
		return dto;
	}

	public boolean updateReferral(Long candidateId, boolean referral) {

		return false;

	}

	public Long savePatientReg(CandidateDTO candidateDTO) {
		String campprefix = campRepository.findById(candidateDTO.getCampId()).get().getCampIdPrefix();

		String streetId = candidateDTO.getStreetId() != null ? String.format("%03d", candidateDTO.getStreetId())
				: "000";

		return candidateRepository.save(new Candidate(campMapper.fromId(candidateDTO.getCampId()),
				candidateDTO.getAddress(), candidateDTO.getStreetId(),
				(campprefix + streetId + generateRegSequenceBasedOnCamp(candidateDTO.getCampId())))).getId();
	}

	public Integer calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}

	public List<String> getHabitTypes(String habit) {
		switch (habit) {
		case "Tobacco":
			return Arrays.asList("Hans", "Gutka", "Chewing Tobacco", "Betel Quid with Areca Nuts", "Pan Masala");
		case "Smoking":
			return Arrays.asList("Cigarette", "Bidi", "Hookah", "E-Cigarette");
		case "Alcohol":
			return Arrays.asList("Brandy", "Whisky", "Rum", "Beer", "Arrack");
		case "Snuff":
			return Collections.emptyList();
		default:
			return Collections.emptyList();
		}
	}

	public static boolean isAgeBetween30And60(int age) {
		return age >= 30 && age <= 60;
	}

	/**
	 * Creates and returns all tests for a candidate based on gender and age.
	 */
	public void buildAndSaveCandidateTests(Long candidateId, CandidateDTO candidateDTO) {
		List<CandidateTest> candidateTests = new ArrayList<>();

		// Normalize gender
		Gender gender = candidateDTO.getGender() == Gender.OTHER ? Gender.FEMALE : candidateDTO.getGender();

		// SCREENING group for age 30-60
		if (gender != null) {
			List<DiseaseTestMasterDTO> tests = diseaseTestMasterRepository.getTestByHospital(gender, Constant.SCREENING,
					candidateDTO.getHospitalId());
			if (tests != null && !tests.isEmpty()) {
				for (DiseaseTestMasterDTO test : tests) {
					if (test.getDefalutTest() || (!test.getDefalutTest()
							&& CommonUtil.matchAge(test.getCondition(), candidateDTO.getAge()))) {
						candidateTests.add(new CandidateTest(null, candidateMapper.fromId(candidateId),
								diseaseTestMasterMapper.fromId(test.getId())));
					}

				}
			}
		} /*
			 * else { // Non 30–60 => only oral + symptom tests
			 * diseaseTestMasterRepository.getDefaultTestByHospital(candidateDTO.
			 * getHospitalId(), Constant.SCREENING) .forEach(testId -> {
			 * 
			 * candidateTests.add(new CandidateTest(null,
			 * candidateMapper.fromId(candidateId),
			 * diseaseTestMasterMapper.fromId(testId))); });
			 * 
			 * }
			 */
		if (!candidateTests.isEmpty())
			candidateTestRepository.saveAll(candidateTests);
	}

}
