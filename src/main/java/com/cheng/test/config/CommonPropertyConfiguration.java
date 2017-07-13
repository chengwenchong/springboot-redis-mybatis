package com.cheng.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class CommonPropertyConfiguration {
	
	/**
	 * rest connection configurations
	 */
	private int connectionTimeout;
	private int readTimeout;
	private int writeTimeout;
	
	private String baseUrl;
	
	/**
	 * html layout
	 */
	private String layout;
	
	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	public int getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	public int getWriteTimeout() {
		return writeTimeout;
	}
	public void setWriteTimeout(int writeTimeout) {
		this.writeTimeout = writeTimeout;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
}
