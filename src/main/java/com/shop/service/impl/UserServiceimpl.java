package com.shop.service.impl;

import com.shop.Repositorty.UserRepository;
import com.shop.configuration.JwtProvider;
import com.shop.exceptions.UserException;
import com.shop.modal.User;
import com.shop.payload.dto.UserDto;
import com.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email =jwtProvider.getEmailFormtoken(token);
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("Invalid token");
        }
        return user;

    }

    @Override
    public User getCurrentUser() throws UserException {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new UserException("user not found");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new UserException("user not found");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException, Exception {
        return userRepository.findById(id).orElseThrow(
                ()-> new Exception("user not find")
        );
    }



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
