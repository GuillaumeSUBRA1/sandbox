package com.example.jwt.dto;

public record UserRecord(
    String email,
    String name,
    String password
) {
}
