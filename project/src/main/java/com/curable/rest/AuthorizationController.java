package com.curable.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curable.service.AuthService;
import com.curable.service.dto.EmployeeDTO;
import com.curable.service.dto.SearchCustomDTO;
import com.curable.service.dto.custom.AuthMenuDTO;

@RestController
public class AuthorizationController {

	@Autowired
	AuthService authService;

	@GetMapping("/authorizeUserRequest/{login}")
	public AuthMenuDTO authorizeUserRequest(@PathVariable String login) throws Exception {
		try {
			return authService.getMenu(login);
		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("/createUser")
	public void createUser(@RequestBody EmployeeDTO employeeDTO) throws Exception {
		authService.createUser(employeeDTO);
	}

	@PostMapping("/getUsers")
	public List<AuthMenuDTO> getUsers(@RequestBody SearchCustomDTO searchCustomDTO) {
		return authService.getUsersByHospital(searchCustomDTO);
	}

}
