package com.epam.trainticketbooking.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String source;
	private String destination;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	private List<Station> stations;

	public Train(List<Station> stations, String sourceStation, String destinationStation) {
		super();
		this.source = sourceStation;
		this.destination = destinationStation;
		this.stations = stations;
	}

	public Train() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String sourceStation) {
		this.source = sourceStation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destinationStation) {
		this.destination = destinationStation;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", source=" + source + ", destination=" + destination + ", stations=" + stations
				+ "]";
	}

}
