package com.lld_Splitwise_Practice;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

public class Main {
	public static void main(String [] args) throws IllegalAccessException {
		ExpenseService expenseService = constructExpenseService();
		Map<String, Group> groups = getGroups();
		GroupService groupService = new GroupService(expenseService, groups);
		var balances = groupService.getBalances("123", "A");
		var balanceMap = balances.getBalances();
		
		System.out.println("A will get the amount: "+balanceMap.get("A").getAmount());
		System.out.println("B will get the amount: "+balanceMap.get("B").getAmount());
		System.out.println("C will get the amount: "+balanceMap.get("C").getAmount());
	}
	
	public static ExpenseService constructExpenseService() {
		var expenseService = new ExpenseService();
		var firstExpense = new BalanceMap();
		firstExpense.getBalances().put("A", new Amount(Currency.USD, 10));
		firstExpense.getBalances().put("B", new Amount(Currency.USD, 20));
		firstExpense.getBalances().put("C", new Amount(Currency.USD, -30));
		
		expenseService.addExpense(new Expense("Outing", "Outing123", "123", firstExpense));
		
		var secondExpense = new BalanceMap();
		secondExpense.getBalances().put("A", new Amount(Currency.USD, -50));
		secondExpense.getBalances().put("B", new Amount(Currency.USD, 10));
		secondExpense.getBalances().put("C", new Amount(Currency.USD, 40));
		
		expenseService.addExpense(new Expense("outing2", "outing 2", "123", secondExpense));
		
		var thirdExpense = new BalanceMap();
		thirdExpense.getBalances().put("A", new Amount(Currency.USD, 90));
		thirdExpense.getBalances().put("C", new Amount(Currency.USD, -90));
		
		expenseService.addExpense(new Expense("outing3", "outing 3", "123", thirdExpense));
		return expenseService;
	}
	public static HashMap<String, Group> getGroups(){
		var groups = new HashMap<String, Group>();
		var userList = new ArrayList<String>();
		userList.add("A");
		userList.add("B");
		userList.add("C");
		groups.put("123", new Group(userList, "Europe", "EuropeTrip"));
		return groups;
	}

}
