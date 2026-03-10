package com.curable.service.dto.custom;

import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "username", "firstName", "lastName", "email", "emailVerified", "enabled", "credentials" })
@Generated("jsonschema2pojo")
public class KeycloakUserCreationCustomDTO {

	@JsonProperty("username")
	private String username;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("emailVerified")
	private Boolean emailVerified;
	@JsonProperty("enabled")
	private Boolean enabled;
	@JsonProperty("credentials")
	private List<Credential> credentials;

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("emailVerified")
	public Boolean getEmailVerified() {
		return emailVerified;
	}

	@JsonProperty("emailVerified")
	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	@JsonProperty("enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	@JsonProperty("enabled")
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@JsonProperty("credentials")
	public List<Credential> getCredentials() {
		return credentials;
	}

	@JsonProperty("credentials")
	public void setCredentials(List<Credential> credentials) {
		this.credentials = credentials;
	}

	public KeycloakUserCreationCustomDTO(String username, String firstName, String lastName, String email,
			Boolean emailVerified, Boolean enabled, List<Credential> credentials) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.emailVerified = emailVerified;
		this.enabled = enabled;
		this.credentials = credentials;
	}

	public KeycloakUserCreationCustomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
