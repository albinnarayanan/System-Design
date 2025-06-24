package com.lld_Splitwise_Practice;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class ExpenseService {
	private Map<String, List<Expense>> groupExpenses;
	
	public ExpenseService() {
		this.groupExpenses = new HashMap<String, List<Expense>>();
	}
	
	public void addExpense(Expense expense) {
		String groupId = expense.getGroupId();
		if(groupId!=null) {
			groupExpenses.putIfAbsent(groupId, new ArrayList<Expense>());
			groupExpenses.get(groupId).add(expense);
		}
	}
	public List<Expense> getGroupExpense(String groupId){
		return groupExpenses.get(groupId);
	}
	
	public PaymentGraph getPaymentGraph(BalanceMap balanceMap) {
		Comparator<Entry<String, Amount>> ascending = Comparator.comparingDouble(balance->balance.getValue().getAmount());
		PriorityQueue<Map.Entry<String, Amount>> positiveAmounts = new PriorityQueue<Map.Entry<String,Amount>>(ascending.reversed());
		PriorityQueue<Map.Entry<String, Amount>> negativeAmounts = new PriorityQueue<Map.Entry<String,Amount>>(ascending);
		for(Entry<String, Amount> balance : balanceMap.getBalances().entrySet()) {
			if(balance.getValue().getAmount()>0) {
				positiveAmounts.add(balance);
			}
			else {
				negativeAmounts.add(balance);
			}
			
		}
		var graph = new HashMap<String, BalanceMap>();
		while(!negativeAmounts.isEmpty() && !positiveAmounts.isEmpty()) {
			var largestPositive = positiveAmounts.poll();
			var largestNegative = negativeAmounts.poll();
			double positiveAmount = largestPositive.getValue().getAmount();
			double negativeAmout = -largestNegative.getValue().getAmount();
			graph.putIfAbsent(largestPositive.getKey(), new BalanceMap());
			graph.get(largestPositive.getKey()).getBalances().put(largestNegative.getKey(), new Amount(Currency.USD, Math.min(positiveAmount, negativeAmout)));
			double remaining = positiveAmount - negativeAmout;
			Amount remainingAmount = new Amount(Currency.USD, remaining);
			if(remaining>0) {
				positiveAmounts.add(new AbstractMap.SimpleEntry<>(largestPositive.getKey(),remainingAmount));
			}
			else if(remaining<0) {
				negativeAmounts.add(new AbstractMap.SimpleEntry<>(largestNegative.getKey(),remainingAmount));
			}
		}
			return new PaymentGraph(graph);
	}

}
