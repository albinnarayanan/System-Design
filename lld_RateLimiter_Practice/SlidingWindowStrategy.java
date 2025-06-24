package com.lld_RateLimiter_Practice;


import java.sql.Timestamp;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowStrategy implements RateLimiterStrategy {
	private ConcurrentLinkedQueue<Data> queue ;
	private Configuration configuration ;
	private int timeWindowInSecs;
	
	public SlidingWindowStrategy(Configuration config, int timeWindowInSecs) {
		this.configuration = config;
		this.timeWindowInSecs = timeWindowInSecs;
		this.queue = new ConcurrentLinkedQueue<Data>();
	}
	
	@Override
	public boolean grantAccess() {
		if(queue.size()<configuration.getCapacity()) {
			queue.add(new Data(configuration.getService(), new Timestamp(System.currentTimeMillis())));
			return true;
		}
		return false;
	}
	public void checkAndUpdate(Timestamp currentTime) {
		if(queue.isEmpty()) {
			return ;
		}
		long diffInMillis =  currentTime.getTime()-queue.peek().getTimeStamp().getTime();
		long calculatedTime = diffInMillis/1000;
		while(calculatedTime>=timeWindowInSecs) {
			queue.poll();
			if(queue.isEmpty()) {
				break;
			}
			diffInMillis = currentTime.getTime()-queue.peek().getTimeStamp().getTime();
			calculatedTime = diffInMillis/1000;
		}
		
	}

}
