package com.lld_RateLimiter_Practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

	public static void main(String[] args) {
		Configuration config = new  Configuration(10, 1, "User-service");
		RateLimiterService rateLimiterService = RateLimiterService.getInstance(config, RateLimiterAlgorithm.TOKEN_BUCKET);
		ExecutorService executor = Executors.newFixedThreadPool(12);
		for(int i=0; i<11; i++) {
			RateLimiterService finalRateLimiterService = rateLimiterService;
			executor.execute(()->{
				finalRateLimiterService.accessApplication(config);
			});
		}
		executor.shutdown();

	}

}
