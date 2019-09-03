package com.epam.trainticketbooking.exceptions;

public class DataWriteException extends RuntimeException{
	
	private static final long serialVersionUID = -170157896170149170L;

	public DataWriteException(String message) {
		super(message);
	}
}
