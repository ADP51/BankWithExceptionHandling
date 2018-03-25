package lab5;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;

import static lab5.Bank.searchAccounts;

public abstract class BankAccount {

	protected Customer accHolder;
	protected int accNumber;
	protected double balance;
	protected String accType;

	DecimalFormat money = new DecimalFormat("###,###.##");

	public boolean addBankAccount() {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Enter the account number");
			int num = scanner.nextInt();
			if (searchAccounts(num) == -1) {
				this.accNumber = num;
			} else {
				System.out.println("That account number already exists.");
				return false;
			}

			System.out.println("What is the opening balance?");
			this.balance = scanner.nextDouble();

			System.out.println("What is the customers first name?");
			String fname = scanner.next();

			System.out.println("What is the customers last name?");
			String lname = scanner.next();

			System.out.println("What is the customers email address?");
			String email = scanner.next();

			System.out.println("What is the customers phone number?");
			long phone = scanner.nextLong();

			this.accHolder = new Customer(fname, lname, email, phone);

			System.out.println("What type of account is this?");
			this.accType = scanner.next();
			return true;
		} catch (InputMismatchException exception) {
			System.out.println("That is not an acceptable input. Please start over.");
			return false;
		} catch (Exception unknownException) {
			System.out.println("Unknown Exception.");
			return false;
		}
	}

	public String toString() {
		return accType + ": " + accNumber + "\n" + "Balance: " + money.format(balance) + "$" + "\n" + "Name: "
				+ accHolder.getName() + "\n" + accHolder.toString();
	}

	public void deposit(double dep) {
		this.balance += dep;
	}

	public void withdraw(double amount) {
		this.balance -= amount;
	}

	public long getAccountNumber() {
		return this.accNumber;
	}

	public abstract void calculateAndUpdateBalance();
}
