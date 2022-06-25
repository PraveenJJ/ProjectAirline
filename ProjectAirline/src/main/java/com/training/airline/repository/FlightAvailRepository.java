package com.training.airline.repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.airline.model.FlightAvail;

/**
 * This is a repository interface for FlightAvail. It has all the repository
 * methods related to FlightAvail Entity. All the repository related operations
 * with the flightAvail are handled by this repository interface.
 * 
 * @author 251656
 *
 */
@Repository
public interface FlightAvailRepository extends CrudRepository<FlightAvail, String> {

	/**
	 * This method is used to find the flight available object for the given flight
	 * id on a given date from the database, and returns back the available flight
	 * object to the service class. This method uses a native query which is
	 * specified in the jpa named properties file.
	 * 
	 * @param flightId
	 * @param travelOn
	 * @return flight available object
	 * @throws SQLException
	 */
	@Query(nativeQuery = true)
	public FlightAvail getFlightsAvailableRepository(@Param("flightId") String flightId,
			@Param("travelOn") Date travelOn) throws SQLException;

	/**
	 * This method is used to find the list of available flight objects for the
	 * given flight id, between the given start date and end date range from the
	 * database, and returns back the list of available flight objects to the
	 * service class. This method uses a native query which is specified in the jpa
	 * named properties file.
	 * 
	 * @param flightId
	 * @param startDate
	 * @param endDate
	 * @return list of available flight objects
	 * @throws SQLException
	 */
	@Query(nativeQuery = true)
	public List<FlightAvail> getFlightsAvailableOnDateRangeRepository(@Param("flightId") String flightId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate) throws SQLException;

	/**
	 * This method is used to update the number of seats available for a given
	 * flight id on a given date in the database, and returns back the number of
	 * rows updated to the service class. This method uses a native query which is
	 * specified in the jpa named properties file.
	 * 
	 * @param updateSeats
	 * @param flightId
	 * @param travelOn
	 * @return number of rows updated
	 * @throws SQLException
	 */
	@Modifying
	@Query(nativeQuery = true)
	public int updateFlightAvailSeatsRepository(@Param("updatedSeats") Integer updateSeats,
			@Param("flightId") String flightId, @Param("travelOn") Date travelOn) throws SQLException;

}
