package com.example.jwt.exception.advice.exception;

public class TokenException extends RuntimeException {
    public TokenException(String message) {
        super(message);
    }
}
