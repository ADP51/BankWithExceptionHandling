package lab5;

public class Customer implements Comparable<Customer> {

	private String firstName;
	private String lastName;
	private String email;
	private long phoneNum;

	public Customer(String firstName, String lastName, String email, long phone) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phone;

	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String toString() {

		return "Name:  " + firstName + " " + lastName + "\n"
				+ "Email: " + email + "\n"
				+ "Phone: " + phoneNum;
	}

	public int compareTo(Customer customer) {

		return getName().compareTo(customer.getName());

	}
}
