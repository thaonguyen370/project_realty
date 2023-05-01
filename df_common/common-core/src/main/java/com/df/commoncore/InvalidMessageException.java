package com.df.commoncore;

public class InvalidMessageException extends Exception {

	private static final long serialVersionUID = -7754179049482603342L;

	public InvalidMessageException() {
		super("Invalid Message");
	}
	
	public InvalidMessageException(String message) {
		super(message);
	}
}
