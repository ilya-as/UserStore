package com.company.store.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String name) {
        super(String.format("User not unique with name: %s", name));
    }
}
