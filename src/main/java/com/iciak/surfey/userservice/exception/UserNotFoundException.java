package com.iciak.surfey.userservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("No such a User in the database");
    }
}
