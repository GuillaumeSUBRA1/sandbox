package com.example.jwt.dto;

import org.springframework.lang.NonNull;

public record UserRecord(
    @NonNull String email,
    @NonNull String name,
    @NonNull String password
) {
}
