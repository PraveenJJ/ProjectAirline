package com.training.airline.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * This is a java class used to act as a composite key generator for the
 * FlightAvail class. It has private fields which are part of the composite key
 * in the FlightAvail class. It has getters and setters to access and modify the
 * private fields.
 * 
 * @author Praveen J
 */
public class FlightAvailId implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field flightId of type Flight represents the flight object. This field
	 * is part of the composite key.
	 */
	private Flight flight; 

	/**
	 * This field flightDate of type Date represents the flight date. This field is
	 * part of the composite key.
	 */
	private Date flightDate;

	/**
	 * This is a zero argument constructor. Used to instantiate flight available id
	 * object.
	 */
	public FlightAvailId() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the flight available object and then initialize the values with
	 * the arguments passed.
	 * 
	 * @param flight   ---> Flight object which has the flight id that the flight
	 *                   available id object uses
	 * @param flightDate ---> The flight date
	 */
	public FlightAvailId(Flight flight, Date flightDate) {
		this.flight = flight; 
		this.flightDate = flightDate;
	}

	/**
	 * This is a method used to get the flight object from a flight available id
	 * object
	 * 
	 * @return the flight object given for each flight available object.
	 */
	public Flight getFlight() { 
		return flight;
	}

	/**
	 * This is a method used to set the flight object for a flight available id
	 * object
	 * 
	 * @param flight The flight object is passed here to set the flight id
	 *                 reference.
	 */
	public void setFlight(Flight flight) { 
		this.flight = flight;
	}

	/**
	 * This is a method used to get the flight date from a flight available id
	 * object
	 * 
	 * @return the flight date given for each flight available object.
	 */
	public Date getFlightDate() {
		return flightDate;
	}

	/**
	 * This is a method used to set the flight date for a flight available id object
	 * 
	 * @param flightDate The flight date is passed here.
	 */
	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	/**
	 * hashCode() from the Object class is overridden with respect to the flight
	 * available id class. Objects.hash(Object... values) is invoked with the
	 * parameters as shown below.
	 * 
	 * @return the integer obtained from the hash method.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(flightDate, flight);
	}

	/**
	 * equals method from object class is overridden to suit the flight available id
	 * class. This method is used to check the equality between two flight available
	 * id objects.
	 * 
	 * returns false if the object passed is not an instance of flight available id
	 * class returns true if the object passed refers to this object. returns true
	 * if the object passed has the same id as this object, same flight date as this
	 * object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FlightAvailId)) {
			return false;
		}
		FlightAvailId other = (FlightAvailId) obj;
		return Objects.equals(flightDate, other.flightDate) && Objects.equals(flight, other.flight);
	}

}
