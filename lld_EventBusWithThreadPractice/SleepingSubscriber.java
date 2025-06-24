package com.lld_EventBusWithThreadPractice;

public class SleepingSubscriber implements ISubscriber{
	private String id;
	private int sleepTimeInMillis;
	

	public SleepingSubscriber(String id, int sleepTimeInMillis) {
		this.id = id;
		this.sleepTimeInMillis = sleepTimeInMillis;
	}
	
	@Override
	public String getId() {
		return id;
		
	}

	@Override
	public void consume(Message message) throws InterruptedException {
		System.out.println("Subscriber :"+id+" started consuming : "+message.getMsg());
		Thread.sleep(sleepTimeInMillis);
		System.out.println("Subscriber :"+id+" done consuming : "+message.getMsg());
		
	}

	
	

}
