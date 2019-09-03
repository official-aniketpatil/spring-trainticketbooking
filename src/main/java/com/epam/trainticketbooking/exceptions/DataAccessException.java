package com.epam.trainticketbooking.exceptions;

public class DataAccessException extends RuntimeException{
	
	private static final long serialVersionUID = 1144761697963478533L;

	public DataAccessException(String message) {
		super(message);
	}
}
