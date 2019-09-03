package com.epam.trainticketbooking.model;

import java.io.Serializable;
import java.sql.Date;

public class AvailabilityId implements Serializable {
	private static final long serialVersionUID = 6192185485845732535L;
	private long trainId;
	private Date date;

	public AvailabilityId(long trainId, Date date) {
		super();
		this.trainId = trainId;
		this.date = date;
	}

	public AvailabilityId() {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (trainId ^ (trainId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AvailabilityId)) {
			return false;
		}
		AvailabilityId availabilityId = (AvailabilityId)obj;
		return trainId == availabilityId.getTrainId() && date.equals(availabilityId.getDate());
	}

}
