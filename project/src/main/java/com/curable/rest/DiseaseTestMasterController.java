package com.curable.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curable.domain.enums.Gender;
import com.curable.service.DiseaseTestMasterService;
import com.curable.service.dto.DiseaseTestMasterDTO;

@RestController
public class DiseaseTestMasterController {

	@Autowired
	private DiseaseTestMasterService service;

	// RESTful API methods for Retrieval operation
	@GetMapping("/diseasetestmaster")
	public List<DiseaseTestMasterDTO> getAllDiseaseTestMasters() {
		// log.debug("REST request to get all DiseaseTestMaster");
		return service.getAll();
	}

	@GetMapping("/diseasetestmaster/{id}")
	public Optional<DiseaseTestMasterDTO> getDiseaseTestMaster(@PathVariable Long id) {
		// log.debug("REST request to get DiseaseTestMasters : {}", id);
		return service.findBy(id);
	}

	@PostMapping("/diseasetestmaster")
	public void postDiseaseTestMaster(@RequestBody DiseaseTestMasterDTO diseasetestmaster) {
		// log.debug("REST request to save diseasetestmaster : {}", id);
		service.save(diseasetestmaster);

	}

	@GetMapping("/getMetrics/{type}/{hospitalId}")
	public DiseaseTestMasterDTO getMetrics(@PathVariable String type, @PathVariable Long hospitalId) {
		// log.debug("REST request to get DiseaseTestMasters : {}", id);
		return service.getMetrics(type, hospitalId);
	}

	@GetMapping("/getMetricsById/{id}")
	public DiseaseTestMasterDTO getMetricsById(@PathVariable Long id) {
		// log.debug("REST request to get DiseaseTestMasters : {}", id);
		return service.getMetricsById(id);
	}

	@GetMapping("/getMetricsByGender/{type}/{gender}/{hospitalId}")
	public DiseaseTestMasterDTO getMetricsByGender(@PathVariable String type, @PathVariable String gender,
			@PathVariable Long hospitalId) {
		// log.debug("REST request to get DiseaseTestMasters : {}", id);
		return service.getMetrics(type, Gender.valueOf(gender), hospitalId);
	}

}
