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

	public int findAccount() {
		System.out.println("What is the account number?");
		int accToFind = sc.nextInt();
		return searchAccounts(accToFind);
	}

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

	public void updateAccount() {
		try {
			int indexNum = findAccount();
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
		}
	}

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

}
