package com.crimsonlogic.flightticketbookingsystem.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8432318767767692506L;

	public UserAlreadyRegisteredException(String message) {
		super(message);
	}
}
