package com.company.springcourse.FirstRestApp.utils;

public class PersonErrorResponse {
	private String message;
	private long timestamp;
	public PersonErrorResponse(String message, long timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
