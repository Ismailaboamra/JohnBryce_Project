package Spring.exceptions;

public class CompanyNotFoundException extends Exception {

	/**
	 * @author ismael
	 * @author saber
	 * @author bahaa
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CompanyNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CompanyNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CompanyNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CompanyNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
