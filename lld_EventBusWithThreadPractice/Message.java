package com.lld_EventBusWithThreadPractice;

import java.util.Map;

public class Message {
	private String msg;
	private String msgId;
	private Map<String, Object> payload;
	
	public Message(String msg, String msgId, Map<String, Object> payload) {
		this.msg = msg;
		this.msgId = msgId;
		this.payload = payload;
	}
	public String getMsg() {
		return msg;
	}
	
	
	

}
