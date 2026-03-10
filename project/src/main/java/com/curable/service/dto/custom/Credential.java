package com.curable.service.dto.custom;

import com.curable.util.Constant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "temporary", "type", "value" })
public class Credential {

	@JsonProperty("temporary")
	private Boolean temporary;
	@JsonProperty("type")
	private String type;
	@JsonProperty("value")
	private String value;

	@JsonProperty("temporary")
	public Boolean getTemporary() {
		return temporary;
	}

	@JsonProperty("temporary")
	public void setTemporary(Boolean temporary) {
		this.temporary = temporary;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	public Credential(Boolean temporary, String value) {
		super();
		this.temporary = temporary;
		this.type = Constant.PASSWORD;
		this.value = value;
	}

	public Credential() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}