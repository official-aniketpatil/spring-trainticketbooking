package com.epam.trainticketbooking.launcher;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.trainticketbooking.model.Passenger;
import com.epam.trainticketbooking.utility.DateConversion;

public class ConsoleOperations {
	private static Logger logger = LogManager.getLogger(ConsoleOperations.class);
	private static Scanner scanner = new Scanner(System.in);

	private ConsoleOperations() {

	}

	public static void showServiceMenu() {
		logger.trace("Enter\n1)Show Available Trains");
		logger.trace("2)Book train");
		logger.trace("3)Load data");
	}

	public static void showAvailableTrainsMenu() {
		logger.trace("Enter\n1)source");
		logger.trace("2)Destination");
		logger.trace("3)Date of travelling i.e (dd-MM-yyyy)");
	}

	public static void showTrainBookingMenu() {
		logger.trace("Enter train number you would like to go with");
		logger.trace("Enter Seat Type(AC/SLEEPER)");
		logger.trace("Enter number of tickets you wants to book");
	}

	public static int getInt() {
		int choice = -1;
		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException ex) {
			logger.error(ex.getMessage());
		}
		return choice;
	}

	public static String getString() {
		String choice = null;
		try {
			choice = scanner.next();
		} catch (InputMismatchException ex) {
			logger.error(ex.getMessage());
		}
		return choice;
	}

	public static Long getLong() {
		long choice = -1;
		try {
			choice = scanner.nextLong();
		} catch (InputMismatchException ex) {
			logger.error(ex.getMessage());
		}
		return choice;
	}

	public static Date getDate() {
		return DateConversion.convertToSqlDate(scanner.next().trim());

	}

	public static Passenger getPassenger() {
		logger.trace("Enter passenger");
		logger.trace("name");
		logger.trace("gender");
		logger.trace("mobile");
		String name = getString();
		String gender = getString();
		String mobile = getString();

		return new Passenger(name, gender, mobile);
	}
}
