/*
 * Student Name: Andrew Palmer
 * Course Number: CST8132
 * Section: 311
 * File Name: InsufficientFundsException.java
 */

package lab5;

/**
 * This is a custom exception class that is thrown when the user tries to withdraw more money than the account has.
 *
 * @ Angela Giddings
 * @version 1
 */
public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * The constructor that prints a message when it is thrown.
	 */
	public InsufficientFundsException() {
		super("Insufficient funds available.");
	}

	/**
	 * Calls the super class and passes it a message.
	 *
	 * @param message Takes a message and passes it to the super class.
	 */
	public InsufficientFundsException(String message) {
		super(message);
	}
}
