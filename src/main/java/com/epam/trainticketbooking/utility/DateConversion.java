package com.epam.trainticketbooking.utility;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DateConversion {
	private static Logger logger = LogManager.getLogger(DateConversion.class);
	
	private DateConversion() {
		
	}
	
	public static Date convertToSqlDate(String inputDate) {
		Date date = null;
		java.util.Date utilDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			utilDate = format.parse(inputDate);
			date = new Date(utilDate.getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return date;	
	}
}
