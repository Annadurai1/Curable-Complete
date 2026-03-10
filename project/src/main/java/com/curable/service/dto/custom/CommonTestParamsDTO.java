package com.curable.service.dto.custom;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CommonTestParamsDTO {

	@JsonProperty("params")
	public List<TestParam> params;
	@JsonProperty("repeat")
	public Boolean repeat;
	@JsonProperty("repeatlabel")
	public String repeatlabel;
	@JsonProperty("id")
	public Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getRepeat() {
		return repeat;
	}

	public void setRepeat(Boolean repeat) {
		this.repeat = repeat;
	}

	public String getRepeatlabel() {
		return repeatlabel;
	}

	public void setRepeatlabel(String repeatlabel) {
		this.repeatlabel = repeatlabel;
	}

	public List<TestParam> getParams() {
		return params;
	}

	public void setParams(List<TestParam> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "CommonTestParamsDTO [params=" + params + "]";
	}

}
