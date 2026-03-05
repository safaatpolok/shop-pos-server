package com.shop.service;

import com.shop.exceptions.UserException;
import com.shop.payload.dto.UserDto;
import com.shop.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;

}
