package com.curable.service.dto.custom;

public class MailCustomDTO {
	private String userName;
	private String url;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MailCustomDTO(String userName, String url, String password) {
		super();
		this.userName = userName;
		this.url = url;
		this.password = password;
	}

	public MailCustomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
