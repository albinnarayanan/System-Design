package com.lld_EventBusWithThreadPractice;

public class Main {

	public static void main(String[] args) {
		Bus bus = new Bus();
		
		Topic topic1 = bus.createTopic("t1");
		Topic topic2 = bus.createTopic("t2");
		
		SleepingSubscriber sub1 = new SleepingSubscriber("sub1", 10000);
		SleepingSubscriber sub2 = new SleepingSubscriber("sub2", 10000);
		
		bus.subscribe(sub2, topic1);
		bus.subscribe(sub1, topic1);
		
		SleepingSubscriber sub3 = new SleepingSubscriber("sub3", 5000);
		bus.subscribe(sub3, topic2);

		bus.publish(topic1, new Message("msg1", "1", null));
		bus.publish(topic1, new Message("msg2", "2", null));
		
		bus.publish(topic2, new Message("msg3", "9", null));
	}

}
