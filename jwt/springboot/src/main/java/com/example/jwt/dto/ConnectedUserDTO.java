package com.example.jwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnectedUserDTO {
    Long id;
    String email;
    String name;
    String token;
}
