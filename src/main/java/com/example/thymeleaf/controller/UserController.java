package com.example.thymeleaf.controller;

import com.example.thymeleaf.model.User;
import com.example.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String allUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping(value = "/users/{username}")
    public String deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/users";
    }

    @GetMapping("/showFormForUpdate/{username}")
    public String showFormForUpdate(@PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);
        //set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "updateForm";
    }

    @PostMapping("/saveUpdatedUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
