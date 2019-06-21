package com.mappractice.demo.exception;

public class CannotConvertFileException extends RuntimeException {

    public CannotConvertFileException() {
    }

    public CannotConvertFileException(String message) {
        super(message);
    }
}
