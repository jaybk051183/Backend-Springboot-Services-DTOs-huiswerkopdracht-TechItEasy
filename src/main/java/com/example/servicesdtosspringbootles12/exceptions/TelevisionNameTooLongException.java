package com.example.servicesdtosspringbootles12.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TelevisionNameTooLongException extends RuntimeException {
    //The class includes a constructor that takes a single parameter, a String message.
    public TelevisionNameTooLongException(String message) {
        super(message);
    }}
