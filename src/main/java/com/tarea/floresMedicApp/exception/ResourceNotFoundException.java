package com.tarea.floresMedicApp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	 public ResourceNotFoundException(String message) {
	        super(message);
	    }

	    public ResourceNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
