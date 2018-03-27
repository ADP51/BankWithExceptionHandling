/*
 * Student Name: Andrew Palmer
 * Course Number: CST8132
 * Section: 311
 * File Name: OverdrawnAccountException.java
 */

package lab5;

/**
 * This is a custom exception which is thrown when the user tries to perform a the monthly update on an account, and
 * the accounts balance falls below 0.
 *
 * @author Andrew Palmer
 * @version 1
 */
public class OverdrawnAccountException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * The constructor is used to print a message when it is thrown.
	 */
	public OverdrawnAccountException() {
		super("This account is overdrawn.");
	}

	/**
	 * The Constructor takes a message as a parameter and calls the super method with it.
	 *
	 * @param message A message to call the super with.
	 */
	public OverdrawnAccountException(String message) {
		super(message);
	}
}
