package com.example.thymeleaf.security;

import com.example.thymeleaf.service.CustomOAuth2User;
import com.example.thymeleaf.service.CustomOAuth2UserService;
import com.example.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private CustomBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin", "/users/**").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/js/**", "/css/**", "/registration/**", "/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();



        http.oauth2Login()
                .loginPage("/login").userInfoEndpoint().userService(oauthUserService)
                .and().successHandler((request, response, authentication) -> {
                    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
                    userService.processOAuthPostLogin(oAuth2User);
                    response.sendRedirect("/");
                }).permitAll()
                .and().exceptionHandling();
    }
}
