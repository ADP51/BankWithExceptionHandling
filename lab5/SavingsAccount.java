/*
 * Student Name: Andrew Palmer
 * Course Number: CST8132
 * Section: 311
 * File Name: SavingsAccount.java
 */

package lab5;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * This is a child class of BankAccount that represents a Savings Account. Which imposes its
 * own rules for an account on top of the BankAccount rules.
 *
 * @author Andrew Palmer
 * @version 2.1
 * @see BankAccount
 */
public class SavingsAccount extends BankAccount {

	/**
	 * Takes a double as a monthly interest rate for the savings account and stores it.
	 */
	protected double monthlyInterestRate;

	/**
	 * Takes a double and stores it as a minimum monthly balance for the account.
	 */
	protected double minBalance;

	DecimalFormat money = new DecimalFormat("$###,###.##");
	NumberFormat percent = NumberFormat.getPercentInstance();

	/**
	 * The addBankAccount method calls the super version of this method and stores the returned boolean.
	 * If true then the method continues to prompt for and set up the interest rate and minimum balance for the
	 * account. Then returning true if it was successful or false if not.
	 *
	 * @return Boolean true if successful and false if not.
	 */
	public boolean addBankAccount() {
		boolean check = super.addBankAccount();
		Scanner scan = new Scanner(System.in);
		if (check) {
			System.out.println("What is the interest rate for this account? (0 - 1)");
			try {
				this.monthlyInterestRate = scan.nextDouble();
				if (monthlyInterestRate > 1 || monthlyInterestRate < 0) {
					System.err.print("Interest rate needs to be between 0 and 1.");
				}
			} catch (InputMismatchException e) {
				System.err.print("That is not an acceptable input.");
			}

			//loops through until an appropriate input for minBalance is input.
			System.out.println("What is the minimum balance for this account?(5 - 100$)");
			try {
				double balance = scan.nextDouble();
				if (balance < 5 || balance > 100) {
					System.out.println("That is not an appropriate minimum balance.");
					return false;
				} else {
					minBalance = balance;
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.err.print("Please enter a number.");
				return false;
			}
			return true;
		}
		return false;
	}


	/**
	 * The calculateAndUpdateBalance method checks to make sure the account is greater than the minimum
	 * amount allowed throwing an OverdrawnAccountException if it is. Then updates the balance by calculating the
	 * interest gained and adding it to the current balance.
	 */
	@Override
	public void calculateAndUpdateBalance() {
		if (this.balance > minBalance) {
			this.balance += (this.balance * (monthlyInterestRate / 100));
		} else {
			try {
				throw new OverdrawnAccountException();
			} catch (OverdrawnAccountException e) {
				e.printStackTrace();
				System.err.println("Account Number: " + this.accNumber + " is overdrawn.");
			}
		}
	}

	/**
	 * The SavingsAccount method returns the super methods string then appends the monthly interest rate
	 * and the minimum allowed balance for the account.
	 *
	 * @return The super.toString method with this classes variables appended on.
	 */
	public String toString() {
		return super.toString() + "\n" + "Interest Rate: " + percent.format(this.monthlyInterestRate) + "\n" + "Minimum Balance: " + money.format(this.minBalance) + "\n";
	}

}



