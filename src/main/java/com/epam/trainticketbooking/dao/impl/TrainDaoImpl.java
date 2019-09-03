package com.epam.trainticketbooking.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.trainticketbooking.dao.TrainDao;
import com.epam.trainticketbooking.exceptions.DataAccessException;
import com.epam.trainticketbooking.exceptions.DataWriteException;
import com.epam.trainticketbooking.model.Availability;
import com.epam.trainticketbooking.model.AvailabilityId;
import com.epam.trainticketbooking.model.Train;

@Component
public class TrainDaoImpl implements TrainDao {
	private static Logger logger = LogManager.getLogger(TrainDaoImpl.class);

	private static final String GET_ALL_TRAINS = "select train from Train train";
	private static final String GET_BY_LOCATION = "select train from Train train " + "JOIN train.stations s "
			+ "where s.name = :source and train.id in(select train.id from Train train "
			+ "JOIN train.stations s where s.name = :destination)";

	private static final String TRAIN_FETCH_ERROR_MESSSAGE = "Problem occured while fetching trains";
	private static final String PERSISTENCE_UNIT = "local-mysql";

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	@Override
	public Train save(Train train) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(train);
			entityManager.flush();
			entityTransaction.commit();
			return train;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataWriteException("Problem Occured while saving train data");
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public Train update(Train train) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.merge(train);
			entityTransaction.commit();
			return train;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataWriteException("Unable to update train");
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public Train getById(long trainId) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			Train train = entityManager.find(Train.class, trainId);
			entityTransaction.commit();
			return train;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataAccessException("Unable to get train Details");
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public List<Train> getByLocation(String source, String destination) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			Query query = entityManager.createQuery(GET_BY_LOCATION);
			query.setParameter("source", source);
			query.setParameter("destination", destination);
			@SuppressWarnings("unchecked")
			List<Train> trains = query.getResultList();
			entityTransaction.commit();
			return trains;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataAccessException(TRAIN_FETCH_ERROR_MESSSAGE);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public List<Train> getAll() {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			Query query = entityManager.createQuery(GET_ALL_TRAINS);
			@SuppressWarnings("unchecked")
			List<Train> trains = query.getResultList();
			entityTransaction.commit();
			return trains;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataAccessException(TRAIN_FETCH_ERROR_MESSSAGE);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public Availability getAvailability(long trainId, Date date) {
		try {
			AvailabilityId availabilityId = new AvailabilityId(trainId, date);
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			Availability availability = entityManager.find(Availability.class, availabilityId);
			entityTransaction.commit();
			return availability;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataAccessException("unable to get Availability of train");
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public Availability setAvailability(Availability availability) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.persist(availability);
			entityTransaction.commit();
			return availability;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataAccessException("unable to set Availability of train");
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public Availability updateAvailability(Availability availability) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(availability);
			entityTransaction.commit();
			return availability;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataAccessException("unable to update Availability of train");
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

}
