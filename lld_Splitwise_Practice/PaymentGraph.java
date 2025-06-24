package com.lld_Splitwise_Practice;

import java.util.HashMap;
import java.util.Map;

public class PaymentGraph {
	private Map<String, BalanceMap> graph;
	
	public PaymentGraph() {
		this.graph = new HashMap<String, BalanceMap>();
	}

	public PaymentGraph(Map<String, BalanceMap> graph) {
		this.graph = graph;
	}

	public Map<String, BalanceMap> getGraph() {
		return graph;
	}

	@Override
	public String toString() {
		return "PaymentGraph [graph=" + graph + "]";
	}
	
	
	

}
