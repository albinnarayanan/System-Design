package com.lld_EventBusWithThreadPractice;

public class SubscriberWorker implements Runnable {
	private Topic topic;
	private Subscriber subscriber;
	
	
	public SubscriberWorker(Topic topic, Subscriber subscriber) {
		this.topic = topic;
		this.subscriber = subscriber;
	}


	@Override
	public void run() {
		synchronized (subscriber) {
			
			do {
				int currOffset = subscriber.getOffset().get();
				while(currOffset>=topic.getMessages().size()) {
					try {
						subscriber.wait();
						
						
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				Message message = topic.getMessages().get(currOffset);
				try {
					subscriber.getSubscriber().consume(message);
					
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
					
				}
				subscriber.getOffset().compareAndSet(currOffset, currOffset+1);
				
				
			} while (true);	
		}	
	}
	synchronized public void wakeUpIfNeeded() {
		synchronized (subscriber) {
			subscriber.notify();
			
		}
	}

}
