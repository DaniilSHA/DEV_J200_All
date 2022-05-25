package com.example.labproject.exceptions;

public class AddressNotBoundException extends RuntimeException{
    public AddressNotBoundException(int id) {
        super("Address with id " + id + " don't bound to user");
    }
}
