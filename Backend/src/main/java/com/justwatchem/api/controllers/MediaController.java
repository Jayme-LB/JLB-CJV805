package com.justwatchem.api.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.justwatchem.api.ServerResponse;
import com.justwatchem.api.models.Media;
import com.justwatchem.api.services.MediaService;

// This class is the Spring REST controller that listens to GET/POST requests
// coming from the client-side and handles them here, on the server-side.
@RestController
public class MediaController {
  @Autowired
  private MediaService service; // For calling MediaService class methods.
  
  // This method will create/add a new media entry to the API.
  // NOTE: The "consumes" parameter tells Spring Boot that the POST data is a JSON object.
  @PostMapping(value="/Details", consumes={MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity addMedia(@RequestBody Media media){
    ServerResponse response; // First argument of the return value that holds the media details, if it is valid.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check that the API actually has any TV show entries.
    try{
      service.addMedia(media);
      // Because the ServerResponse expects a List<T> as the second argument, we must use a wrapper for our single object.
      // The second argument will also throw an exception if the ID is not found.
      response = new ServerResponse<>("A new media entry has been successfully added.", Collections.singletonList(media));
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will return all media entries from the API.
  @GetMapping("/Listing")
  public ResponseEntity getAllMedia(){
    ServerResponse response; // First argument of the return value that holds all media details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check that the API actually has any media entries.
    try{
      response = new ServerResponse<>("All media entries.", service.getAllMedia());
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will return all movie entries from the API.
  @GetMapping("/Listing/Movies")
  public ResponseEntity getAllMovies(){
    ServerResponse response; // First argument of the return value that holds all movie details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check that the API actually has any movie entries.
    try{
      response = new ServerResponse<>("All movies.", service.getAllMovies());
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will return all TV show entries from the API.
  @GetMapping("/Listing/TVShows")
  public ResponseEntity getAllTVShows(){
    ServerResponse response; // First argument of the return value that holds all TV show details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check that the API actually has any TV show entries.
    try{
      response = new ServerResponse<>("All TV shows.", service.getAllTVShows());
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will return all media entries from the API that contain the search term.
  @GetMapping("/Listing/{searchTerm}")
  public ResponseEntity getAllMedia(@PathVariable("searchTerm") String searchTerm){
    ServerResponse response; // First argument of the return value that holds all relevant media details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check that the API actually has any media entries.
    try{
      response = new ServerResponse<>("All media entries with their name containg the term '" + searchTerm + "'.", service.getAllMedia(searchTerm));
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will return all featured movies from the API.
  @GetMapping("/Listing/Movies/Featured")
  public ResponseEntity getAllFeaturedMovies(@RequestParam(value = "featured") String queryParam){
    ServerResponse response; // First argument of the return value that holds all featured movie details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check that the API actually has any movie entries.
    try{
      response = new ServerResponse<>("All movies.", service.getAllFeaturedMovies(queryParam));
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will return all featured TV shows from the API.
  @GetMapping("/Listing/TVShows/Featured")
  public ResponseEntity getAllFeaturedTVShows(@RequestParam(value = "featured") String queryParam){
    ServerResponse response; // First argument of the return value that holds all featured TV show details.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check that the API actually has any TV show entries.
    try{
      response = new ServerResponse<>("All TV shows.", service.getAllFeaturedTVShows(queryParam));
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will return a media entry from the API, based on the given route parameter.
  @GetMapping("/Details/{mediaID}")
  public ResponseEntity getMedia(@PathVariable("mediaID") String id){
    ServerResponse response; // First argument of the return value that holds the media details, if the ID is found.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check if the media entry exists in the first place.
    try{
      response = new ServerResponse<>("A media entry with the ID " + id + ".", Collections.singletonList(service.getMedia(id)));
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }
    
    return new ResponseEntity<>(response, status);
  }

  // This method will edit a media entry in the API, based on the given route parameter.
  @PutMapping(value = "/Details/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity updateMedia(@PathVariable("id") String id, @RequestBody Media updatedMedia){
    ServerResponse response; // First argument of the return value that holds the media details, if the ID is found.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.
  
    // Try to update an existing media entry.
    try{
      response = new ServerResponse<>("The media entry with the ID " + id + " has been updated successfully.", Collections.singletonList(service.updateMedia(id, updatedMedia)));
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }

  // This method will delete a media entry from the API, based on the given route parameter.
  @DeleteMapping("/Details/{mediaID}")
  public ResponseEntity deleteMedia(@PathVariable("mediaID") String id){
    ServerResponse response; // First argument of the return value that holds the media details, if it is valid.
    HttpStatus status; // Second argument of the return value that holds a proper HTTP response status.

    // Check if the media entry exists in the first place.
    try{
      service.deleteMedia(id);
      response = new ServerResponse<>("The media entry has been deleted from the database.", null);
      status = HttpStatus.OK;
    } catch (Exception e){
      response = new ServerResponse<>(e.getMessage(), null);
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(response, status);
  }
}
