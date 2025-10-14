package com.example.jwt.dto;

public record UserRecord(
    String username,
    String email,
    String password
) {
}
