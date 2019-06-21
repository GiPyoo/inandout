package com.mappractice.demo.exception;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestReponseEntityExceptionHandler {
    private Logger log = LoggerFactory.getLogger(RestReponseEntityExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ImageNotFoundException.class)
    public void imageNotFound() {
        log.debug("Image is not found");
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {AmazonS3Exception.class, SdkClientException.class})
    public void storageRequestError() {
        log.debug("remote storage request error");
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {IllegalStateException.class, CannotConvertFileException.class})
    public void fileConvertingError() {
        log.debug("fail converting MultipartFile to File");
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {CannotDownloadFileException.class})
    public void fileDownloadError() {
        log.debug("fail downloading MultipartFile to File");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InValidParamsException.class})
    public void paramsError() {
        log.debug("invalid parameters");
    }
}

