package com.example.thymeleaf.controller;

import com.example.thymeleaf.mappers.UserMapper;
import com.example.thymeleaf.model.User;
import com.example.thymeleaf.model.dto.UserDto;
import com.example.thymeleaf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(Model model) {
        List<UserDto> userDtos = UserMapper.mapAllUser(userService.getAllUsers());
        model.addAttribute("userDtos", userDtos);
        return "userList";
    }

    @GetMapping(value = "/{emailid}")
    public String deleteUserByUsername(@PathVariable String emailid) {
        userService.deleteUserByEmailId(emailid);
        return "redirect:/users";
    }

    @GetMapping("/showFormForUpdate/{emailid}")
    public String showFormForUpdate(@PathVariable String emailid, Model model) {
        User user = userService.findUserByEmailIdIgnoreCase(emailid);
        UserDto userDto = UserMapper.toUserDto(user);

        model.addAttribute("user", userDto);
        return "updateForm";
    }

    @PostMapping("/saveUpdatedUser")
    public String updateUser(@ModelAttribute("user") UserDto userDto) {
        userService.saveUser(UserMapper.toEntity(userDto));
        return "redirect:/users";
    }
}
