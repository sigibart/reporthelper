package com.cujo.reporthelper.model;

public class Agent {
	
	private int id;	
	private int status;
	private Double latitude;	
	private Double longitude;

	
	//Getters And Setters
	public int getId() {
		return id;
	}
	public Agent() {
	super();
}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}
