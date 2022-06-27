package com.training.airline.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * This is a Dto class to hold flight information. It has private fields to
 * represent the properties of each Flight entity. It has getters and setters to
 * access and modify the private fields.
 * 
 * @author Praveen J
 */
public class FlightDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field flightId of type String represents the flight id allocated to each
	 * flight.
	 */
	private String flightId;

	/**
	 * This field holds the origin city id for a flight. It is coming from the City
	 * Dto.
	 */
	private CityDto originCity;

	/**
	 * This field holds the destination city id for a flight. It is coming from the
	 * City Dto.
	 */
	private CityDto destinationCity;

	/**
	 * This field represents the departure time for a flight.
	 */
	private Date departureTime;

	/**
	 * This field represents the arrival time for a flight.
	 */
	private Date arrivalTime;

	/**
	 * This field represents the list of available flights dto that has flight id as this flight.
	 */
	private List<FlightAvailDto> availableFlights;

	/**
	 * This is a zero argument constructor. Used to instantiate flight dto object.
	 */
	public FlightDto() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the flight dto object and then initialize the values with the
	 * arguments passed.
	 * 
	 * @param flightId      ---> id of the flight
	 * @param departureTime ---> departure time of the flight
	 * @param arrivalTime   ---> arrival time of the flight
	 */
	public FlightDto(String flightId, Date departureTime, Date arrivalTime) {
		this.flightId = flightId;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	/**
	 * This is a method used to get the flight id from a flight dto object
	 * 
	 * @return the flight id given for each flight.
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * 
	 * This is a method used to set the flight id for a flight dto object
	 *
	 * @param flightId The id of the flight is passed here.
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	/**
	 * This is a method used to get the origin city dto object from a flight dto object
	 * 
	 * @return the origin city dto object for each flight.
	 */
	public CityDto getOriginCity() {
		return originCity;
	}

	/**
	 * This is a method used to set the origin city dto object for a flight dto object
	 * 
	 * @param originCity The origin city dto object of the flight is passed here.
	 */
	public void setOriginCity(CityDto originCity) {
		this.originCity = originCity;
	}

	/**
	 * This is a method used to get the destination city dto object from a flight dto object
	 * 
	 * @return The destination city dto object for each flight.
	 */
	public CityDto getDestinationCity() {
		return destinationCity;
	}

	/**
	 * This is a method used to set the destination city dto object for a flight dto object
	 * 
	 * @param destinationCity The destination city dto object of the flight is passed
	 *                        here.
	 */
	public void setDestinationCity(CityDto destinationCity) {
		this.destinationCity = destinationCity;
	}

	/**
	 * This is a method used to get the list of available flights dto for this flight id
	 * 
	 * @return List of available flights dto for this flight id
	 */
	public List<FlightAvailDto> getAvailableFlights() {
		return availableFlights;
	}

	/**
	 * This is a method used to set the list of available flights dto for this
	 * flight id
	 * 
	 * @param availableFlights The list of available flights dto is passed here.
	 */
	public void setAvailableFlights(List<FlightAvailDto> availableFlights) {
		this.availableFlights = availableFlights;
	}

	/**
	 * This is a method used to get the departure time from a flight dto object
	 * 
	 * @return The departure time of the flight.
	 */
	public Date getDepartureTime() {
		return departureTime;
	}

	/**
	 * This is a method used to set the departure time for a flight dto object
	 * 
	 * @param departureTime The departure time of the flight is passed here.
	 */
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * This is a method used to get the arrival time from a flight dto object
	 * 
	 * @return The arrival time of the flight.
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * This is a method used to set the arrival time for a flight dto object
	 * 
	 * @param arrivalTime The arrival time of the flight is passed here.
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}
