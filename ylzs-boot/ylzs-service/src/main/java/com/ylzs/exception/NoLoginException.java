package com.ylzs.exception;

public class NoLoginException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoLoginException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public NoLoginException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public NoLoginException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NoLoginException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
