package com.lld_EventBusWithThreadPractice;

import java.util.concurrent.atomic.AtomicInteger;

public class Subscriber {
	private AtomicInteger offset;
	private ISubscriber subscriber;
	
	public Subscriber( ISubscriber subscriber) {
		this.subscriber = subscriber;
		this.offset = new AtomicInteger(0);
	}

	public AtomicInteger getOffset() {
		return offset;
	}

	public ISubscriber getSubscriber() {
		return subscriber;
	}
	

}
