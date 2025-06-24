package com.lld_Splitwise_Practice;

public class UserBuilder {
	private String name;
	private String phoneNumber;
	private int age;
	private String address;
	private String bio;
	
	public UserBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public UserBuilder setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	public UserBuilder setAge(int age) {
		this.age = age;
		return this;
	}
	public UserBuilder setAddress(String address) {
		this.address = address;
		return this;
	}
	public UserBuilder setBio(String bio) {
		this.bio = bio;
		return this;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public int getAge() {
		return age;
	}
	public String getAddress() {
		return address;
	}
	public String getBio() {
		return bio;
	}
	
	public User build() {
		User user = new User(this);
		return user;
	}
	@Override
	public String toString() {
		return "UserBuilder [name=" + name + ", phoneNumber=" + phoneNumber + ", age=" + age + ", address=" + address
				+ ", bio=" + bio + "]";
	}
	
	
	
	
	

}
