package com.curable.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curable.config.SecurityUtils;
import com.curable.domain.Camp;
import com.curable.domain.CampStaff;
import com.curable.domain.RegIdSequenceGenerator;
import com.curable.repository.CampRepository;
import com.curable.repository.CampStaffRepository;
import com.curable.repository.EmployeeRepository;
import com.curable.repository.RegIdSequenceGeneratorRepository;
import com.curable.service.dto.ActiveCampCustomDTO;
import com.curable.service.dto.CampDTO;
import com.curable.service.dto.CampStaffDTO;
import com.curable.service.dto.SearchCustomDTO;
import com.curable.service.mapper.CampMapper;
import com.curable.service.mapper.CampStaffMapper;
import com.curable.service.mapper.EmployeeMapper;
import com.curable.service.mapper.HospitalMapper;
import com.curable.util.CommonUtil;
import com.curable.util.Constant;

@Service
@Transactional
public class CampService {

	@Autowired
	private CampRepository campRepository;

	@Autowired
	private CampStaffRepository campStaffRepository;

	// @Autowired
	private final CampMapper campMapper;

	// @Autowired
	private final CampStaffMapper campStaffMapper;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	RegIdSequenceGeneratorRepository regIdSequenceGeneratorRepository;

	@Autowired
	HospitalMapper hospitalMapper;

	public CampService(CampMapper campMapper, CampStaffMapper campStaffMapper) {
		this.campMapper = campMapper;
		this.campStaffMapper = campStaffMapper;
	}

	public List<CampDTO> getAll() {
		return campRepository.findAll().stream().map(campMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	public Optional<CampDTO> findBy(Long id) {
		// log.debug("Request to get Camp : {}", id);
		return campRepository.findById(id).map(campMapper::toDto);
	}

	public Camp save(CampDTO campDTO) throws Exception {
		// log.debug("Request to save Camp : {}", campDTO);

		// String campPrefix = dmas.getCode() + tmas.getCode() + pmas.getCode();
		// campDTO.setCampIdPrefix(campPrefix);

		if (campDTO.getCampIdPrefix() == null || campDTO.getCampIdPrefix().isEmpty()) {
			campDTO.setCampIdPrefix(generateRegSequenceBasedOnHospital(campDTO.getHospitalId()));
		}

		if (campDTO.getCampIdPrefix() != null && campDTO.getCampIdPrefix().length() < 7) {
			throw new Exception("campId not less than 7 digit");
		}
		Camp camp = campMapper.toEntity(campDTO);
		camp = campRepository.save(camp);
		return camp;
	}

	public String generateRegSequenceBasedOnHospital(Long hospitalId) {
		try {
			long resetKey = 0;
			RegIdSequenceGenerator reg = regIdSequenceGeneratorRepository
					.findByHospital_idAndIsRecordDeletedFalse(hospitalId);
			if (reg == null) {
				reg = regIdSequenceGeneratorRepository
						.save(new RegIdSequenceGenerator(null, hospitalMapper.fromId(hospitalId), 1000000L, 7L, 1L));
				resetKey = reg.getSequenceNo();
			} else {
				resetKey = reg.getSequenceNo() + reg.getIncrement();
				reg.setSequenceNo(resetKey);
			}

			// String sequence = String.format("%0" + reg.getMax() + "d", resetKey);
			return String.valueOf(resetKey);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(CampDTO campDTO, List<CampStaffDTO> campStaffDTO) throws Exception {
		Camp result = null;
		try {
			if (campDTO.getId() == null) {
				result = save(campDTO);
				List<CampStaff> campStaffs = new ArrayList<CampStaff>();
				Long createdUserId = employeeRepository.getUserIdByLogginUser(SecurityUtils.getCurrentUserLogin(),
						Constant.CAMP_COORDINATOR);
				if (createdUserId != null)
					campStaffs.add(new CampStaff(null, result, employeeMapper.fromId(createdUserId)));
				for (int i = 0; i < campStaffDTO.size(); i++) {

					campStaffs.add(new CampStaff(null, result,
							employeeMapper.fromId(campStaffDTO.get(i).getHospitalEmployeeId())));
				}
				if (!campStaffs.isEmpty())
					campStaffRepository.saveAll(campStaffs);
				return result.getCampIdPrefix();
			} else {
				List<CampStaff> campStaffs = new ArrayList<CampStaff>();
				campStaffRepository.deleteByCampId(campDTO.getId());
				Long createdUserId = employeeRepository.getUserIdByLogginUser(SecurityUtils.getCurrentUserLogin(),
						Constant.CAMP_COORDINATOR);
				for (int i = 0; i < campStaffDTO.size(); i++) {
					if (campStaffDTO.get(i).getHospitalEmployeeId() != createdUserId)
						campStaffs.add(new CampStaff(null, campMapper.fromId(campDTO.getId()),
								employeeMapper.fromId(campStaffDTO.get(i).getHospitalEmployeeId())));
				}

				if (createdUserId != null)
					campStaffs.add(new CampStaff(null, campMapper.fromId(campDTO.getId()),
							employeeMapper.fromId(createdUserId)));
				if (!campStaffs.isEmpty())
					campStaffRepository.saveAll(campStaffs);
				return campDTO.getCampIdPrefix();
			}
		} catch (Exception e) {
			throw e;
		}

	}

	public List<ActiveCampCustomDTO> getActiveCamps(SearchCustomDTO searchCustomDTO) {
		String search = searchCustomDTO.getSearch();
		return campRepository.getActiveCampsByFilter(searchCustomDTO.getRoleId(), search,
				CommonUtil.filter(searchCustomDTO));
	}

	public void delete(Long id) {
		// log.debug("Request to delete : {}",id );
		campRepository.deleteById(id);
	}

	public boolean checkDuplication(String outreachClinicId, Long hospitalId) {
		return campRepository.countByCampIdPrefixAndHospital_idAndIsRecordDeletedFalse(outreachClinicId,
				hospitalId) == 0 ? false : true;
	}
	
	public List<ActiveCampCustomDTO> activecampForReport(SearchCustomDTO searchCustomDTO) {
		String search = searchCustomDTO.getSearch();
		return campRepository.getActiveCampsForReport(searchCustomDTO.getRoleId(), search,
				CommonUtil.filter(searchCustomDTO));
	}
}
