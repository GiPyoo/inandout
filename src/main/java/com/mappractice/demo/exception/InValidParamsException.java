package com.mappractice.demo.exception;

public class InValidParamsException extends RuntimeException{

    public InValidParamsException() {
    }

    public InValidParamsException(String message) {
        super(message);
    }
}
