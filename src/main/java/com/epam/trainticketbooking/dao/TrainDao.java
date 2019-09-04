package com.epam.trainticketbooking.dao;

import java.sql.Date;
import java.util.List;

import com.epam.trainticketbooking.model.Availability;
import com.epam.trainticketbooking.model.Train;

public interface TrainDao {

	public Train getById(long trainId);

	public List<Train> getByLocation(String source, String destination);

	public List<Train> getAll();

	public Availability getAvailability(long trainId, Date date);

	public Train save(Train train);

	public Train update(Train train);

	Availability setAvailability(Availability availability);

	Availability updateAvailability(Availability availability);
}
