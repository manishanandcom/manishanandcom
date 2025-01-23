package com.manish.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Generate a random username
        String randomUsername = "user-" + UUID.randomUUID().toString().substring(0, 8);

        // Create a user with the random username and fixed password
        UserDetails user = User.withDefaultPasswordEncoder()
                .username(randomUsername)
                .password("12345")
                .roles("USER")
                .build();

        System.out.println("Generated Username: " + randomUsername); // Log the username to the console

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .requestMatchers("/", "/pi.jpeg").permitAll() // Allow access to home and static resources
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
