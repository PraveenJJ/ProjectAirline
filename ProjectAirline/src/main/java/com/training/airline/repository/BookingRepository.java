package com.training.airline.repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.airline.model.Booking;

/**
 * This is a repository interface for Booking. It has all the repository methods
 * related to Booking Entity. All the repository related operations with the
 * booking are handled by this repository interface.
 * 
 * @author Praveen J
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

	/**
	 * This method is used to get all the bookings made by the user, from the
	 * database and returns back list of bookings to the service class. This method
	 * uses a native query which is specified in the jpa named properties file.
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	@Query(nativeQuery = true)
	public List<Booking> getBookingForUserRepository(@Param("userId") Integer userId) throws SQLException;

	/**
	 * This method is used to save the booking made by the user into the database
	 * using all the given details and return back the booking reference id to the
	 * service class. This is a named stored procedure, it's parameters are
	 * specified in the entity class.
	 * 
	 * @param userId
	 * @param flightIdentity
	 * @param date
	 * @param seats
	 * @param cost
	 * @return booking reference id
	 * @throws SQLException
	 */
	@Procedure(name = "booking_save")
	String callBookingSave(@Param("userId") Integer userId, @Param("flightId") String flightIdentity,
			@Param("date") Date date, @Param("seats") Integer seats, @Param("cost") Float cost) throws SQLException;

	/**
	 * This method is used to cancel the entire booking made by the user in the
	 * database and returns back number of rows updated to the service class. This
	 * method uses a native query which is specified in the jpa named properties
	 * file. Since updation is being performed here, this method is annotated
	 * with @Modifying
	 * 
	 * @param referenceId
	 * @return number of rows updated
	 * @throws SQLException
	 */
	@Modifying
	@Query(nativeQuery = true)
	Integer cancelBookingRepository(@Param("referenceId") Integer referenceId) throws SQLException;

	/**
	 * This method is used to get the booking using the given reference id, from the
	 * database and returns back the booking object to the service class. This
	 * method uses a native query which is specified in the jpa named properties
	 * file.
	 * 
	 * @param referenceId
	 * @return booking object
	 * @throws SQLException
	 */
	@Query(nativeQuery = true)
	Booking getBookingByReferenceIdRepository(@Param("referenceId") Integer referenceId) throws SQLException;

}
