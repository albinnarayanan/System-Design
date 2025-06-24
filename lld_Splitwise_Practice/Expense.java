package com.lld_Splitwise_Practice;

public class Expense {
	private String title;
	private String desc;
	private String groupId;
	private BalanceMap userBalances;
	public Expense(String title, String desc, String groupId, BalanceMap userBalances) {
		
		this.title = title;
		this.desc = desc;
		this.groupId = groupId;
		this.userBalances = userBalances;
	}
	
	public String getGroupId() {
		return groupId;
	}


	public BalanceMap getUserBalances() {
		return userBalances;
	}

	@Override
	public String toString() {
		return "Expense [title=" + title + ", desc=" + desc + ", groupId=" + groupId + ", userBalances=" + userBalances
				+ "]";
	}

	
	
	
	
	

}
