package com.epam.trainticketbooking.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.trainticketbooking.helper.BookingDetail;
import com.epam.trainticketbooking.model.Availability;
import com.epam.trainticketbooking.model.Passenger;
import com.epam.trainticketbooking.model.Train;
import com.epam.trainticketbooking.services.BookingService;
import com.epam.trainticketbooking.services.TrainService;
import com.epam.trainticketbooking.utility.DateConversion;

@WebServlet(urlPatterns = { "/train", "/book" })
public class TrainController extends HttpServlet {

	private static final long serialVersionUID = -4838676184738977955L;
	private static Logger logger = LogManager.getLogger(TrainController.class);

	private final TrainService trainService;
	private final BookingService bookingService;
	private final AnnotationConfigApplicationContext context;

	public TrainController() {
		context = new AnnotationConfigApplicationContext("com.epam.trainticketbooking");
		this.trainService = context.getBean(TrainService.class);
		this.bookingService = context.getBean(BookingService.class);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String source = request.getParameter("source").trim();
		String destination = request.getParameter("destination").trim();
		String inputDate = request.getParameter("date").trim();
		Date date = DateConversion.convertToSqlDate(inputDate);
		List<Train> trains = trainService.findTrains(source, destination, date);
		List<Availability> availabilities = new ArrayList<>();
		for (Train train : trains) {
			availabilities.add(trainService.getAvailability(train.getId(), date));
		}
		request.setAttribute("availabilities", availabilities);
		request.setAttribute("trainList", trains);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("show-trains.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		List<Passenger> passengers = new ArrayList<>();
		try {
			int passengerCount = Integer.parseInt(request.getParameter("passengerCount"));
			String seatType = request.getParameter("seatType").trim();
			String source = request.getParameter("source").trim();
			String destination = request.getParameter("destination").trim();
			long trainId = Long.parseLong(request.getParameter("trainId"));
			Date date = DateConversion.convertToSqlDate(request.getParameter("date"));
			passengers = getPassengers(request);
			BookingDetail bookingDetail = new BookingDetail(passengers, source, destination, date, seatType,
					passengerCount, trainId);
			bookingService.bookTicket(bookingDetail);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.jsp");
			requestDispatcher.forward(request, response);
		} catch (NumberFormatException | ServletException | IOException ex) {
			logger.error(ex.getMessage());
		}
	}

	private List<Passenger> getPassengers(HttpServletRequest request) {
		List<Passenger> passengers = new ArrayList<>();
		String[] passengerWithNames = request.getParameterMap().get("passengerName");
		String[] passengerWithMobileNumber = request.getParameterMap().get("mobile");
		String[] passengerWithGender = request.getParameterMap().get("gender");
		int passengerCount = passengerWithNames.length;
		for (int i = 0; i < passengerCount; i++) {
			String name = passengerWithNames[i];
			String gender = passengerWithGender[i];
			String mobile = passengerWithMobileNumber[i];
			Passenger passenger = new Passenger(name, gender, mobile);
			passengers.add(passenger);
		}
		return passengers;
	}
}
