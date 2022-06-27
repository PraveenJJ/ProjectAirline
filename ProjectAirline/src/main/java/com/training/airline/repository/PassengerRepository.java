package com.training.airline.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.airline.model.Passenger;

/**
 * This is a repository interface for Passenger. It has all the repository
 * methods related to Passenger Entity. All the repository related operations
 * with the passenger are handled by this repository interface.
 * 
 * @author Praveen J
 */
@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Integer> {

	/**
	 * This method is used to get all the passengers with the given reference id,
	 * from the database and returns back list of passengers to the service class.
	 * This method uses a native query which is specified in the jpa named
	 * properties file.
	 * 
	 * @param referenceId
	 * @return list of passenger objects
	 * @throws SQLException
	 */
	@Query(nativeQuery = true)
	public List<Passenger> getBookingPassengersRepository(@Param("referenceId") Integer referenceId)
			throws SQLException;

	/**
	 * This method is used to save the passenger into the database using all the
	 * given details and return back the confirmation message for saving, to the
	 * service class. This is a named stored procedure, it's parameters are
	 * specified in the entity class.
	 * 
	 * @param referenceId
	 * @param name
	 * @param age
	 * @return confirmation message for saving
	 * @throws SQLException
	 */
	@Procedure(name = "passenger_save")
	String callPassengerSave(@Param("referenceId") Integer referenceId, @Param("name") String name,
			@Param("age") Integer age) throws SQLException;

	/**
	 * This method is used to cancel all the passengers having the given reference
	 * id in the database and returns back number of rows updated to the service
	 * class. This method uses a native query which is specified in the jpa named
	 * properties file. Since updation is being performed here, this method is
	 * annotated with @Modifying
	 * 
	 * @param referenceId
	 * @return number of rows updated
	 * @throws SQLException
	 */
	@Modifying
	@Query(nativeQuery = true)
	Integer cancelPassengerById(@Param("referenceId") Integer referenceId) throws SQLException;

	/**
	 * This method is used to cancel the passenger having the given ticket number in
	 * the database and returns back number of rows updated to the service class.
	 * This method uses a native query which is specified in the jpa named
	 * properties file. Since updation is being performed here, this method is
	 * annotated with @Modifying
	 * 
	 * @param ticketNumber
	 * @param referenceId
	 * @return number of rows updated
	 * @throws SQLException
	 */
	@Modifying
	@Query(nativeQuery = true)
	Integer cancelPassengerByTicketRepository(@Param("ticketNumber") Integer ticketNumber,
			@Param("referenceId") Integer referenceId) throws SQLException;

	/**
	 * This method is used to get the count of all the passengers with the given
	 * reference id having status as cancelled, from the database and returns back
	 * count to the service class. This method uses a native query which is
	 * specified in the jpa named properties file.
	 * 
	 * @param referenceId
	 * @return count of tickets having cancelled status
	 * @throws SQLException
	 */
	@Query(nativeQuery = true)
	Integer getCancelledTicketsCount(@Param("referenceId") Integer referenceId) throws SQLException;

}
