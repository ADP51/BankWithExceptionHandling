/*
 * Student Name: Andrew Palmer
 * Course Number: CST8132
 * Section: 311
 * File Name: Customer.java
 */

package lab5;

/**
 * The Customer class is used to assign all customer information such as name, phone, and email.
 *
 * @author Angela Giddings, Andrew Palmer
 * @version 1.1
 * @see BankAccount
 */
public class Customer implements Comparable<Customer> {

	private String firstName;
	private String lastName;
	private String email;
	private long phoneNum;

	/**
	 * The constructor for Customer takes 4 parameters to initialize all the variables.
	 *
	 * @param firstName used to initialize the customers first name.
	 * @param lastName  used to initialize the customers last name.
	 * @param email     Used to initialize the customers email.
	 * @param phone     Used to initialize the customers phone number.
	 */
	public Customer(String firstName, String lastName, String email, long phone) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phone;

	}

	/**
	 * The getName method is used to get the customers name and return it as a String.
	 *
	 * @return returns firstName and lastName as a string.
	 */
	public String getName() {
		return firstName + " " + lastName;
	}

	/**
	 * The toString method is used to get and return all the customers information as a string.
	 *
	 * @return a string of the customers information.
	 */
	public String toString() {

		return "Name:  " + firstName + " " + lastName + "\n"
				+ "Email: " + email + "\n"
				+ "Phone: " + phoneNum;
	}

	/**
	 * compareTo is used to compare a customers information against another to find duplicates.
	 *
	 * @param customer Takes a Customer object as an argument.
	 * @return The comparison of the argument to the object it is called on.
	 */
	public int compareTo(Customer customer) {

		return getName().compareTo(customer.getName());

	}
}
