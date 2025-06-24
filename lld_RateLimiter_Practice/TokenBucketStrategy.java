package com.lld_RateLimiter_Practice;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucketStrategy implements RateLimiterStrategy{
	private int refreshRate;
	private Configuration configuration;
	private AtomicInteger currentCapacity;
	private AtomicLong lastUpdatedTime;
	

	public TokenBucketStrategy(Configuration config, int refreshRate) {
		this.configuration = config;
		this.refreshRate = refreshRate;
		this.currentCapacity = new AtomicInteger(config.getCapacity());
		this.lastUpdatedTime = new AtomicLong(System.currentTimeMillis());
		
	}
	@Override
	public boolean grantAccess() {
		refreshBucket();
		if(currentCapacity.get()>0) {
			currentCapacity.decrementAndGet();
			return true;
		}
		
		return false;
	}
	
	public void refreshBucket() {
		long currTime = System.currentTimeMillis();
		int additionalToken = (int)((currTime - lastUpdatedTime.get())/1000*refreshRate);
		int currCapacity = Math.min(currentCapacity.get()+additionalToken, configuration.getCapacity());
		currentCapacity.getAndSet(currCapacity);
		lastUpdatedTime.getAndSet(currTime);
		
	}
}
