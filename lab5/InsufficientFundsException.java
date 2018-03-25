package lab5;

public class InsufficientFundsException extends Exception{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException() {
		super("Insufficient funds available.");
	}

	public InsufficientFundsException(String message) {
		super(message);
	}
}
