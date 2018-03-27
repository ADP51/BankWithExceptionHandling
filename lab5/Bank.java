/*
 * Student Name: Andrew Palmer
 * Course Number: CST8132
 * Section: 311
 * File Name: Bank.java
 */

package lab5;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is to set up the bank object class and handle all the methods directly related to the bank.
 * Contains the array of accounts and handles putting accounts into the bank array.
 *
 * @author Andrew Palmer
 * @version 2.8
 * @see BankAccount, ChequingAccount, SavingsAccount
 */
public class Bank {
	private String bankName;

	/**
	 * This is the scanner method to take all input for this class and it's methods.
	 */
	protected static Scanner sc = new Scanner(System.in);

	/**
	 * This is an array of Bank accounts that belong to this bank.
	 */
	protected static BankAccount[] accounts;

	/**
	 * numAccounts works as a counter to keep track of the position in the accounts[]. Only incrementing once an account has been
	 * created.
	 */
	protected static int numAccounts = 0;

	/**
	 * bankSize works as a limit on how many accounts a bank can have. The default size is set to 100.
	 */
	protected static int bankSize = 100;

	/**
	 * This is the constructor for the the bank class. It initializes the name of the bank, and sets how many accounts it can have.
	 *
	 * @param name This parameter is sed to set the name of the bank object.
	 * @param size This parameter overrides the maximum number of accounts and then initializes the size of the accounts[] array.
	 */
	public Bank(String name, int size) {
		this.bankName = name;
		bankSize = size;
		accounts = new BankAccount[bankSize];
	}

	/**
	 * The addBankAccount method is used to set up new accounts. When it is called it first checks to see if
	 * see if the bank object has reached its maximum number of accounts. If it has, the method prints
	 * a message informing the user it has reached its max. If it has not then it prompts the user for what
	 * kind of account they want to make. Then finally, calls the addBankAccount for the BankAccount class
	 * if it returns true then the account is put in the array and the numAccounts variable is incremented.
	 */
	public void addBankAccount() {
		if (numAccounts >= bankSize) {
			System.err.println("This bank has reached its maximum number of accounts");
			return;
		}

		try {
			System.out.println("Please enter details for account " + numAccounts + ".");
			System.out.println("What kind of account is this? Chequing 'c' or Savings 's'?");
			String type = sc.next();
			if (type.equalsIgnoreCase("c") || type.equalsIgnoreCase("Chequing")) {
				accounts[numAccounts] = new ChequingAccount();
			} else if (type.equalsIgnoreCase("s") || type.equalsIgnoreCase("savings")) {
				accounts[numAccounts] = new SavingsAccount();
			} else {
				System.err.println("Invalid input. Please start over.");
				return;
			}

			if (accounts[numAccounts].addBankAccount()) {
				numAccounts++;
			} else {
				System.err.println("There was a problem creating the account.");
			}
		} catch (Exception e) {
			System.err.println("Unknown exception.");
		}
	}

	/**
	 * The findAccount method is used to find an account. It prompts the user for input then calls the
	 * searchAccounts method with it.
	 *
	 * @return returns the outcome of the searchAccounts method.
	 */
	public int findAccount() {
		System.out.println("What is the account number?");
		int accToFind = sc.nextInt();
		return searchAccounts(accToFind);
	}

	/**
	 * The searchAccounts method takes a double as a parameter and loops through the accounts array
	 * calling the getAccountNumber from the BankAccount class on every account and comparing it to the
	 * input parameter.
	 *
	 * @param accToFind Is used to compare against account numbers to find a match.
	 * @return The index number of the account that matches the parameter number if a match is found.
	 * If no match is found -1 is returned.
	 */
	public static int searchAccounts(int accToFind) {
		try {
			for (int i = 0; i < accounts.length; i++) {
				if (accounts[i] == null) {
					break;
				} else if (accounts[i].getAccountNumber() == accToFind) {
					return i;
				}
			}
		} catch (Exception e) {
			System.err.println("Unknown Exception in search accounts method.");
			return -1;
		}
		return -1;
	}

	/**
	 * The displayAccount method is used to implicitly call the toString method on the account that
	 * is found with the findAccounts method.
	 */
	public void displayAccount() {
		try {
			System.out.println(accounts[findAccount()]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("That account number doesn't exist.");
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.err.println("That is not an acceptable input please start over.");
			sc.nextLine();
		}
	}

	/**
	 * The updateAccount method calls the findAccount number and stores the index number returned to a local varialble.
	 * Then it prompts the user for an amount(double), if the user inputs a negative number then the
	 * withdraw method is called with that number. If the number is positive the deposit method is called
	 * with the amount.
	 */
	public void updateAccount() {
		int indexNum = findAccount();
		try {
			System.out.println("Please enter an amount.");
			double adjust = sc.nextDouble();
			if (adjust < 0) {
				adjust = adjust * -1;
				accounts[indexNum].withdraw(adjust);
			} else if (adjust > 0) {
				accounts[indexNum].deposit(adjust);
			}
		} catch (InputMismatchException e) {
			System.err.println("That is not an appropriate input.");
			sc.nextLine();
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.err.println("That account number does not exist.");
			sc.nextLine();
		} catch (InsufficientFundsException e) {
			System.err.println("Account Number: " + accounts[indexNum].accNumber + " has insufficient funds.");
		}
	}

	/**
	 * The monthlyUpdate method is used to loop through the accounts[] array using an enhanced for
	 * loop and calls the calculateAndUpdateBalance method on each account.
	 */
	public void monthlyUpdate() {
		try {
			for (BankAccount i : accounts) {
				if (i == null) {
					break;
				} else {
					i.calculateAndUpdateBalance();
				}
			}
		} catch (Exception e) {
			System.err.print("Unknown Exception occurred in monthly update.");
		}
	}

	/**
	 * the listAccounts method is used to loop through accounts array and prints out the account number and
	 * the account holders name.
	 */
	public void listAccounts() {
		try {
			for (int i = 0; i < accounts.length; i++) {
				if (accounts[i] == null) {
					break;
				} else {
					System.out.println("Account Number: " + accounts[i].accNumber + " Name: " + accounts[i].accHolder.getName());
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Index out of bounds exception.");
			return;
		} catch (NullPointerException e) {
			System.err.println("Null pointer exception.");
			return;
		}
		System.out.println("");
	}

}
