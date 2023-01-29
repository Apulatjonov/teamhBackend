package com.teamh.FuelBack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistExeption(UserAlreadyExistException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionResponse(exception.getField(), exception.getMessage(), exception.getStatus()));
    }
}
