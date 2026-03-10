package com.curable.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.curable.config.EmailService;
import com.curable.domain.Employee;
import com.curable.domain.HospitalEmployeeMap;
import com.curable.repository.EmployeeRepository;
import com.curable.repository.HospitalEmployeeMapRepository;
import com.curable.repository.MenuRoleMappingRepository;
import com.curable.service.dto.EmployeeDTO;
import com.curable.service.dto.SearchCustomDTO;
import com.curable.service.dto.custom.AuthMenuDTO;
import com.curable.service.dto.custom.Credential;
import com.curable.service.dto.custom.KeycloakUserCreationCustomDTO;
import com.curable.service.dto.custom.MailCustomDTO;
import com.curable.service.mapper.EmployeeRoleMasterMapper;
import com.curable.service.mapper.HospitalMapper;
import com.curable.util.Constant;

@Service
@Transactional
public class AuthService {

	@Autowired
	private MenuRoleMappingRepository mappingRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private HospitalMapper hospitalMapper;

	@Autowired
	private EmployeeRoleMasterMapper employeeRoleMasterMapper;

	@Autowired
	private HospitalEmployeeMapRepository hospitalEmployeeMapRepository;

	@Autowired
	RestTemplate restTemplate;

	@Value("${keycloak.userCreationUrl}")
	private String userCreationUrl;

	@Autowired
	KeycloakService keycloakService;

	@Autowired
	EmailService emailService;

	public AuthMenuDTO getMenu(String login) throws Exception {
		try {
			AuthMenuDTO userDetails = employeeRepository.getUserDetailsByLogin(login);
			if (userDetails != null) {
				if (!userDetails.getIsRecordDeleted()) {
					userDetails.setCustomMenuDTOs(mappingRepository.getMenuByRole(userDetails.getRoleId()));
					String hospitalInfo = employeeRepository.getHospitalIdByUser(userDetails.getUserId());
					if (hospitalInfo != null) {
						String[] split = hospitalInfo.split("#");
						userDetails.setHospitalId(Long.parseLong(split[0]));
						userDetails.setTenantName(split[1]);
					}

				} else {
					userDetails.setMessage("Your login access is inactive. Please contact the admin");
				}
			} else {
				throw new Exception("Invalid login");
			}
			return userDetails;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public Long createUser(EmployeeDTO employeeDTO) throws Exception {
		List<HospitalEmployeeMap> saveList = new ArrayList<HospitalEmployeeMap>();

		try {
			// save employee
			if (employeeDTO.getId() == null) {

				Employee employee = employeeRepository
						.save(new Employee(null, employeeRoleMasterMapper.fromId(employeeDTO.getEmployeeRoleMasterId()),
								employeeDTO.getName(), employeeDTO.getYearsExp(), employeeDTO.getPhoneNo(),
								employeeDTO.getGender(), employeeDTO.getEmail()));
				// save employeeHospitalMap

				employeeDTO.getHospitalId().forEach(hospitalId -> {
					saveList.add(new HospitalEmployeeMap(null, employee, hospitalMapper.fromId(hospitalId)));
				});
				if (!saveList.isEmpty())
					hospitalEmployeeMapRepository.saveAll(saveList);

				// keycloak user creation
				String password = employeeDTO.getPassword() == null ? "cure@123" : employeeDTO.getPassword();
				String result = keycloakService.createKeyCloakUser(employeeDTO.getName(), password,
						employeeDTO.getEmail());
				employee.setKeycloakUserId(result);
				// employeeRepository.updateKeycolakId(null, employee.getId());
				emailService.sendMail(employeeDTO.getEmail(), "Welcome to -",
						new MailCustomDTO(employeeDTO.getEmail(), "http://52.66.226.72/", password));
				return employee.getId();
			} else {
				Employee e = new Employee(employeeDTO.getId(),
						employeeRoleMasterMapper.fromId(employeeDTO.getEmployeeRoleMasterId()), employeeDTO.getName(),
						employeeDTO.getYearsExp(), employeeDTO.getPhoneNo(), employeeDTO.getGender(),
						employeeDTO.getEmail(), employeeDTO.getKeycloakUserId());
				e.setIsRecordDeleted(employeeDTO.getIsRecordDeleted());
				employeeRepository.save(e);
				if (employeeDTO.getKeycloakUserId() != null)
					keycloakService.madeUserInActive(employeeDTO.getKeycloakUserId(),
							!employeeDTO.getIsRecordDeleted());
				return employeeDTO.getId();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public void keyCloakUserCreation(EmployeeDTO employeeDTO) {
		try {
			KeycloakUserCreationCustomDTO keycloakUserCreationCustomDTO = new KeycloakUserCreationCustomDTO(
					employeeDTO.getEmail(), employeeDTO.getName(), employeeDTO.getName(), employeeDTO.getEmail(), true,
					true, Arrays.asList(new Credential(false, "defaultPassword")));
			HttpHeaders headers = new HttpHeaders();
			headers.add(Constant.HEADER_STRING, Constant.TOKEN_PREFIX + Constant.KEYCLOAK_STATIC_ACCESS_TOKEN);
			HttpEntity<?> httpEntity = new HttpEntity<>(keycloakUserCreationCustomDTO, headers);
			restTemplate.postForLocation(userCreationUrl, httpEntity);
		} catch (RestClientException e) {
			throw e;
		}
	}

	public List<AuthMenuDTO> getUsersByHospital(SearchCustomDTO searchCustomDTO) {
		return employeeRepository.getUserDetailsBySearch(searchCustomDTO.getSearch(), searchCustomDTO.getHospitalId());

	}

}
