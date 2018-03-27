/*
 * Student Name: Andrew Palmer
 * Course Number: CST8132
 * Section: 311
 * File Name: ChequingAccount.java
 */

package lab5;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * This class is a child of BankAccount and represents a chequing account. Which imposes its
 * own rules for an account on top of the BankAccount rules.
 *
 * @author andrew palmer
 * @version 1.5
 * @see BankAccount
 */
public class ChequingAccount extends BankAccount {

	/**
	 * This is a double to keep track of a monthly fee for this type of account.
	 */
	protected double monthlyFee;
	private Scanner scan = new Scanner(System.in);
	DecimalFormat money = new DecimalFormat("$###,###.##");

	/**
	 * The addBankAccount for the ChequingAccount class is used to call the super BankAccount method
	 * and if that returns true then continues to get the values for for the monthly fee.
	 *
	 * @return returns true or false depending on whether the account was created successfully.
	 */
	public boolean addBankAccount() {
		boolean check = super.addBankAccount();

		if (check) {
			try {
				System.out.println("What is the monthly fee for this account?");
				monthlyFee = scan.nextDouble();
				return true;
			} catch (InputMismatchException e) {
				System.err.print("That is not an acceptable input.");
				scan.nextLine();
			} catch (Exception unknownE) {
				System.err.print("Unknown Exception");
				scan.nextLine();
			}
		}
		return false;
	}

	/**
	 * The method toString calls upon the super classes toString method then appends the monthly fee onto the end.
	 *
	 * @return Returns a string provided by the super method then adds the monthly fee onto the end of the String.
	 */
	public String toString() {
		return super.toString() + "\n" + "Monthly Fee: " + money.format(this.monthlyFee);
	}

	/**
	 * The calculateAndUpdateBalance method takes the the monthly fee and subtracts it from the balance of
	 * the account it is called on.
	 */
	@Override
	public void calculateAndUpdateBalance() {
		try {
			this.balance -= monthlyFee;
			if (this.balance < 0) {
				throw new OverdrawnAccountException();
			}
		} catch (OverdrawnAccountException e) {
			System.err.print("This account: " + this.accNumber + " is overdrawn.");
		}
	}
}

