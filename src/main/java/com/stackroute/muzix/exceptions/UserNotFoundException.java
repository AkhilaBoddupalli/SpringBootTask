package com.stackroute.muzix.exceptions;

public class UserNotFoundException extends Exception {
    private String message;
    public UserNotFoundException(String message)
    {
        super(message);
        this.message=message;
    }
}
