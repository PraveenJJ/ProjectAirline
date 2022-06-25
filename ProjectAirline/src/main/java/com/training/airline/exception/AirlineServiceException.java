package com.training.airline.exception;

/**
 * This is the exception class for the service layer. Any exception that is
 * thrown in the service layer to the controller layer will be of this class
 * type.
 * 
 * @author 251656
 */
public class AirlineServiceException extends RuntimeException {

	/**
	 * default serial version uid is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor is used to instantiate the AirlineServiceException by
	 * passing the following arguments.
	 * 
	 * @param message
	 * @param cause
	 */
	public AirlineServiceException(String message, Throwable cause) {

		// calling the super class constructor by passing the message and the cause for
		// the exception
		super(message, cause);

	}

	/**
	 * This constructor is used to instantiate the AirlineServiceException by
	 * passing the following arguments.
	 * 
	 * @param message
	 */
	public AirlineServiceException(String message) {

		// calling the super class constructor by passing the message.
		super(message);

	}

}
