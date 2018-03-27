/*
 * Student Name: Andrew Palmer
 * Course Number: CST8132
 * Section: 311
 * File Name: MainMenu.java
 */

package lab5;

import java.util.Scanner;

/**
 * This is the Main class which is used to give the user options and then call methods based upon the
 * user input. This class also contains the main method.
 *
 * @author Angela Giddings
 * @author Andrew Palmer
 * @version 1.1
 * @see Bank
 */
public class MainMenu {

	/**
	 * The main method which prints out the options for the user to perform tasks such as create account.
	 * Then calls the appropriate methods to complete the tasks.
	 *
	 * @param args These are command line arguments.
	 */
	public static void main(String[] args) {

		Bank bank = new Bank("My bank", 10);
		Scanner input = new Scanner(System.in);
		char option = 'x';

		do {

			displayMenu();

			option = input.next().toLowerCase().charAt(0);

			switch (option) {

				case 'a':
					bank.addBankAccount();
					break;

				case 'd':
					bank.displayAccount();
					break;

				case 'l':
					bank.listAccounts();
					break;

				case 'u':
					bank.updateAccount();
					break;

				case 'm':
					bank.monthlyUpdate();
					break;

				default:

					System.out.println("Please enter a valid option.");
			}

		} while (option != 'q');

		System.out.println("Have a good day!");

		input.close();
	}

	/**
	 * The displayMenu method lists all the options for the user to give commands.
	 */
	public static void displayMenu() {

		System.out.println("");
		System.out.println("Enter your choice:");
		System.out.println("a: Add new account");
		System.out.println("d: Display account details");
		System.out.println("l: List all account details");
		System.out.println("u: Update account balance");
		System.out.println("m: Month-end update");
		System.out.println("q: Quit");
		System.out.println("");

	}
}
