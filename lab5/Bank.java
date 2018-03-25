package lab5;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bank {
	private String bankName;
	protected static Scanner sc = new Scanner(System.in);
	protected static BankAccount[] accounts;
	protected static int numAccounts = 0;
	protected static int bankSize = 100;

	public Bank(String name, int size) {
		this.bankName = name;
		bankSize = size;
		accounts = new BankAccount[bankSize];
	}

	public void addBankAccount() {
		if (numAccounts >= bankSize) {
			System.err.print("This bank has reached its maximum number of accounts");
			return;
		}

			System.out.println("Please enter details for account " + numAccounts + ".");
			System.out.println("What kind of account is this? Chequing 'c' or Savings 's'?");
			String type = sc.next();
			if (type.equalsIgnoreCase("c") || type.equalsIgnoreCase("Chequing")) {
				accounts[numAccounts] = new ChequingAccount();
			} else if (type.equalsIgnoreCase("s")|| type.equalsIgnoreCase("savings")) {
				accounts[numAccounts] = new SavingsAccount();
			} else {
				System.out.println("Invalid input. Please start over.");
				addBankAccount();
			}

			if (accounts[numAccounts].addBankAccount()) {
				numAccounts++;
				System.out.println(numAccounts);
			} else {
				System.out.println("Oops something went wrong. Account creation did not work.");
			}

	}

	public int findAccount() {
		System.out.println("What is the account number?");
		int accToFind = sc.nextInt();
		return searchAccounts(accToFind);
	}

	public static int searchAccounts(int accToFind) {
		for (int i =0; i < accounts.length; i++) {
			if (accounts[i] == null) {
				break;
			} else if (accounts[i].getAccountNumber() == accToFind) {
				return i;
			}
		}
		return -1;
	}

	public void displayAccount() {
			System.out.println(accounts[findAccount()]);
	}

	public void updateAccount() {
		try {
			int indexNum = findAccount();
			System.out.println("Please enter an amount.");
			double adjust = sc.nextDouble();
			if (adjust < 0) {
				accounts[indexNum].withdraw(adjust);
			} else if (adjust > 0) {
				accounts[indexNum].deposit(adjust);
			}
		} catch (InputMismatchException e) {
			System.out.println("That is not an appropriate input.");
		}
	}

	public void monthlyUpdate() {
			for (BankAccount i : accounts) {
				if (i == null) {
					break;
				} else {
					i.calculateAndUpdateBalance();
				}
			}
	}
}
