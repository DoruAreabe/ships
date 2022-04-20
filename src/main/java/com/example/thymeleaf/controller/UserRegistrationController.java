package com.example.thymeleaf.controller;

import com.example.thymeleaf.controller.dto.UserRegistrationDto;
import com.example.thymeleaf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto, RedirectAttributes redirectAttributes) {
        userService.saveUserRegistrationDto(userRegistrationDto);
        redirectAttributes.addFlashAttribute("success", "You have successfully registered!");
        return "redirect:/login";
    }
}
