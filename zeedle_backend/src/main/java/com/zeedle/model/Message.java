
package com.zeedle.model;

public class Message {
	private String message;
	private int id;
	
	
	public Message() {
	
	}


	public Message(String message, int id) {
		this.message = message;
		this.id = id;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
