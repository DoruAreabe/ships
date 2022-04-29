package com.example.thymeleaf.controller;

import com.example.thymeleaf.mappers.UserMapper;
import com.example.thymeleaf.model.dto.UserDto;
import com.example.thymeleaf.mailconfiguration.MailMessageConfiguration;
import com.example.thymeleaf.model.ConfirmationToken;
import com.example.thymeleaf.model.User;
import com.example.thymeleaf.model.roles.Role;
import com.example.thymeleaf.service.ConfirmationTokenServiceImpl;
import com.example.thymeleaf.service.EmailSenderService;
import com.example.thymeleaf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private final UserService userService;
    private final ConfirmationTokenServiceImpl confirmationTokenService;
    private final EmailSenderService emailSenderService;

    public UserRegistrationController(UserService userService, ConfirmationTokenServiceImpl confirmationTokenService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @ModelAttribute("userDto")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("userDto") UserDto userDto, RedirectAttributes redirectAttributes) {
        String userDtoEmailId = userDto.getEmailId();
        User existingUser = userService.findUserByEmailIdIgnoreCase(userDtoEmailId);
        if (existingUser != null) {
            redirectAttributes.addFlashAttribute(ERROR, "You have already account.");
        } else {
            userDto.getRoles().add(Role.USER);
            userService.saveUser(UserMapper.toEntity(userDto));
            existingUser = userService.findUserByEmailIdIgnoreCase(userDtoEmailId);
            ConfirmationToken token = new ConfirmationToken(existingUser);
            confirmationTokenService.saveToken(token);

            MailMessageConfiguration mailMessage = new MailMessageConfiguration(userDtoEmailId, token.getConfirmationTokenName());

            emailSenderService.sendEmail(mailMessage.getEmail());
            redirectAttributes.addFlashAttribute(SUCCESS, "You have successfully registered! Confirm your email to be able to use the application");
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@ModelAttribute("userDto") UserDto userDTO, @RequestParam("token") String confirmationToken, RedirectAttributes redirectAttributes) {
        ConfirmationToken token = confirmationTokenService.getConfirmationTokenByToken(confirmationToken);
        if (token != null) {
            User user = userService.findUserByEmailIdIgnoreCase(token.getUser().getEmailId());
            user.setEnabled(true);
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute(SUCCESS, "You have successfully registered!");
        } else {
            redirectAttributes.addFlashAttribute(ERROR, "The link is invalid or broken!");
        }
        return "redirect:/login";
    }


}
