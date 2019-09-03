package com.epam.trainticketbooking.services;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.trainticketbooking.dao.TicketDao;
import com.epam.trainticketbooking.dao.TrainDao;
import com.epam.trainticketbooking.helper.BookingDetail;
import com.epam.trainticketbooking.model.Availability;
import com.epam.trainticketbooking.model.Station;
import com.epam.trainticketbooking.model.Ticket;
import com.epam.trainticketbooking.model.Train;

@Component
public class BookingService {

	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private TrainDao trainDao;
	private static final Double AC_FARE = 4.5;
	private static final Double SLEEPER_FARE = 3.0;
	private static Logger logger = LogManager.getLogger(BookingService.class);

	public BookingService(TicketDao ticketDao, TrainDao trainDao) {
		this.trainDao = trainDao;
		this.ticketDao = ticketDao;
	}

	public synchronized Ticket bookTicket(BookingDetail bookingDetail) {
		Ticket ticket = null;
		long trainId = bookingDetail.getTrainId();
		Date date = bookingDetail.getDate();
		String seatType = bookingDetail.getSeatType();
		int seatCount = bookingDetail.getSeatCount();
		String source = bookingDetail.getSource();
		String destination = bookingDetail.getDestination();
		Availability availability = trainDao.getAvailability(trainId, date);
		if (isSeatAvailableToBook(availability, seatType, seatCount)) {
			Train train = trainDao.getById(trainId);
			long distance = computeDistanceBetweenStations(train, source, destination);
			double fare = computeFare(distance, seatType);
			bookingDetail.setFare(fare);
			bookingDetail.setTrain(train);
			ticket = ticketDao.book(bookingDetail);
			updateSeatCount(availability, seatType, seatCount);
			trainDao.updateAvailability(availability);
			logger.trace("seat booked");
		} else {
			logger.trace("not able to book seat");
		}
		return ticket;
	}

	private boolean isSeatAvailableToBook(Availability availability, String seatType, int seatCount) {
		if (seatType.equalsIgnoreCase("AC")) {
			return availability.getAcSeats() >= seatCount;
		}
		return availability.getSleeperSeats() >= seatCount;
	}

	private void updateSeatCount(Availability availability, String seatType, int seatCount) {
		if (seatType.equalsIgnoreCase("AC")) {
			int newSeatCount = availability.getAcSeats() - seatCount;
			availability.setAcSeats(newSeatCount);
		} else if (seatType.equalsIgnoreCase("SLEEPER")) {
			int newSeatCount = availability.getSleeperSeats() - seatCount;
			availability.setSleeperSeats(newSeatCount);
		}
	}

	private double computeFare(long distance, String seatType) {
		if (seatType.equalsIgnoreCase("AC")) {
			return distance * AC_FARE;
		}
		return distance * SLEEPER_FARE;
	}

	private long computeDistanceBetweenStations(Train train, String source, String destination) {
		List<Station> stations = train.getStations();
		long distanceToSource = 0;
		long distanceToDestination = 0;

		for (Station station : stations) {
			if (station.getName().equalsIgnoreCase(source)) {
				distanceToSource = station.getDistance();
			} else if (station.getName().equalsIgnoreCase(destination)) {
				distanceToDestination = station.getDistance();
			}
		}
		return distanceToDestination - distanceToSource;
	}
}
