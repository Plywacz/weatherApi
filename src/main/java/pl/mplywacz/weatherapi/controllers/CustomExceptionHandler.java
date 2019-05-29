package pl.mplywacz.weatherapi.controllers;
/*
Author: BeGieU
Date: 29.05.2019
*/

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception) {
        return new ResponseEntity<>("Illegal Argument, give proper city name and frequency must be bigger than zero", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
