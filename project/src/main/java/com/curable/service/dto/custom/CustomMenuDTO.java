package com.curable.service.dto.custom;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomMenuDTO {
	private String menu;
	private Integer menuOrder;
	private String url;
	@JsonIgnore
	private String details;
	private List<String> privileges;

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CustomMenuDTO(String menu, Integer menuOrder, String url) {
		super();
		this.menu = menu;
		this.menuOrder = menuOrder;
		this.url = url;
	}

	public CustomMenuDTO(String menu, Integer menuOrder, String url, String details) {
		super();
		this.menu = menu;
		this.menuOrder = menuOrder;
		this.url = url;
		this.details = details;
		this.privileges = Arrays.asList(details.split(","));
	}

	@Override
	public String toString() {
		return "CustomMenuDTO [menu=" + menu + ", menuOrder=" + menuOrder + ", url=" + url + "]";
	}

}
