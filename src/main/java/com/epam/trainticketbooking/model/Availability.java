package com.epam.trainticketbooking.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(value = AvailabilityId.class)
public class Availability {
	@Id
	private long trainId;
	@Id
	private Date date;
	private int acSeats;
	private int sleeperSeats;

	public Availability() {
	}

	public Availability(long trainId, Date date, int acSeats, int sleeperSeats) {
		this.trainId = trainId;
		this.date = date;
		this.acSeats = acSeats;
		this.sleeperSeats = sleeperSeats;
	}

	public long getTrainId() {
		return trainId;
	}

	public void setTrainId(long trainId) {
		this.trainId = trainId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAcSeats() {
		return acSeats;
	}

	public void setAcSeats(int acSeats) {
		this.acSeats = acSeats;
	}

	public int getSleeperSeats() {
		return sleeperSeats;
	}

	public void setSleeperSeats(int sleeperSeats) {
		this.sleeperSeats = sleeperSeats;
	}

	@Override
	public String toString() {
		return "Availability [date=" + date + ", acSeats=" + acSeats + ", sleeperSeats=" + sleeperSeats + "]";
	}

}