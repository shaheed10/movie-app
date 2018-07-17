package com.stackroute.movieapp.exception;

public class MovieAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieAlreadyExistException(String message) {
		super(message);
	}
}