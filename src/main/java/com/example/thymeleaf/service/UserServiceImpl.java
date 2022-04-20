package com.example.thymeleaf.service;

import com.example.thymeleaf.model.User;
import com.example.thymeleaf.repository.UserRepository;
import com.example.thymeleaf.controller.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public void saveUserRegistrationDto(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(userRegistrationDto.getPassword());
        user.setRole("ROLE_USER");
        repository.save(user);
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = repository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User: " + username + " not found.");
        }
        return user;
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = repository.findUserByUsername(username);
        if (user != null) {
            repository.delete(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String[] split = role.split(",");
        for (String s : split) {
            grantedAuthorities.add(new SimpleGrantedAuthority(s));
        }
        return grantedAuthorities;
    }
}
