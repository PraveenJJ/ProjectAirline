package com.training.airline.dto;

import java.io.Serializable;
import java.util.List;

/**
 * This is a Dto class to hold passenger information to cancel. It has private
 * fields to represent the properties of each passenger. It has getters and
 * setters to access and modify the private fields.
 * 
 * @author 251656
 */
public class CancelPassengerDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field referenceId of type Integer represents the Booking id.
	 */
	private Integer referenceId;

	/**
	 * This field holds the list of passengers dto whose tickets needs to be cancelled
	 */
	private List<PassengerDto> passengerDtos;

	/**
	 * This is a zero argument constructor. Used to instantiate cancel passenger dto
	 * object.
	 */
	public CancelPassengerDto() {

	}
	
	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the cancel passenger dto object and then initialize the values with the
	 * arguments passed.
	 * 
	 * @param passengerDtos ---> list of passenger dtos
	 */
	public CancelPassengerDto(List<PassengerDto> passengerDtos) {
		this.passengerDtos = passengerDtos;
	}

	/**
	 * This is a method used to get the booking id from a cancel passenger dto object
	 * 
	 * @return the booking id object given for each cancel passenger dto object.
	 */
	public Integer getReferenceId() {
		return referenceId;
	}

	/**
	 * This is a method used to set the booking id for a cancel passenger dto object
	 * 
	 * @param referenceId The booking id object is passed here.
	 */
	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}
	
	/**
	 * This is a method used to get the list of passenger dtos from a cancel passenger dto object
	 * 
	 * @return the list of passenger dtos from each cancel passenger dto object.
	 */
	public List<PassengerDto> getPassengerDtos() {
		return passengerDtos;
	}

	/**
	 * This is a method used to set the list of passenger dto for a cancel passenger dto object
	 * 
	 * @param passengerDtos The list of passenger dto is passed here.
	 */
	public void setPassengerDtos(List<PassengerDto> passengerDtos) {
		this.passengerDtos = passengerDtos;
	}

}
