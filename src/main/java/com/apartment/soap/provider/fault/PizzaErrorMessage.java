package com.apartment.soap.provider.fault;

public class PizzaErrorMessage {
	private String message;
	private String code;
	private String description;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PizzaErrorMessage [message=" + message + ", code=" + code + ", description=" + description + "]";
	}

}
