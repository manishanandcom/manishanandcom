package com.manish.portfolio.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .requestMatchers("/", "/pi.jpeg", "/User").permitAll() // Allow access to `/User`
                .anyRequest().authenticated() // Restrict other pages
            .and()
            .formLogin()
                .loginPage("/login") // Custom login page
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/")
                .permitAll();
        return http.build();
    }
}
