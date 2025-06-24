package com.lld_EventBusWithThreadPractice;

public interface ISubscriber {
	
	void consume(Message message) throws InterruptedException;
	String getId();

}
