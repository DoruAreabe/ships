package com.example.thymeleaf.mappers;

import com.example.thymeleaf.model.dto.UserDto;
import com.example.thymeleaf.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public final class UserMapper {
    private UserMapper() {
    }

    public static UserDto toUserDto(User user) {

        UserDto dto = new UserDto();

        dto.setUserid(user.getUserid());
        dto.setEmailId(user.getEmailId());
        dto.setPassword(user.getPassword());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRoles(user.getRoles());
        dto.setEnabled(user.isEnabled());
        dto.setProvider(user.getProvider());
        return dto;
    }

    public static User toEntity(UserDto dto) {

        User user = new User();

        user.setUserid(dto.getUserid());
        user.setEmailId(dto.getEmailId());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRoles(dto.getRoles());
        user.setEnabled(dto.isEnabled());
        user.setProvider(dto.getProvider());
        return user;
    }

    public static List<UserDto> mapAllUser(List<User> users) {
        return users.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
