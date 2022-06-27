package com.training.airline.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is a model class to hold available flight information. It resembles the
 * table in the database under the name "alr_flightavail". Since this class has
 * a composite primary key, it uses FlightAvailId class to generate ID. It has
 * private fields to represent the properties of each Flight entity. It has
 * getters and setters to access and modify the private fields.
 * 
 * @author Praveen J
 */
@IdClass(FlightAvailId.class)
@Entity(name = "alr_flightavail")
public class FlightAvail implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field flightId of type Flight represents the flight object. It is used
	 * to obtain a relation between Flight and FlightAvail using the field flight id
	 * as a join. It is a primary key, and hence it is annotated with @Id. It is
	 * mapped to a column named "flight_id" in the database. Since many flights are
	 * available with same flight id, @ManyToOne is used here.
	 */
	@Id
	@ManyToOne
	@JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
	private Flight flight; 

	/**
	 * This field represents the flight date. It is a primary key, and hence it is
	 * annotated with @Id. It is mapped to a column named "flight_date" in the
	 * database.
	 */
	@Id
	@Column(name = "flight_date")
	private Date flightDate;

	/**
	 * This field represents the number of seats available to book. It is mapped to
	 * a column named "seats" in the database.
	 */
	@Column(name = "seats")
	private Integer seats;

	/**
	 * This field represents the cost for each seat that is available to book. It is
	 * mapped to a column named "cost" in the database. This Float type field has a
	 * precision of 8 and a scale of 2.
	 */
	@Column(name = "cost", precision = 8, scale = 2)
	private Float cost;

	/**
	 * This is a bidirectional mapping, which represents the list of bookings for
	 * this flight on a given date. It is mapped by a variable named "flight" in
	 * Booking model class.
	 * 
	 * @JsonIgnore is used to avoid the circular dependency that would occur between
	 *             FlightAvail and Booking.
	 */
	@OneToMany(mappedBy = "flightAvail", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Booking> flightBookings;

	/**
	 * This is a zero argument constructor. Used to instantiate flight available
	 * object.
	 */
	public FlightAvail() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the flight available object and then initialize the values with
	 * the arguments passed.
	 * 
	 * @param flightDate ---> The flight date
	 * @param seats      ---> The number of seats available to book
	 * @param cost       ---> The cost of each seat
	 */
	public FlightAvail(Date flightDate, int seats, float cost) {
		this.flightDate = flightDate;
		this.seats = seats;
		this.cost = cost;
	}

	/**
	 * This is a method used to get the flight object from a flight available object
	 * 
	 * @return the flight object given for each flight available object.
	 */
	public Flight getFlight() { 
		return flight;
	}

	/**
	 * This is a method used to set the flight object for a flight available object
	 * 
	 * @param flight The flight object is passed here to set the flight id
	 *                 reference.
	 */
	public void setFlight(Flight flight) {  
		this.flight = flight;
	}

	/**
	 * This is a method used to get the flight date from a flight available object
	 * 
	 * @return the flight date given for each flight available object.
	 */
	public Date getFlightDate() {
		return flightDate;
	}

	/**
	 * This is a method used to set the flight date for a flight available object
	 * 
	 * @param flightDate The flight date is passed here.
	 */
	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	/**
	 * This is a method used to get the seats available from a flight available
	 * object
	 * 
	 * @return the seats available for each flight available object.
	 */
	public Integer getSeats() {
		return seats;
	}

	/**
	 * This is a method used to set the seats available for a flight available
	 * object
	 * 
	 * @param seats The seats available is passed here.
	 */
	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	/**
	 * This is a method used to get the cost of each seat from a flight available
	 * object
	 * 
	 * @return the cost for each seat for a flight available object.
	 */
	public Float getCost() {
		return cost;
	}

	/**
	 * This is a method used to set the cost of each seat available for a flight
	 * available object
	 * 
	 * @param cost The cost of each seat available is passed here.
	 */
	public void setCost(Float cost) {
		this.cost = cost;
	}

	/**
	 * This is a method used to get the list of flight bookings from a flight
	 * available object
	 * 
	 * @return the list of flight bookings for this flight on a given date.
	 */
	public List<Booking> getFlightBookings() {
		return flightBookings;
	}

	/**
	 * This is a method used to set the list of flight bookings for a flight
	 * available object
	 * 
	 * @param flightBookings List of flight bookings is passed here.
	 */
	public void setFlightBookings(List<Booking> flightBookings) {
		this.flightBookings = flightBookings;
	}

	/**
	 * hashCode() from the Object class is overridden with respect to the flight
	 * available class. Objects.hash(Object... values) is invoked with the
	 * parameters as shown below.
	 * 
	 * @return the integer obtained from the hash method.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cost, flightBookings, flightDate, flight, seats);
	}

	/**
	 * equals method from object class is overridden to suit the flight available
	 * class. This method is used to check the equality between two flight available
	 * objects.
	 * 
	 * returns false if the object passed is not an instance of flight available
	 * class returns true if the object passed refers to this object. returns true
	 * if the object passed has the same id as this object, same flight date as this
	 * object, same cost for each seat as this object, same available seats as this
	 * object, same list of flight bookings as this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FlightAvail)) {
			return false;
		}
		FlightAvail other = (FlightAvail) obj;
		return Objects.equals(cost, other.cost) && Objects.equals(flightBookings, other.flightBookings)
				&& Objects.equals(flightDate, other.flightDate) && Objects.equals(flight, other.flight)
				&& Objects.equals(seats, other.seats);
	}

	/**
	 * toString method from the object class is overridden to print the details of
	 * the flight available object.
	 */
	@Override
	public String toString() {
		return "FlightAvail [flight=" + flight + ", flightDate=" + flightDate + ", seats=" + seats + ", cost="
				+ cost + "]";
	}

}
