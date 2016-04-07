package com.apartment.soap.provider.fault;

public class PizzaNotValidException extends Exception {
	private PizzaErrorMessage errorMessage;

	public PizzaNotValidException(String message,PizzaErrorMessage errorMessage) {
		super(message);
		this.errorMessage = errorMessage;
	}
	
	public PizzaErrorMessage getFault(){
		return errorMessage;
	}

	@Override
	public String toString() {
		return "PizzaNotValidException [errorMessage=" + errorMessage + "]";
	}
	
	
}