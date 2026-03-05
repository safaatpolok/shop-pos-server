package com.shop.mapper;

import com.shop.modal.User;
import com.shop.payload.dto.UserDto;

public class UserMapper {
    public static UserDto toDTO(User savedUser) {

        UserDto userDto = new UserDto();

        userDto.setId(savedUser.getId());
        userDto.setFullName(savedUser.getFullName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setRole(savedUser.getRole());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());
        userDto.setLastLogin(savedUser.getLastLogin());
        userDto.setPhone(savedUser.getPhone());
        userDto.setStoreId(savedUser.getStore()!=null?savedUser.getStore().getId():null);
        userDto.setBranchId(savedUser.getStore()!=null?savedUser.getBranch().getId():null);

        return userDto;
    }
    public static User toEntity(UserDto userDto) {
        User ceratedUser = new User();
        ceratedUser.setEmail(userDto.getEmail());
        ceratedUser.setFullName(userDto.getFullName());
        ceratedUser.setRole(userDto.getRole());
        ceratedUser.setCreatedAt(userDto.getCreatedAt());
        ceratedUser.setUpdatedAt(userDto.getUpdatedAt());
        ceratedUser.setLastLogin(userDto.getLastLogin());
        ceratedUser.setPhone(userDto.getPhone());
        ceratedUser.setPassword(userDto.getPassword());

        return ceratedUser;
    }
}
