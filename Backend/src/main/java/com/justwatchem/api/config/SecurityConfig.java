package com.justwatchem.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.justwatchem.api.services.UserService;

// This class will configure both authentication and authorization for users.
@Configuration
@EnableWebSecurity
public class SecurityConfig{
  @Autowired
  private UserService userService;

  // This method configures authentication for users.
  @Bean // This annotation signifies that when the method is loaded, an object will be created.
  protected UserDetailsService userDetailsService(){
    UserDetails user = User.withUsername("email")
      .password(passwordEncoder().encode("password"))
      .roles("user")
      .build();

      return new InMemoryUserDetailsManager(user);
  }

  // This method configures the authorization for API endpoints.
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.csrf(csrf -> csrf.disable()) // For testing purposes, NOT for production builds.
        .authorizeHttpRequests(auth -> auth
        .requestMatchers(HttpMethod.GET, "/Listing/**").permitAll() // Allows anyone to send a GET request to all /Listing endpoints.
        .requestMatchers(HttpMethod.GET, "/Details/**").permitAll() // Allows anyone to send a GET request to all /Details endpoints.
        .requestMatchers(HttpMethod.POST, "/Details/**").permitAll() // Allows anyone to send a POST request to all /Details endpoints.
        .requestMatchers(HttpMethod.PUT, "/Details/**").permitAll() // Allows anyone to send a PUT request to all /Details endpoints.
        .requestMatchers(HttpMethod.DELETE, "/Details/**").permitAll() // Allows anyone to send a DELETE request to all /Details endpoints.
        .requestMatchers(HttpMethod.GET, "/Users/**").permitAll() // For testing User endpoints, remove afterwards.
        .requestMatchers(HttpMethod.POST, "/Users/**").permitAll() // For testing User endpoints, remove afterwards.
        .anyRequest().authenticated()
      ).formLogin(Customizer.withDefaults());

    return http.build();
  }

  // This method encodes the user's password.
  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
