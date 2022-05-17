package com.example.thymeleaf.controller.api;

import com.example.thymeleaf.mappers.UserMapper;
import com.example.thymeleaf.model.entity.User;
import com.example.thymeleaf.model.dto.UserDto;
import com.example.thymeleaf.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllerApi {

    private final UserService userService;

    public UserControllerApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) {
        User user = userService.findUserByEmailIdIgnoreCase(email);
        if (user != null) {
            UserDto userDto = UserMapper.toUserDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = UserMapper.mapAllUser(userService.getAllUsers());
        if (userDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
}
