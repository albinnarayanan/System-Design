package com.lld_EventBusWithThreadPractice;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
	private Topic topic;
	private Map<String , SubscriberWorker> subscriberWorkers;
	
	public TopicHandler(Topic topic) {
		this.topic = topic;
		subscriberWorkers = new HashMap<String, SubscriberWorker>();
	}
	public void publish() {
		for(Subscriber sub : topic.getSubscribers()) {
			startSubscriberWorker(sub);
		}
	}
	public void startSubscriberWorker(Subscriber subscriber) {
		String subscriberId = subscriber.getSubscriber().getId();
		while(!subscriberWorkers.containsKey(subscriberId)) {
			SubscriberWorker newSub = new SubscriberWorker(topic, subscriber);
			subscriberWorkers.put(subscriberId, newSub);
			new Thread(newSub).start();
		}
		SubscriberWorker sub = subscriberWorkers.get(subscriberId);
		sub.wakeUpIfNeeded();
	}
	
}
