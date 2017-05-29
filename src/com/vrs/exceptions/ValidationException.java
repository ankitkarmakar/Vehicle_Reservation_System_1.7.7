package com.vrs.exceptions;

public class ValidationException extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String message=null;
public String getMessage() {
	return message;
}
public ValidationException(String message) {
	super();
	this.message = message;
}
}
