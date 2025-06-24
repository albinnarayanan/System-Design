package com.lld_Splitwise_Practice;

public class User {
	private String name;
	private String phoneNumber;
	private String address;
	private String bio;
	private int age;
	public User(UserBuilder userBuilder) {
	
		this.name = userBuilder.getName();
		this.phoneNumber = userBuilder.getPhoneNumber();
		this.address = userBuilder.getAddress();
		this.bio = userBuilder.getBio();
		this.age = userBuilder.getAge();
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public String getBio() {
		return bio;
	}
	public int getAge() {
		return age;
	}
	
	
	
}
