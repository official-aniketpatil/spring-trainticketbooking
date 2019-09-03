package com.epam.trainticketbooking.services;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.trainticketbooking.dao.TrainDao;
import com.epam.trainticketbooking.model.Availability;
import com.epam.trainticketbooking.model.Train;

@Component
public class TrainService {

	@Autowired
	private TrainDao trainDao;

	public TrainService(TrainDao trainDao) {
		this.trainDao = trainDao;
	}

	public List<Train> findTrains(String source, String destination, Date date) {
		List<Train> trains = trainDao.getByLocation(source, destination);
		Iterator<Train> trainIterator = trains.listIterator();
		while (trainIterator.hasNext()) {
			Train train = trainIterator.next();
			Availability availability = trainDao.getAvailability(train.getId(), date);
			if (availability == null) {
				trainIterator.remove();
			}
		}
		return trains;
	}

	public Availability getAvailability(long trainId, Date date) {
		return trainDao.getAvailability(trainId, date);
	}
}
