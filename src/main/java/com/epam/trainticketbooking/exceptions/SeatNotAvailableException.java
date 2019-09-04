package com.epam.trainticketbooking.exceptions;

public class SeatNotAvailableException extends RuntimeException{
	
	private static final long serialVersionUID = 6643086150242934286L;

	public SeatNotAvailableException(String message) {
		super(message);
	}
}
