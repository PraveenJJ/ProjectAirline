package com.training.airline.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is a model class to hold booking information. It resembles the table in
 * the database under the name "alr_bookings". It has private fields to
 * represent the properties of each booking entity. It has getters and setters
 * to access and modify the private fields. A stored procedure is defined for
 * the booking in the database which is mapped to this entity class
 * using @NamedStoredProcedureQuery
 * 
 * @author Praveen J
 */
@Entity(name = "alr_bookings")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "booking_save", procedureName = "P_booking_sav", parameters = {
				// bookingRepository.callBookingSave(1, "A1", date, 1, 233.45f);
				// CALL P_booking_sav(:userId, :flightId, :date, :seats, :cost, :result)
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "flightId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = Date.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "seats", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "cost", type = Float.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1", type = String.class) }) })
public class Booking implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field referenceId of type Integer represents the booking reference id
	 * allocated to each booking. It is a primary key, and hence it is annotated
	 * with @Id. It is to be auto-incremented by the database, and so the
	 * GenerationType for Id is IDENTITY. It is mapped to a column named
	 * "reference_id" in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reference_id")
	private Integer referenceId;

	/**
	 * This field user of type User represents the user object. It is used to obtain
	 * a relation between User and Booking using the field user id as a join. It is
	 * mapped to a column named "user_id" in the database. Since many bookings are
	 * available with same user id, @ManyToOne is used here.
	 */
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	/**
	 * This field flight of type FlightAvail represents the flight available object.
	 * It is used to obtain a relation between Booking and FlightAvail using the
	 * field flight id and travel date as a join. It is mapped to a column named
	 * "flight_id" and "traveldate" in the database. Since many bookings are
	 * available with same flight id, @ManyToOne is used here.
	 */
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "flight_id", referencedColumnName = "flight_id"),
			@JoinColumn(name = "traveldate", referencedColumnName = "flight_date") })
	private FlightAvail flightAvail; 

	/**
	 * This field status of type String represents the status for a booking. It is
	 * mapped to a column named "status" in the database. It can have length upto 2.
	 */
	@Column(name = "status", length = 2)
	private String status;

	/**
	 * This field bookedSeats of type Integer represents the number of seats booked
	 * for a booking. It is mapped to a column named "bookseats" in the database.
	 */
	@Column(name = "bookseats")
	private Integer bookedSeats;

	/**
	 * This field represents the total cost for all the seats that is booked in the
	 * booking. It is mapped to a column named "totalcost" in the database. This
	 * Float type field has a precision of 10 and a scale of 2.
	 */
	@Column(name = "totalcost", precision = 10, scale = 2)
	private Float totalCost;

	/**
	 * This is a bidirectional mapping, which represents the list of passengers
	 * having the reference id as this booking. It is mapped by a variable named
	 * "referenceId" in Passenger model class.
	 * 
	 * @JsonIgnore is used to avoid the circular dependency that would occur between
	 *             User and Booking.
	 */
	@OneToMany(mappedBy = "booking")
	@JsonIgnore
	private List<Passenger> passengers;

	/**
	 * This is a zero argument constructor. Used to instantiate booking object.
	 */
	public Booking() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the booking object and then initialize the values with the
	 * arguments passed.
	 * 
	 * @param status      ---> status of the booking
	 * @param bookedSeats ---> number of booked seats
	 * @param totalCost   ---> total cost for all the seats booked
	 */
	public Booking(String status, Integer bookedSeats, Float totalCost) {
		this.status = status;
		this.bookedSeats = bookedSeats;
		this.totalCost = totalCost;
	}

	/**
	 * This is a method used to get the reference id from a booking object
	 * 
	 * @return the reference id given for each booking.
	 */
	public Integer getReferenceId() {
		return referenceId;
	}

	/**
	 * This is a method used to set the reference id for a booking object
	 * 
	 * @param referenceId The reference id of the booking is passed here.
	 */
	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * This is a method used to get the user from a booking object
	 * 
	 * @return the user given for each booking.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * This is a method used to set the user for a booking object
	 * 
	 * @param user The user of the booking is passed here.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * This is a method used to get the flight available object from a booking
	 * object
	 * 
	 * @return the flight available object given for each booking.
	 */
	public FlightAvail getFlightAvail() { 
		return flightAvail;
	}

	/**
	 * This is a method used to set the flight available object for a booking object
	 * 
	 * @param flightAvail The flight available object of the booking is passed here.
	 */
	public void setFlight(FlightAvail flightAvail) { 
		this.flightAvail = flightAvail;
	}

	/**
	 * This is a method used to get the status from a booking object
	 * 
	 * @return the status given for each booking.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This is a method used to set the status for a booking object
	 * 
	 * @param status The status of the booking is passed here.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * This is a method used to get the number of booked seats from a booking object
	 * 
	 * @return the number of booked seats given for each booking.
	 */
	public Integer getBookedSeats() {
		return bookedSeats;
	}

	/**
	 * This is a method used to set the number of booking seats for a booking object
	 * 
	 * @param bookedSeats The number of booking seats is passed here.
	 */
	public void setBookedSeats(Integer bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	/**
	 * This is a method used to get the total cost for all seats from a booking
	 * object
	 * 
	 * @return the total cost for all seats given for each booking.
	 */
	public Float getTotalCost() {
		return totalCost;
	}

	/**
	 * This is a method used to set the total cost for all seats for a booking
	 * object
	 * 
	 * @param totalCost The total cost for all seats is passed here.
	 */
	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * This is a method used to get the list of passengers from a booking object
	 * 
	 * @return the list of passengers given for each booking.
	 */
	public List<Passenger> getPassengers() {
		return passengers;
	}

	/**
	 * This is a method used to set the list of passengers for a booking object
	 * 
	 * @param passengers The list of passengers is passed here.
	 */
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers; 
	}

	/**
	 * hashCode() from the Object class is overridden with respect to the booking
	 * class. Objects.hash(Object... values) is invoked with the parameters as shown
	 * below.
	 * 
	 * @return the integer obtained from the hash method.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(bookedSeats, flightAvail, passengers, referenceId, status, totalCost, user);
	}

	/**
	 * equals method from object class is overridden to suit the booking class. This
	 * method is used to check the equality between two booking objects.
	 * 
	 * returns false if the object passed is not an instance of booking class.
	 * returns true if the object passed refers to this object. returns true if the
	 * object passed has the same id as this object, same flight as this object,
	 * same booked seats as this object, same passengers as this object, same status
	 * as this object, same total cost as this object, same user as this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Booking)) {
			return false;
		}
		Booking other = (Booking) obj;
		return Objects.equals(bookedSeats, other.bookedSeats) && Objects.equals(flightAvail, other.flightAvail)
				&& Objects.equals(passengers, other.passengers) && Objects.equals(referenceId, other.referenceId)
				&& Objects.equals(status, other.status) && Objects.equals(totalCost, other.totalCost)
				&& Objects.equals(user, other.user);
	}

	/**
	 * toString method from the object class is overridden to print the details of
	 * the booking object.
	 */
	@Override
	public String toString() {
		return "Booking [referenceId=" + referenceId + ", user=" + user + ", flightAvail=" + flightAvail + ", status=" + status
				+ ", bookedSeats=" + bookedSeats + ", totalCost=" + totalCost + ", passengers=" + passengers + "]";
	}

}
