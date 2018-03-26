package lab5;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * This is a child class of BankAccount that represents a Savings Account
 */
public class SavingsAccount extends BankAccount {
	protected double monthlyInterestRate;
	protected double minBalance;

	DecimalFormat money = new DecimalFormat("$###,###.##");
	NumberFormat percent = NumberFormat.getPercentInstance();

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

	public String toString() {
		return super.toString() + "\n" + "Interest Rate: " + percent.format(this.monthlyInterestRate) + "\n" + "Minimum Balance: " + money.format(this.minBalance) + "\n";
	}

}



