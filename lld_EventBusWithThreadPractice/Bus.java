package com.lld_EventBusWithThreadPractice;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bus {
	private Map<String, TopicHandler> topicProcessors;
	
	public Bus() {
		this.topicProcessors = new HashMap<String, TopicHandler>();
	}
	
	public Topic createTopic(String topicName) {
		Topic topic = new Topic(topicName, UUID.randomUUID().toString());
		TopicHandler topicHandler = new TopicHandler(topic);
		topicProcessors.put(topic.getTopicId(), topicHandler);
		System.out.println("Created topic : "+topic.getTopicName());
		return topic;
		
	}
	
	public void subscribe(ISubscriber subscriber, Topic topic) {
		topic.addSubscriber(new Subscriber(subscriber));
		System.out.println(subscriber.getId()+" has subscribed to :"+topic.getTopicName());
		
	}
	
	public void publish(Topic topic, Message message) {
		topic.addMessage(message);
		System.out.println(message.getMsg()+" added to topic: "+topic.getTopicName());
		new Thread(()->topicProcessors.get(topic.getTopicId()).publish()).start();
		
	}
	
	public void resetOffset(Topic topic, ISubscriber subscriber, Integer newOffset) {
		for(Subscriber sub : topic.getSubscribers()) {
			if(sub.getSubscriber().equals(subscriber)) {
				sub.getOffset().set(newOffset);
				System.out.println(subscriber.getId()+" has reset offset to "+newOffset);
				new Thread(()->topicProcessors.get(topic.getTopicId()).startSubscriberWorker(sub)).start();
			}
		}
		
	}

}
