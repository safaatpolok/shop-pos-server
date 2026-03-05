package com.shop.service.impl;

import com.shop.Repositorty.UserRepository;
import com.shop.domain.UserRole;
import com.shop.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        initializeAdminUser();

    }

    private  void initializeAdminUser(){
        String adminUserName="polokAdmin@gmail.com";
        if(userRepository.findByEmail(adminUserName) == null){
            User adminUser=new User();
            adminUser.setPassword(passwordEncoder.encode("polokAdmin"));
            adminUser.setFullName("polok");
            adminUser.setEmail(adminUserName);
            adminUser.setRole(UserRole.ROLE_ADMIN);
            adminUser.setPhone("01700000000");

            User admin=userRepository.save(adminUser);

        }
    }
}
