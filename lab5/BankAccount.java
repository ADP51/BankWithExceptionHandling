/*
 * Student Name: Andrew Palmer
 * Course Number: CST8132
 * Section: 311
 * File Name: BankAccount.java
 */

package lab5;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;

import static lab5.Bank.searchAccounts;

/**
 * The bank account class is the parent class for all bank account types.
 *
 * @author Andrew Palmer
 * @version 3
 * @see Bank, ChequingAccount, SavingsAccount, Customer
 */
public abstract class BankAccount {

	/**
	 * This is the customer that this account belongs to.
	 */
	protected Customer accHolder;

	/**
	 * This is the account number to identify the account, it must be unique to all accounts.
	 */
	protected int accNumber;

	/**
	 * This is the balance for the account.
	 */
	protected double balance;

	/**
	 * This is a string representation of that can be entered to be description a description of the account.
	 */
	protected String accType;

	DecimalFormat money = new DecimalFormat("$###,###.##");

	/**
	 * The addBankAccount for the BankAccount method is used to set up the account number, balance
	 * , and gets all the information for the Customer that owns the account and creates it. It also
	 * prompts for an account description of the account to display.
	 *
	 * @return If all inputs are successful then this method returns true, otherwise false is returned.
	 */
	public boolean addBankAccount() {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Enter the account number");
			int num = scanner.nextInt();
			if (searchAccounts(num) == -1) {
				if (num < 100 || num > 999) {
					throw new InvalidAccountNumberException();
				} else {
					this.accNumber = num;
				}
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
			System.err.println("That is not an acceptable input. Please start over.");
			return false;
		} catch (InvalidAccountNumberException e) {
			System.err.println("Account numbers need to be between 100 and 999.");
			return false;
		} catch (Exception e) {
			System.err.println("Unknown Error.");
			return false;
		}
	}

	/**
	 * The Bank Account toString returns a string of all the account details that were given in
	 * addBankAccount earlier.
	 *
	 * @return returns string of account details.
	 */
	public String toString() {
		return this.accType + ": " + this.accNumber + "\n" + "Balance: " + money.format(this.balance) + "\n" + "Name: "
				+ this.accHolder.getName();
	}

	/**
	 * The deposit method takes an argument and adds it to the current balance of the account.
	 *
	 * @param dep A double taken as a deposit amount.
	 */
	public void deposit(double dep) {
		this.balance += dep;
	}

	/**
	 * The withdraw method takes a double and checks if it is greater than the current balance, if it is
	 * it throws a InsufficientFundsException. If not it subtracts the double from balance.
	 *
	 * @param amount The amount to be taken out.
	 * @throws InsufficientFundsException
	 */
	public void withdraw(double amount) throws InsufficientFundsException {
		if (amount > this.balance) {
			throw new InsufficientFundsException();
		} else {
			this.balance -= amount;
		}
	}

	/**
	 * The getAccountNumber method is used to get the account number of the account it is called on.
	 *
	 * @return returns the accnumber variable of the object.
	 */
	public long getAccountNumber() {
		return this.accNumber;
	}

	/**
	 * This is the abstract method that all child classes must implement.
	 */
	public abstract void calculateAndUpdateBalance();
}
