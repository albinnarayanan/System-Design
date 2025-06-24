package com.lld_RateLimiter_Practice;

import java.sql.Timestamp;

public class Data {
	private String message;
	private Timestamp timeStamp;
	public Data(String message, Timestamp timeStamp) {
		
		this.message = message;
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	
	
}
