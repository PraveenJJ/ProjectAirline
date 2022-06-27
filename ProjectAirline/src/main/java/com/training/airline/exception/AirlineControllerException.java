package com.training.airline.exception;

/**
 * This is the exception class for the controller layer. Any exception that is
 * thrown in the controller layer will be of this class type.
 * 
 * @author Praveen J
 */
public class AirlineControllerException extends RuntimeException {

	/**
	 * default serial version uid is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor is used to instantiate the AirlineControllerException by
	 * passing the following arguments.
	 * 
	 * @param message
	 * @param cause
	 */
	public AirlineControllerException(String message, Throwable cause) {

		// calling the super class constructor by passing the message and the cause for
		// the exception
		super(message, cause);

	}

	/**
	 * This constructor is used to instantiate the AirlineControllerException by
	 * passing the following arguments.
	 * 
	 * @param message
	 */
	public AirlineControllerException(String message) {

		// calling the super class constructor by passing the message.
		super(message);

	}

}
