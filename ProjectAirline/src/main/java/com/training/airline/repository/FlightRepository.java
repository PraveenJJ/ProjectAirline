package com.training.airline.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.airline.model.Flight;

/**
 * This is a repository interface for Flight. It has all the repository methods
 * related to Flight Entity. All the repository related operations with the
 * flight are handled by this repository interface.
 * 
 * @author Praveen J
 */
@Repository
public interface FlightRepository extends CrudRepository<Flight, String> {

	/**
	 * This method is used to find all the flights between the given origin city id
	 * and destination city id from the database, and returns back the list of
	 * flights to the service class. This method uses a native query which is
	 * specified in the jpa named properties file.
	 * 
	 * @param origin
	 * @param destination
	 * @return list of flights
	 * @throws SQLException
	 */
	@Query(nativeQuery = true)
	public List<Flight> getFlightsByRouteRepository(@Param("origin") Integer origin,
			@Param("destination") Integer destination) throws SQLException;

}
