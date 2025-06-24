package com.lld_EventBusWithThreadPractice;

import java.util.ArrayList;
import java.util.List;

public class Topic {
	private String topicName;
	private String topicId;
	private List<Message> messages;
	private List<Subscriber> subscribers;
	public Topic(String topicName, String topicId) {
		
		this.topicName = topicName;
		this.topicId = topicId;
		messages = new ArrayList<Message>();
		subscribers = new ArrayList<Subscriber>();
	}
	public String getTopicName() {
		return topicName;
	}
	public String getTopicId() {
		return topicId;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public List<Subscriber> getSubscribers() {
		return subscribers;
	}
	
	public Topic createTopic(String topicName,String topicId) {
		return new Topic(topicName, topicId);
	}
	
	public void addMessage(Message message) {
		messages.add(message);
	}
	public void addSubscriber(Subscriber subscriber) {
		subscribers.add(subscriber);
	}
	
	

}
