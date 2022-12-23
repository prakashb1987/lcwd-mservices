package com.lcwd.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("Resource Not Found Exception");
    }

    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
