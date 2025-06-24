package com.lld_Splitwise_Practice;

public class PaymentTrackingApp {
	private ExpenseService expenseService;
	private GroupService groupService;
	
	public PaymentTrackingApp(ExpenseService expenseService, GroupService groupService) {
		
		this.expenseService = expenseService;
		this.groupService = groupService;
	}
	
	

}
