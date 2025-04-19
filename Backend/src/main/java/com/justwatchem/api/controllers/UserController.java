package com.justwatchem.api.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.justwatchem.api.ServerResponse;
import com.justwatchem.api.models.Users;
import com.justwatchem.api.services.UserService;

// This class is the Spring REST controller that listens to HTTP requests
// coming from the client-side and handles them here, on the server-side.
@RestController
public class UserController {
  @Autowired
  private UserService service; // For calling UserService class methods.
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  // This method will create/add a new user to the API.
  // NOTE: The "consume" parameter tells Spring Boot that the POST data is a JSON object.
  @SuppressWarnings("rawtypes")
  @PostMapping(value = "/Users", consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity addUser(@RequestBody Users user) {
    ServerResponse response; // First argument of the return value that holds the user details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.
    // Encrypt the given password before adding it to the API.
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    // Check that the email doesn't exist in the API.
    try{
      service.addUser(user);
      // Because the ServerResponse expects a List<T> as the second argument, we must use a wrapper for our single object.
      // The second argument will also throw an exception if the ID is not found.
      response = new ServerResponse<>("A new media entry has been successfully added.", Collections.singletonList(user));
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.CONFLICT;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will authenticate a user login request.
  @SuppressWarnings("rawtypes")
  @PostMapping(value = "/Users/LogIn", consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity logInUser(@RequestBody Users user) {
    ServerResponse response; // First argument of the return value that holds the user details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.
    
    // Check that the email and password are valid.
    try {
      service.getUserLogin(user.getEmail(), user.getPassword());
      response = new ServerResponse<>("Login successful!", null);
      status = HttpStatus.OK;
    } catch (BadCredentialsException bde) {
      response = new ServerResponse<>(bde.getMessage(), null);
      status = HttpStatus.UNAUTHORIZED;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will return all users from the API.
  @SuppressWarnings("rawtypes")
  @GetMapping("/Users")
  public ResponseEntity getAllUsers() {
    ServerResponse response; // First argument of the return value that holds all user details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.
    
    // Check that the API actually has any users.
    try{
      response = new ServerResponse<>("All users.", service.getAllUsers());
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will return a user from the API, based on the given ID from the URL route parameter.
  @SuppressWarnings("rawtypes")
  @GetMapping("/Users/{userID}")
  public ResponseEntity getUser(@PathVariable("userID") String id) {
    ServerResponse response; // First argument of the return value that holds the user details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check if the user exists in the API.
    try{
      response = new ServerResponse<>("A user with the ID " + id + ".", Collections.singletonList(service.getUser(id)));
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }
}
