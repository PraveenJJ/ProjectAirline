package com.training.airline.exception;

/**
 * This is the exception class for the validation. Any validation that fails,
 * will be thrown with an exception of this class.
 * 
 * @author 251656
 */
public class ValidationFailureException extends RuntimeException {

	/**
	 * default serial version uid is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor is used to instantiate the ValidationFailureException by
	 * passing the following arguments.
	 * 
	 * @param message
	 * @param cause
	 */
	public ValidationFailureException(String message, Throwable cause) {

		// calling the super class constructor by passing the message and the cause for
		// the exception
		super(message, cause);

	}

	/**
	 * This constructor is used to instantiate the ValidationFailureException by
	 * passing the following arguments.
	 * 
	 * @param message
	 */
	public ValidationFailureException(String message) {

		// calling the super class constructor by passing the message.
		super(message);

	}

}
