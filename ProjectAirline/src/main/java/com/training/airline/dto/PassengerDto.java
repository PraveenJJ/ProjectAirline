package com.training.airline.dto;

import java.io.Serializable;

/**
 * This is a Dto class to hold passenger information. It has private fields to
 * represent the properties of each Passenger entity. It has getters and setters
 * to access and modify the private fields.
 * 
 * @author Praveen J
 */
public class PassengerDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field referenceId of type BookingDto represents the Booking dto object.
	 */
	private Integer referenceId; 

	/**
	 * This field ticketNumber of type Integer represents the ticket number
	 * allocated to each passenger.
	 */
	private Integer ticketNumber;

	/**
	 * This field passengerName of type String represents the name given for a
	 * passenger.
	 */
	private String passengerName;

	/**
	 * This field age of type Integer represents the age for a passenger.
	 */
	private Integer age;

	/**
	 * This field status of type String represents the booking status given for a
	 * passenger.
	 */
	private String status;

	/**
	 * This is a zero argument constructor. Used to instantiate passenger dto object.
	 */
	public PassengerDto() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the passenger dto object and then initialize the values with the
	 * arguments passed.
	 * 
	 * @param ticketNumber  ---> ticket number of the passenger
	 * @param passengerName ---> name of the passenger
	 * @param age           ---> age of the passenger
	 * @param status        ---> status of ticket of the passenger
	 */
	public PassengerDto(Integer ticketNumber, String passengerName, Integer age, String status) {
		this.ticketNumber = ticketNumber;
		this.passengerName = passengerName;
		this.age = age;
		this.status = status;
	}

	/**
	 * This is a method used to get the booking dto object from a passenger dto
	 * object
	 * 
	 * @return the booking dto object given for each passenger.
	 */
	public Integer getReferenceId() {
		return referenceId;
	}

	/**
	 * This is a method used to set the booking dto object for a passenger dto
	 * object
	 * 
	 * @param referenceId The booking dto object is passed here.
	 */
	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * This is a method used to get the ticket number from a passenger dto object
	 * 
	 * @return the ticket number given for each passenger.
	 */
	public Integer getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * This is a method used to set the ticket number for a passenger dto object
	 * 
	 * @param ticketNumber The ticket number is passed here.
	 */
	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	/**
	 * This is a method used to get the passenger name from a passenger dto object
	 * 
	 * @return the passenger name given for each passenger.
	 */
	public String getPassengerName() {
		return passengerName;
	}

	/**
	 * This is a method used to set the passenger name for a passenger dto object
	 * 
	 * @param passengerName The passenger name is passed here.
	 */
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	/**
	 * This is a method used to get the age from a passenger dto object
	 * 
	 * @return the age for each passenger.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * This is a method used to set the age for a passenger dto object
	 * 
	 * @param age The age of the passenger is passed here.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * This is a method used to get the status of the ticket from a passenger dto
	 * object
	 * 
	 * @return the status of the ticket for each passenger.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This is a method used to set the status of the ticket for a passenger dto
	 * object
	 * 
	 * @param status The status of the ticket of the passenger is passed here.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
