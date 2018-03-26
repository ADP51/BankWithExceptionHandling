package lab5;

import java.util.Scanner;

public class MainMenu {

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

	public static void displayMenu() {

		System.out.println("Enter your choice:");
		System.out.println("a: Add new account");
		System.out.println("d: Display account details");
		System.out.println("l: List all account details");
		System.out.println("u: Update account balance");
		System.out.println("m: Month-end update");
		System.out.println("q: Quit");

	}
}
