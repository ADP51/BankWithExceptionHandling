package lab5;

public class InvalidAccountNumberException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidAccountNumberException() {
		super("Invalid account number.");
	}

	public InvalidAccountNumberException(String message) {
		super(message);
	}
}
