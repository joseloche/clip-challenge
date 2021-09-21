package com.example.clip.exception;

import javax.servlet.annotation.HttpConstraint;

@HttpConstraint()
public class NotDataFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -1197863621932977046L;

	
	public NotDataFoundException(String message)
	{
		super(message);
	}

}
