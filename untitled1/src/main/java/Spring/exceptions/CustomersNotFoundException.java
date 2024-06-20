package Spring.exceptions;

public class CustomersNotFoundException extends Exception {

	/**
	 * @author ismael
	 * @author saber
	 * @author bahaa
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CustomersNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CustomersNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomersNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CustomersNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomersNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
