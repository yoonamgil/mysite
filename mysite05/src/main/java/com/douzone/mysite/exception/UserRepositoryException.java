package com.douzone.mysite.exception;

public class UserRepositoryException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public UserRepositoryException() {
		super("UserRepositoryExcption Occurs");
	}
	public UserRepositoryException(String Message) {
		super(Message);
	}
}
