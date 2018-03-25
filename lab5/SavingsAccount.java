package lab5;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * This is a child class of BankAccount that represents a Savings Account
 */
public class SavingsAccount extends BankAccount {
	protected double monthlyInterestRate;
	protected double minBalance;

	public boolean addBankAccount() {
		boolean check = super.addBankAccount();
		Scanner scan = new Scanner(System.in);
		if (check) {
			System.out.println("What is the interest rate for this account?");
				try {
					this.monthlyInterestRate = scan.nextDouble();
				} catch (InputMismatchException e) {
					System.out.println("That is not an acceptable input.");
				} catch (Exception unknownE) {
					System.out.println("Unknown Exception");
					return false;
				}
			//loops through until an appropriate input for minBalance is input.
			System.out.println("What is the minimum balance for this account?");
				try {
					minBalance = scan.nextDouble();
					if (minBalance < 5 || minBalance > 100) {
						throw new Exception();
					}
				} catch (InputMismatchException e) {
					scan.nextLine();
					System.out.println("Please enter a number.");
				} catch (Exception e) {
					scan.nextLine();
					System.out.println("Please enter an amount between 5$ and 100$");
				}
			return true;
		}
		return false;
	}


	@Override
	public void calculateAndUpdateBalance() {
		if (this.balance > minBalance) {
			this.balance += (this.balance * (monthlyInterestRate / 100));
		}
	}

	public String toString() {
		super.toString();

		DecimalFormat money = new DecimalFormat("$###,###.##");
		DecimalFormat percentile = new DecimalFormat("%###.##");
		return "Interest Rate: " + percentile.format(this.monthlyInterestRate) + "\n" + "Minimum Balance: " + money.format(this.minBalance);
	}


}



