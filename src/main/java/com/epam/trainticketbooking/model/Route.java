package com.epam.trainticketbooking.model;

import javax.persistence.Embeddable;

@Embeddable
public class Route {

	private String source;
	private String destination;
	private long distance;

	public Route(String source, String destination, long distance) {
		super();
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	public Route() {

	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestinationId() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Route [source=" + source + ", destination=" + destination + ", distance=" + distance + "]";
	}

}
