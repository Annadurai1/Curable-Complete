package com.curable.service.dto.custom;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestParam {
	@JsonProperty("testName")
	private String testName;
	@JsonProperty("subtestName")
	private String subtestName;
	@JsonProperty("condition")
	private List<ConditionDTO> condition;
	@JsonProperty("valueType")
	private String valueType;
	@JsonProperty("values")
	private List<String> values;
	@JsonProperty("selectedValues")
	private List<Object> selectedValues;
	@JsonProperty("isMandatory")
	private Boolean isMandatory;

	public TestParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestParam(String testName, List<Object> selectedValues) {
		super();
		this.testName = testName;
		this.selectedValues = selectedValues;
	}

	public Boolean getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getSubtestName() {
		return subtestName;
	}

	public void setSubtestName(String subtestName) {
		this.subtestName = subtestName;
	}

	public List<ConditionDTO> getCondition() {
		return condition;
	}

	public void setCondition(List<ConditionDTO> condition) {
		this.condition = condition;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public List<Object> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(List<Object> selectedValues) {
		this.selectedValues = selectedValues;
	}

}
