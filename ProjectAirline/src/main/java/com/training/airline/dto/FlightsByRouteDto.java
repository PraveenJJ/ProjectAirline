package com.training.airline.dto;

import java.io.Serializable;

/**
 * This is a Dto class to hold flight available by route information. It has
 * private fields to represent the properties of each Flight information. It has
 * getters and setters to access and modify the private fields.
 * 
 * @author Praveen J
 */
public class FlightsByRouteDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field originCity of type String represents the origin city name.
	 */
	private String originCity;

	/**
	 * This field destinationCity of type String represents the destination city
	 * name.
	 */
	private String destinationCity;

	/**
	 * This is a zero argument constructor. Used to instantiate flight available by
	 * route dto object.
	 */
	public FlightsByRouteDto() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the flight available by route dto object and then initialize the
	 * values with the arguments passed.
	 * 
	 * @param originCity      ---> origin city name
	 * @param destinationCity ---> destination city name
	 */
	public FlightsByRouteDto(String originCity, String destinationCity) {
		this.originCity = originCity;
		this.destinationCity = destinationCity;
	}

	/**
	 * This is a method used to get the origin city name from a flight available by
	 * route dto object
	 * 
	 * @return the origin city name given for each flight available by route dto
	 *         object.
	 */
	public String getOriginCity() {
		return originCity;
	}

	/**
	 * This is a method used to set the origin city name for a flight available by
	 * route dto object
	 * 
	 * @param originCity The origin city name is passed here.
	 */
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	/**
	 * This is a method used to get the destination city name from a flight
	 * available by route dto object
	 * 
	 * @return the destination city name given for each flight available by route
	 *         dto object.
	 */
	public String getDestinationCity() {
		return destinationCity;
	}

	/**
	 * This is a method used to set the destination city name for a flight available
	 * by route dto object
	 * 
	 * @param destinationCity The destination city name is passed here.
	 */
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

}
