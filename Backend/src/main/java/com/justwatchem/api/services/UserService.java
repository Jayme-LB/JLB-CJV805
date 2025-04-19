package com.justwatchem.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.justwatchem.api.models.UserRepository;
import com.justwatchem.api.models.Users;

// This class will communicate with the MongoDB collection.
@Service
public class UserService{
  @Autowired
  private UserRepository repo; // For calling methods from the UserRepository/MongoRepository interface.
  @Autowired
  private MongoTemplate mongo; // For more complex queries that the MovieRepository can't handle.
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  // This method will add a new user to the database.
  public Users addUser(Users user) throws Exception{
    Query query = new Query(); // For the MongoDB query.
    Users newUser; // Return value.

    // Throw an exception if the email address is already in the database.
    query.addCriteria(Criteria.where("email").is(user.getEmail()));
    newUser = mongo.findOne(query, Users.class);
    if (newUser != null)
      throw new Exception("That email address is already in use.");

    newUser = repo.insert(user);

    return newUser;
  }

  // This method will select all users in the database.
  public List<Users> getAllUsers() throws Exception{
    List<Users> users = repo.findAll(); // Return value.

    // Throw an exception if there's no data.
    if (users == null || users.isEmpty())
      throw new Exception("No users were found in the database somehow.");

    return users;
  }
  
  // This method will select a user from the database that
  // matches the given ID.
  public Optional<Users> getUser(String id) throws Exception{
    Optional<Users> user = repo.findById(id); // Return value.

    // Throw an exception if the user ID does not exist in the database.
    if (!user.isPresent())
      throw new Exception("No user with that ID was found in the database.");

    return user;
  }

  // This method will select a user from the database that
  // matches the given email and (encrypted) password.
  public Users getUserLogin(String email, String password) throws BadCredentialsException{
    Query query = new Query(); // For the MongoDB query.
    Users user; // Return value.

    // Throw an exception if the email or password don't match anywhere.
    query.addCriteria(Criteria.where("email").is(email));
    user = mongo.findOne(query, Users.class);
    if (user == null)
      throw new BadCredentialsException("That username is invalid.");
    else if (!passwordEncoder.matches(password, user.getPassword()))
      throw new BadCredentialsException("That password is invalid.");

    return user;
  }
}
