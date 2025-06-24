package com.lld_RateLimiter_Practice;

public class Configuration {
	private int capacity;
	private int id;
	private String service;
	
	public Configuration(int capacity, int id, String service) {
		
		this.capacity = capacity;
		this.id = id;
		this.service = service;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getId() {
		return id;
	}

	public String getService() {
		return service;
	}
	
	
	

}
