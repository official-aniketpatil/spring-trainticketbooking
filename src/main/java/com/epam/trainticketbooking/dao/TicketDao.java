package com.epam.trainticketbooking.dao;

import java.util.List;

import com.epam.trainticketbooking.helper.BookingDetail;
import com.epam.trainticketbooking.model.Ticket;

public interface TicketDao {

	public boolean cancel();

	public Ticket book(BookingDetail bookingDetail);
	
	public Ticket getById(long ticketId);
	
	public List<Ticket> getByTrain(long trainId);
}
