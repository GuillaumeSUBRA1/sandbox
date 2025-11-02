package com.example.jwt.dto;

import lombok.Data;

@Data
public class UserDTO {
    Long id;
    String email;
    String password;
}
