package com.cheng.test.domain;

import java.io.Serializable;

public class TestUser implements Serializable{
	private static final long serialVersionUID = 1435515995276255188L;
	
	private String id;
	
	private String loginName;
	
	private String password;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", loginName=" + loginName + ", password=" + password + "]";
	}
	
}
