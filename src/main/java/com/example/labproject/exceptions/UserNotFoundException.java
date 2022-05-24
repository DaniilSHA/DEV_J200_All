package com.example.labproject.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(int id) {
        super("User with id " + id + " don't found");
    }
}
