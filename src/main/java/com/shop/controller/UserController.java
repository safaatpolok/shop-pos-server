package com.shop.controller;

import com.shop.exceptions.UserException;
import com.shop.mapper.UserMapper;
import com.shop.modal.User;
import com.shop.payload.dto.UserDto;
import com.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/api/users/profile")

    public ResponseEntity<UserDto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    )                throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user));

    }


    @GetMapping("/api/users/{id}")

    public ResponseEntity<UserDto>getUserById
            (
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws UserException, Exception {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(UserMapper.toDTO(user));

    }
    @GetMapping("/users/list")

    public ResponseEntity<List<User>>getUserList
            (

            ) throws UserException, Exception {
        List <User> users= userService.getAllUsers();

        return ResponseEntity.ok(users);

    }


}
