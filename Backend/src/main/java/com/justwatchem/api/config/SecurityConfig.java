package com.justwatchem.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// This class will configure both authentication and authorization for users.
@Configuration
@EnableWebSecurity
public class SecurityConfig{
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
      ).formLogin(Customizer.withDefaults())
      .cors(Customizer.withDefaults());

    return http.build();
  }

  // This method configures CORS to allow the React client to make requests to this application.
  @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("http://localhost:3000", "https://jlb-cjv805.vercel.app/")
					.allowedMethods("GET", "POST")
					.allowedHeaders("*");
			}
		};
	}

  // This method encodes the user's password.
  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
