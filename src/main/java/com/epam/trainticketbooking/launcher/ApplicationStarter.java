package com.epam.trainticketbooking.launcher;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.trainticketbooking.dao.TrainDao;
import com.epam.trainticketbooking.dao.impl.TrainDaoImpl;
import com.epam.trainticketbooking.helper.BookingDetail;
import com.epam.trainticketbooking.model.Availability;
import com.epam.trainticketbooking.model.Passenger;
import com.epam.trainticketbooking.model.Station;
import com.epam.trainticketbooking.model.Train;
import com.epam.trainticketbooking.services.BookingService;
import com.epam.trainticketbooking.services.TrainService;
import com.epam.trainticketbooking.utility.DateConversion;

public class ApplicationStarter {
	private static Logger logger = LogManager.getLogger(ApplicationStarter.class);
	private static final int SEARCH_TRAINS = 1;
	private static final int BOOK_TRAIN = 2;
	private static final int LOAD_DATA = 3;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				"com.epam.trainticketbooking");
		TrainService trainService = context.getBean("trainService", TrainService.class);
		BookingService bookingService = context.getBean("bookingService", BookingService.class);
		ConsoleOperations.showServiceMenu();
		int choice = ConsoleOperations.getInt();

		if (choice == SEARCH_TRAINS) {
			ConsoleOperations.showAvailableTrainsMenu();
			String source = ConsoleOperations.getString();
			String destination = ConsoleOperations.getString();
			Date date = ConsoleOperations.getDate();
			List<Train> trains = trainService.findTrains(source, destination, date);
			logger.trace(trains.toString());
		} else if (choice == BOOK_TRAIN) {
			ConsoleOperations.showAvailableTrainsMenu();
			String source = ConsoleOperations.getString();
			String destination = ConsoleOperations.getString();
			Date date = ConsoleOperations.getDate();
			List<Train> trains = trainService.findTrains(source, destination, date);
			logger.trace(trains.toString());
			ConsoleOperations.showTrainBookingMenu();
			long trainId = ConsoleOperations.getLong();
			String seatType = ConsoleOperations.getString();
			int seatCount = ConsoleOperations.getInt();
			List<Passenger> passengers = new ArrayList<>();
			for (int i = 0; i < seatCount; i++) {
				passengers.add(ConsoleOperations.getPassenger());
			}
			BookingDetail bookingDetail = new BookingDetail(passengers, source, destination, date, seatType, seatCount,
					trainId);
			bookingService.bookTicket(bookingDetail);
		} else if (choice == LOAD_DATA) {
			Availability dayOne = new Availability(1, DateConversion.convertToSqlDate("11-11-2011"), 10, 10);
			Availability dayTwo = new Availability(1, DateConversion.convertToSqlDate("12-11-2011"), 10, 10);
			Station s0 = new Station("chennai", 0);
			Station s1 = new Station("hyderabad", 300);
			Station s2 = new Station("pune", 500);
			Station s3 = new Station("bhopal", 900);
			List<Station> stations = new ArrayList<>();
			stations.add(s0);
			stations.add(s1);
			stations.add(s2);
			stations.add(s3);
			Train train = new Train(stations, "chennai", "bhopal");
			TrainDao trainDao = new TrainDaoImpl();
			trainDao.save(train);
			trainDao.setAvailability(dayOne);
			trainDao.setAvailability(dayTwo);
			logger.info("train data has been loaded");
		} else {
			logger.error("Enter a valid choice");
		}
		context.close();
	}

}
