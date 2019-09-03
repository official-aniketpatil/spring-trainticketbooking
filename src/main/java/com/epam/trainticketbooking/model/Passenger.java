package com.epam.trainticketbooking.model;

import javax.persistence.Embeddable;

@Embeddable
public class Passenger {
	private String name;
	private String gender;
	private String mobile;
	
	public Passenger() {
		
	}
	
	public Passenger(String name, String gender, String mobile) {
		super();
		this.name = name;
		this.gender = gender;
		this.mobile = mobile;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}

