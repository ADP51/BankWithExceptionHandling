package lab5;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * This class is a child of BankAccount and represents a chequing account.
 *
 * @author andrew palmer
 */
public class ChequingAccount extends BankAccount {

	protected double monthlyFee;
	private Scanner scan = new Scanner(System.in);
	DecimalFormat money = new DecimalFormat("$###,###.##");

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

	public String toString() {
		return super.toString() + "\n" + "Monthly Fee: " + money.format(this.monthlyFee);
	}

	@Override
	public void calculateAndUpdateBalance() {
		try {
			this.balance -= monthlyFee;
			if (this.balance < 0) {
				throw new OverdrawnAccountException();
			}
		} catch (OverdrawnAccountException e) {
			System.err.print("This account is overdrawn.");
		}
	}
}

