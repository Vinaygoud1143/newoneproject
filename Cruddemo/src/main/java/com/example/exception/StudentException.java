package com.example.exception;

import org.springframework.http.HttpStatus;

public class StudentException  extends Exception{

	public String message;

	
	public StudentException(String message){
		super();
		this.message=message;

		
			}
	
	
	
	
}
