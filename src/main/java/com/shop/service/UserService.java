package com.shop.service;

import com.shop.exceptions.UserException;
import com.shop.modal.User;
import com.shop.payload.dto.UserDto;

import java.util.List;

public interface UserService {

    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException, Exception;
    List<User> getAllUsers();
}
