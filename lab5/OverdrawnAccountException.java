package lab5;

public class OverdrawnAccountException extends Exception {

	private static final long serialVersionUID = 1L;

	public OverdrawnAccountException() {
		super("This account is overdrawn.");
	}

	public OverdrawnAccountException(String message) {
		super(message);
	}
}
