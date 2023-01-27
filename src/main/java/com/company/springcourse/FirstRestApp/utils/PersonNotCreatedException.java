package com.company.springcourse.FirstRestApp.utils;

public class PersonNotCreatedException extends RuntimeException{
	private String message;

	public PersonNotCreatedException(String message) {
		super(message);
	}
	
}
