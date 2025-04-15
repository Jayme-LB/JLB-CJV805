package com.justwatchem.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// This class holds the details of a user's account.
@Document("users")
public class Users{
  @Id
  private String id; // The numerical ID of the user.
  private String first_name; // The user's first name.
  private String last_name; // The user's first name.
  private String email; // The user's email address.
  private String password; // The user's (encrypted) password.

  // The constructor populates all fields with the appropriate data.
  public Users(String id, String first_name, String last_name, String email, String password){
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    this.password = password;
  }
  
  // This method is the Getter for the ID.
  public String getId(){
    return id;
  }

  // This method is the Setter for the ID.
  public void setId(String id){
    this.id = id;
  }

  // This method is the Getter for the first name.
  public String getFirst_name(){
    return first_name;
  }

  // This method is the Setter for the first name.
  public void setFirst_name(String first_name){
    this.first_name = first_name;
  }

  // This method is the Getter for the last name.
  public String getLast_name(){
    return last_name;
  }

  // This method is the Setter for the last name.
  public void setLast_name(String last_name){
    this.last_name = last_name;
  }

  // This method is the Getter for the email address.
  public String getEmail(){
    return email;
  }

  // This method is the Setter for the email address.
  public void setEmail(String email){
    this.email = email;
  }

  // This method is the Getter for the password.
  public String getPassword(){
    return password;
  }

  // This method is the Setter for the password.
  public void setPassword(String password){
    this.password = password;
  }
}
