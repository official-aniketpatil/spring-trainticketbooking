package com.epam.trainticketbooking.model;

import javax.persistence.Embeddable;

@Embeddable
public class Station {
	private String name;
	private long distance;
	
	public Station(String name, long distance) {
		super();
		this.name = name;
		this.distance = distance;
	}
	
	public Station() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + ", distance=" + distance + "]";
	}
}
