package com.lld_Splitwise_Practice;

public class Amount {
	private double amount;
	private Currency currency;
	
	public Amount(Currency currency,double amount ) {
		this.amount = amount;
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}
	public Amount add(Amount amount) {
		return new Amount(currency,this.amount+amount.getAmount());
	}

	@Override
	public String toString() {
		return "Amount [amount=" + amount + ", currency=" + currency + "]";
	}
	
}
