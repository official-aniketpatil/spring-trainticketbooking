package com.epam.trainticketbooking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.trainticketbooking.dao.TrainDao;
import com.epam.trainticketbooking.model.Availability;
import com.epam.trainticketbooking.model.Station;
import com.epam.trainticketbooking.model.Train;
import com.epam.trainticketbooking.utility.DateConversion;

class TrainServiceTest {

	@Mock
	private TrainDao trainDao;

	private List<Train> trains;
	
	private TrainService trainService;
	
	private Availability availability;
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		availability = new Availability(1, DateConversion.convertToSqlDate("11-11-2011"), 10, 10);
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
		trains = new ArrayList<>();
		trains.add(train);
		trainService = new TrainService(trainDao);
		when(trainDao.getByLocation(any(String.class), any(String.class))).thenReturn(trains);
		when(trainDao.getAvailability(any(Long.class), any(Date.class))).thenReturn(availability);
	}

	@Test
	public void testFindTrains() {
		List<Train> actual = trainService.findTrains("chennai", "bhopal", DateConversion.convertToSqlDate("11-11-2011"));
		assertEquals(trains, actual);
		verify(trainDao).getByLocation(any(String.class),any(String.class));
		verify(trainDao).getAvailability(any(Long.class), any(Date.class));
	}
	
	@Test
	public void testGetAvailability() {
		Availability actual = trainService.getAvailability(1, DateConversion.convertToSqlDate("14-11-2011"));
		assertEquals(availability, actual);
	}

}
