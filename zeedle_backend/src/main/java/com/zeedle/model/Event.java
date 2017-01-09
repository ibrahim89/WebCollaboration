package com.zeedle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="EVENT_SEQ")
	@SequenceGenerator(name="EVENT_SEQ",sequenceName="EVENT_SEQ",allocationSize=1)

	private int id;
	private String name;
	private String venue;
	private String description;
	/*@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd a z")*/
	private String dateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		
		this.dateTime = dateTime;
	}
	
	

}
