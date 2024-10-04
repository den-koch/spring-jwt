package io.github.denkoch.springjwt.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorResponse handleException(DuplicateKeyException exception){
        return ErrorResponse.create(exception, HttpStatus.CONFLICT, exception.getMessage());
    }

}
