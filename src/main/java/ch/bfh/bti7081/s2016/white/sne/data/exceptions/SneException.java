package ch.bfh.bti7081.s2016.white.sne.data.exceptions;

/**
 * All exceptions that are expected to be handled and displayed to the user
 * should be mapped into a SneException.
 * 
 * Maybe later we should split those into exceptions corresponding to the tier
 * the root-exception was thrown
 * 
 * @author thons1
 */
public class SneException extends Exception {

	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = -7237453308041417769L;

	/**
	 * the message that shall be given to the user. This should not be too
	 * technical.
	 */
	private String message;

	/**
	 * Constructs a SneException. It is required to specify an error message
	 * (fortunately not a too technical one) and an other exception.
	 * 
	 * @param message - String
	 * @param e - Exception
	 */
	public SneException(String message, Exception e) {
		super(e);
		this.message = message;
	}

	/**
	 * Returns the error message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Assigns a message to an existing exception
	 * @param message - String
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
