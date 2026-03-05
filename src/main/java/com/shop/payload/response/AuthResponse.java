package com.shop.payload.response;

import com.shop.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private UserDto user;
}
