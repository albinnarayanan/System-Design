package com.lld_RateLimiter_Practice;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
	private static RateLimiterService rateLimiterService;
	private Map<Configuration, RateLimiterStrategy> rateLimiterMap = new HashMap<Configuration, RateLimiterStrategy>() ;
	
	public void addConfiguration(Configuration configuration, RateLimiterAlgorithm type) {
		rateLimiterMap.put(configuration, RateLimiterFactory.getInstance(configuration, type));
	}

	public synchronized static RateLimiterService getInstance(Configuration config, RateLimiterAlgorithm type) {
		if(rateLimiterService==null) {
			rateLimiterService = new RateLimiterService();
			rateLimiterService.addConfiguration(config, type);
		}
		
		return rateLimiterService;
	}
	public boolean accessApplication(Configuration config) {
		if(rateLimiterMap.get(config).grantAccess()) {
			System.out.println(Thread.currentThread().getName()+" -> able to access the application");
			return true;
		}
		else {
			System.out.println(Thread.currentThread().getName()+" -> Unable to access. Please try after sometime. ");
			return false;
			
		}
	}
}
