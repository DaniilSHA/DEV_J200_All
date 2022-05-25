package com.example.labproject.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(int id) {
        super("User with id " + id + " don't found");
    }
}
