package jtm.activity07;

public class SimpleCalcException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private String message;
	private Throwable cause;

	public SimpleCalcException(String message) {
		super(message);
		this.message = message;
	}

	public SimpleCalcException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.cause = cause;
	}
}
