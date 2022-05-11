package com.example.thymeleaf.service;

import com.example.thymeleaf.model.User;
import com.example.thymeleaf.model.providers.Provider;
import com.example.thymeleaf.model.roles.Role;
import com.example.thymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public User findUserByEmailIdIgnoreCase(String emailId) {
        return repository.findUserByEmailIdIgnoreCase(emailId);
    }

    @Override
    public void processOAuthPostLogin(CustomOAuth2User oAuth2User) {
        String email = oAuth2User.getEmail();
        User existingUser = findUserByEmailIdIgnoreCase(email);
        if (existingUser == null) {
            User user = new User();
            user.setEnabled(true);
            user.setEmailId(email);
            user.setFirstName(oAuth2User.getGivenName());
            user.setLastName(oAuth2User.getFamilyName());
            user.getRoles().add(Role.USER);
            user.setProvider(Provider.GOOGLE);
            saveUser(user);
        }
    }

    @Override
    @Transactional
    public void deleteUserByEmailId(String emailId) {
        User user = findUserByEmailIdIgnoreCase(emailId);
        if (user != null) {
            confirmationTokenService.removeConfirmationTokenByUserId(user.getUserid());
            repository.delete(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmailIdIgnoreCase(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("User is not active!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r.getCode())));

        return grantedAuthorities;
    }

}
