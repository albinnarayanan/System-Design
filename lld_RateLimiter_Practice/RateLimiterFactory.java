package com.lld_RateLimiter_Practice;

public class RateLimiterFactory {
	public static RateLimiterStrategy getInstance(Configuration config, RateLimiterAlgorithm type) {
		switch (type) {
		case SLIDING_WINDOW:
			return new SlidingWindowStrategy(config,1);
		
		case TOKEN_BUCKET:
			return new TokenBucketStrategy(config,10);
		
		default:
				return new LeakyBucketStrategy(config,2);
		}
		
		
	}

}
