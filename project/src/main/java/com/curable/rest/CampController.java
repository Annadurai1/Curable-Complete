package com.curable.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curable.service.CampService;
import com.curable.service.dto.ActiveCampCustomDTO;
import com.curable.service.dto.CampDTO;
import com.curable.service.dto.CampStaffDTO;
import com.curable.service.dto.SearchCustomDTO;
import com.curable.service.dto.custom.CampCreationDTO;

@RestController
public class CampController {

	@Autowired
	private CampService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/camp")
	public List<CampDTO> getAllCamps() {
		// log.debug("REST request to get all Camp");
		return service.getAll();
	}

	@GetMapping("/camp/{id}")
	public Optional<CampDTO> getCamp(@PathVariable Long id) {
		// log.debug("REST request to get Camps : {}", id);
		return service.findBy(id);
	}

	@PostMapping("/camp")
	public void postCamp(@RequestBody CampDTO camp) throws Exception {
		// log.debug("REST request to save camp : {}", id);
		service.save(camp);
	}

	@PostMapping("/newcamp")
	public String postCamp(@RequestBody CampCreationDTO campCreationDTO) throws Exception {
		// log.debug("REST request to save camp : {}", id);
		return service.save(campCreationDTO.getCampDTO(), campCreationDTO.getCampStaffs());
	}

	@PostMapping("/newcamp1")
	public String postCamp1(@RequestBody CampDTO campDTO, @RequestBody List<CampStaffDTO> staffDTO) throws Exception {
		// log.debug("REST request to save camp : {}", id);
		return service.save(campDTO, staffDTO);
	}

	@PostMapping("/activecamp")
	public List<ActiveCampCustomDTO> getActiveCamp(@RequestBody SearchCustomDTO searchCustomDTO) {
		// log.debug("REST request to get Camps : {}", id);
		return service.getActiveCamps(searchCustomDTO);
	}

	@GetMapping("isDuplicateClinic/hospitalId/{hospitalId}/outreachClinicId/{outreachClinicId}")
	public boolean checkDuplicateClinic(@PathVariable String outreachClinicId, @PathVariable Long hospitalId) {
		return service.checkDuplication(outreachClinicId, hospitalId);
	}

	@PostMapping("/activecampForReports")
	public List<ActiveCampCustomDTO> activecampForReport(@RequestBody SearchCustomDTO searchCustomDTO) {
		// log.debug("REST request to get Camps : {}", id);
		return service.activecampForReport(searchCustomDTO);
	}

}
