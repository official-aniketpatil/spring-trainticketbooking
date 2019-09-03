package com.epam.trainticketbooking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.trainticketbooking.dao.TicketDao;
import com.epam.trainticketbooking.dao.TrainDao;
import com.epam.trainticketbooking.helper.BookingDetail;
import com.epam.trainticketbooking.model.Availability;
import com.epam.trainticketbooking.model.Passenger;
import com.epam.trainticketbooking.model.Station;
import com.epam.trainticketbooking.model.Ticket;
import com.epam.trainticketbooking.model.Train;
import com.epam.trainticketbooking.utility.DateConversion;

class BookingServiceTest {

	@Mock
	private TicketDao ticketDao;

	@Mock
	private TrainDao trainDao;

	private Availability availability;

	private BookingService bookingService;

	private BookingDetail bookingDetail;

	private Ticket ticket;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		availability = new Availability(1, DateConversion.convertToSqlDate("11-11-2011"), 10, 10);
		List<Passenger> passengers = new ArrayList<>();
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
		Passenger passenger = new Passenger("aniket", "male", "9145752925");
		passengers.add(passenger);
		String source = "chennai";
		String destination = "bhopal";
		Date date = DateConversion.convertToSqlDate("11-11-2011");
		String seatType = "AC";
		int seatCount = 10;
		long trainId = 1;
		ticket = new Ticket(seatType, source, destination, passengers, trainId, 100);
		bookingDetail = new BookingDetail(passengers, source, destination, date, seatType, seatCount, trainId);
		bookingService = new BookingService(ticketDao, trainDao);
		when(trainDao.getAvailability(any(Long.class), any(Date.class))).thenReturn(availability);
		when(trainDao.getById(any(Long.class))).thenReturn(train);
		when(ticketDao.book(any(BookingDetail.class))).thenReturn(ticket);
		when(trainDao.updateAvailability(any(Availability.class))).thenReturn(availability);
	}

	@Test
	public void testBookTicket() {
		Ticket actual = bookingService.bookTicket(bookingDetail);
		assertEquals(ticket, actual);
		bookingDetail.setSeatType("SLEEPER");
		Ticket actualTicket = bookingService.bookTicket(bookingDetail);
		assertEquals(actualTicket, actual);
	}

	@Test
	public void testIsSeatAvailableToBook() {

	}
}
