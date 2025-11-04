package com.example.jwt.exception.advice;

import com.example.jwt.exception.advice.exception.PasswordException;
import com.example.jwt.exception.advice.exception.TokenException;
import com.example.jwt.exception.advice.exception.UserException;
import com.example.jwt.exception.advice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiError> UserException(UserException e) {
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> UserNotFoundException(UserException e) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<ApiError> PasswordException(PasswordException e) {
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiError> TokendException(PasswordException e) {
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
