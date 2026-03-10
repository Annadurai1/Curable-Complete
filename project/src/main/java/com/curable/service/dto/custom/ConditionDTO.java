package com.curable.service.dto.custom;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ConditionDTO {

	@JsonProperty("enabledField")
	private String enabledField;

	@JsonProperty("triggerValue")
	private String triggerValue;

	public String getEnabledField() {
		return enabledField;
	}

	public void setEnabledField(String enabledField) {
		this.enabledField = enabledField;
	}

	public String getTriggerValue() {
		return triggerValue;
	}

	public void setTriggerValue(String triggerValue) {
		this.triggerValue = triggerValue;
	}

}
