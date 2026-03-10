package com.curable.service.dto.custom;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ListCommonTestParamsDTO {
	@JsonProperty("params")
	private List<List<TestParam>> params;

	@JsonProperty("repeat")
	private Boolean repeat;

	@JsonProperty("repeatlabel")
	private String repeatlabel;

}
