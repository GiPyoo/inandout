package com.mappractice.demo.exception;

public class CannotDownloadFileException extends RuntimeException {

    public CannotDownloadFileException() {
    }

    public CannotDownloadFileException(String message) {
        super(message);
    }
}
