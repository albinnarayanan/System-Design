package com.lld_RateLimiter_Practice;

import java.sql.Timestamp;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucketStrategy implements RateLimiterStrategy {
	private Configuration configuration;
	private int LeakRatePerSec;
	private BlockingQueue<Data> queue;
	private ScheduledExecutorService scheduler;
	
	
	
	public LeakyBucketStrategy(Configuration config, int leakRatePerSec) {
		this.configuration = config;
		this.LeakRatePerSec = leakRatePerSec;
		this.queue = new LinkedBlockingQueue<Data>(config.getCapacity());
		this.scheduler = Executors.newSingleThreadScheduledExecutor();
		startLeaking();
	}
	@Override
	public boolean grantAccess() {
		if(queue.remainingCapacity()>0) {
			queue.offer(new Data(configuration.getService(), new Timestamp(System.currentTimeMillis())));
			return true;
		}
		
		return false;
	}
	public void startLeaking() {
		long leakIntervalMillis = 1000L/LeakRatePerSec;
		scheduler.scheduleAtFixedRate(()->{
			queue.poll();
		}, 0, leakIntervalMillis, TimeUnit.MILLISECONDS);
		
	}

}
