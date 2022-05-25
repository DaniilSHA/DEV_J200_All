package com.example.labproject.exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(int id) {
        super("Address with id " + id + " don't found");
    }
}
