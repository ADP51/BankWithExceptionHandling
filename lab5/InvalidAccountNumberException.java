/*
 * Student Name: Andrew Palmer
 * Course Number: CST8132
 * Section: 311
 * File Name: InvalidAccountNumberException.java
 */

package lab5;

/**
 * This class is a custom exception for our bank to throw if someone inputs an invalid account number while creating an
 * account.
 *
 * @author Andrew Palmer
 * @version 1
 */
public class InvalidAccountNumberException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor that prints a message when it is thrown.
	 */
	public InvalidAccountNumberException() {
		super("Invalid account number.");
	}

	public InvalidAccountNumberException(String message) {
		super(message);
	}
}
