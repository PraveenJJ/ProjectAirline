package com.training.airline.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This is a utility class for generating the password. This class provides the
 * methods for the generation of encrypted password.
 * 
 * @author 251656
 */
public class Password {

	/**
	 * This method is used to convert a string password into an encrypted password
	 * and return it back.
	 * 
	 * @param passwordToHash
	 * @return encrypted password
	 * @throws NoSuchAlgorithmException
	 */
	public static String get_SHA_1_SecurePassword(String passwordToHash) throws NoSuchAlgorithmException {

		// initializing the variable
		String generatedPassword = null;

		// adding salt at the start and end of the password that is to be encrypted.
		passwordToHash = getFrontSalt() + passwordToHash + getEndSalt();

		// getting the instance of the message digest object
		MessageDigest md = MessageDigest.getInstance("SHA-1");

		// converting the password to be encrypted into array of bytes and updating it
		// to the digest to get array of bytes for the resulting hash value.
		byte[] bytes = md.digest(passwordToHash.getBytes());

		// instantiating a string builder object
		StringBuilder sb = new StringBuilder();

		// iterating over the length of byte array
		for (int i = 0; i < bytes.length; i++) {

			// converting byte of decimal format into hexadecimal format
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

		}

		// getting complete password in hexadecimal format
		generatedPassword = sb.toString();

		// returning the generated password
		return generatedPassword;

	}

	/**
	 * This method is used to get the salt that is to be added at the front of the
	 * password that is to be encrypted.
	 * 
	 * @return front salt
	 */
	private static String getFrontSalt() {

		// unique salt to be added at the front of the password
		return "heyItsFun!@#";

	}

	/**
	 * This method is used to get the salt that is to be added at the end of the
	 * password that is to be encrypted.
	 * 
	 * @return end salt
	 */
	private static String getEndSalt() {

		// unique salt to be added at the end of the password
		return "12987!@#";

	}

}
