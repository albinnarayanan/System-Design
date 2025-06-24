package com.lld_Splitwise_Practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupService {
	private static Amount nil = new Amount(Currency.USD, 0);
	private ExpenseService expenseService;
	private Map<String, Group> groups;
	public GroupService(ExpenseService expenseService, Map<String, Group> groups) {
		this.expenseService = expenseService;
		this.groups = groups;
	}
	
	public PaymentGraph getPaymentGraph(String groupId,String userId) throws IllegalAccessException{
		return expenseService.getPaymentGraph(getBalances(groupId, userId));
	}
	
	public BalanceMap sumExpenses(List<Expense> groupExpenses) {
		Map<String, Amount> resultBalances = new HashMap<String, Amount>();
		for(Expense expense : groupExpenses) {
			for( Map.Entry<String, Amount> balance: expense.getUserBalances().getBalances().entrySet()) {
				var amount = balance.getValue();
				var user = balance.getKey();
				resultBalances.put(user, resultBalances.getOrDefault(user, nil).add(amount));
			}
		}
		
		return new BalanceMap(resultBalances);
		
	}
	public BalanceMap getBalances(String groupId, String userId) throws IllegalAccessException {
		if(!groups.get(groupId).getUsers().contains(userId)) {
			throw new IllegalAccessException();
		}
		return sumExpenses(expenseService.getGroupExpense(groupId));
	}
	
	
	

}
