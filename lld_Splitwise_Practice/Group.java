package com.lld_Splitwise_Practice;

import java.util.List;

public class Group {
	private List<String>users;
	private String name;
	private String desc;
	public Group(List<String> users, String name, String desc) {
		this.users = users;
		this.name = name;
		
		this.desc = desc;
	}
	public List<String> getUsers() {
		return users;
	}
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	
	
	
	

}
