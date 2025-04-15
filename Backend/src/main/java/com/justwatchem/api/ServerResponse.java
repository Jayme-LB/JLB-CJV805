package com.justwatchem.api;

import java.util.List;

// This class will create customized responses for any HTTP requests.
public class ServerResponse<T> {
  private String message; // Describes what "body" contains.
  private List<T> body; // List of objects. It's declared as a generic type so it can hold movies, users, etc.

  // The constructor populates all fields with the data in the parameters.
  public ServerResponse(String message, List<T> body) {
    this.message = message;
    this.body = body;
  }

  // This method is a Getter for the message.
  public String getMessage() {
    return message;
  }

  // This method is a Setter for the message.
  public void setMessage(String message) {
    this.message = message;
  }

  // This method is a Getter for the body.
  public List<T> getBody() {
    return body;
  }

  // This method is a Setter for the body.
  public void setBody(List<T> body) {
    this.body = body;
  } 
}
