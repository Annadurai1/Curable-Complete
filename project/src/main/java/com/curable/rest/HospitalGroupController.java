package com.curable.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;
import com.curable.service.HospitalGroupService;
import com.curable.service.dto.HospitalGroupDTO;

@RestController
public class HospitalGroupController {

	@Autowired
	private HospitalGroupService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/hospitalgroup")
	public List<HospitalGroupDTO> getAllHospitalGroups() {
		//log.debug("REST request to get all HospitalGroup");
		return service.getAll();
	}

	@GetMapping("/hospitalgroup/{id}")
	public Optional<HospitalGroupDTO> getHospitalGroup(@PathVariable Long id) {
		//log.debug("REST request to get HospitalGroups : {}", id);
		return service.findBy(id);
	}
	@PostMapping("/hospitalgroup")
	public void postHospitalGroup(@RequestBody HospitalGroupDTO hospitalgroup) {
		//log.debug("REST request to save hospitalgroup : {}", id);
		service.save(hospitalgroup);
	
	}
	
}
