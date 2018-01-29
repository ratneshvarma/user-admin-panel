package com.panel.response;

import java.util.Date;

public class TokenData {

	private Integer code;
	private Date timeStamp;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public TokenData(Integer code, Date timeStamp, String token) {
		super();
		this.code = code;
		this.timeStamp = timeStamp;
		this.token = token;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;

}
