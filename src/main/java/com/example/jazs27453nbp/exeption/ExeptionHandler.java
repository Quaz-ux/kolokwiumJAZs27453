package com.example.jazs27453nbp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExeptionHandler {

    @ExceptionHandler(NoRatesException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoRatesException(NoRatesException ex) {
        return ex.getMessage();
    }
}
