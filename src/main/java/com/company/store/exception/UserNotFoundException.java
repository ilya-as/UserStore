package com.company.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User with id=" + id + " not found");
    }

    public UserNotFoundException(String name) {
        super("User with name=" + name + " not found");
    }
}
