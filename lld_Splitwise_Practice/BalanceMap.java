package com.lld_Splitwise_Practice;

import java.util.HashMap;
import java.util.Map;

public class BalanceMap {
	private Map<String, Amount> balances;
	
	public BalanceMap() {
		this.balances = new HashMap<String, Amount>();
	}
	public BalanceMap(Map<String, Amount> balanceMap) {
		this.balances = balanceMap;
	}

	public Map<String, Amount> getBalances() {
		return balances;
	}
	@Override
	public String toString() {
		return "BalanceMap [balances=" + balances + "]";
	}
	
	
}
