package com.justwatchem.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.justwatchem.api.models.Media;
import com.justwatchem.api.models.MediaRepository;

// This class will communicate with the MongoDB collection.
@Service
public class MediaService {
  @Autowired
  private MediaRepository repo; // For calling methods from the MovieRepository/MongoRepository interface.
  @Autowired
  private MongoTemplate mongo; // For more complex queries that the MovieRepository can't handle.
  
  // This method will add a new media entry to the database.
  public void addMedia(Media media) throws Exception{
    // Throw an exception if the media ID exists in the database.
    if (repo.findById(media.getId()).isPresent())
      throw new Exception("A media entry with that ID already exists in the database.");
    else
      repo.insert(media);
  }

  // This method will select all media entries in the database.
  public List<Media> getAllMedia() throws Exception{
    List<Media> media = repo.findAll(); // Return value.

    // Throw an exception if there's no data.
    if (media == null || media.isEmpty())
      throw new Exception("No media entries were found in the database somehow.");

    return media;
  }

  // This method will select all movies in the database.
  public List<Media> getAllMovies() throws Exception{
    Query query = new Query(); // For the MongoDB query.
    List<Media> movies; // Return value.

    // Set the criteria for the query.
    query.addCriteria(Criteria.where("media_type").is("movie"));

    // Execute the query and add it to the list.
    movies = mongo.find(query, Media.class);

    // Throw an exception if there's no data.
    if (movies == null || movies.isEmpty())
      throw new Exception("No are currently no movies in the database. Why not add some?");

    return movies;
  }

  // This method will select all TV shows in the database.
  public List<Media> getAllTVShows() throws Exception{
    Query query = new Query(); // For the MongoDB query.
    List<Media> tvShows; // Return value.

    // Set the criteria for the query.
    query.addCriteria(Criteria.where("media_type").is("tv"));

    // Execute the query and add it to the list.
    tvShows = mongo.find(query, Media.class);

    // Throw an exception if there's no data.
    if (tvShows == null || tvShows.isEmpty())
      throw new Exception("No are currently no TV shows in the database. Why not add some?");

    return tvShows;
  }

  // This method will select all media entries in the database
  // where the name of the media contains the given search term.
  public List<Media> getAllMedia(String searchTerm) throws Exception{
    Query query = new Query(); // For the MongoDB query.
    List<Media> media; // Return value.

    // Set the criteria for the query.
    query.addCriteria(Criteria.where("name").regex(".*" + searchTerm + ".*", "i"));

    // Execute the query and add it to the list.
    media = mongo.find(query, Media.class);

    // Throw an exception if there's no data.
    if (media == null || media.isEmpty())
      throw new Exception("No media entries has their name containing the term '" + searchTerm + "'.");

    return media;
  }

  // This method will return all featured movies in the database.
  public List<Media> getAllFeaturedMovies(String queryParam) throws Exception{
    Query query = new Query(); // For the MongoDB query.
    List<Media> featuredMovies; // Return value.

    // Throw an exception if the query string is not "true".
    if (!"true".equals(queryParam))
      throw new Exception("Featured query paramater is invalid.");

    // Set the criteria for the query.
    query.addCriteria(Criteria.where("media_type").is("movie"));
    query.addCriteria(Criteria.where("is_featured").is(true));

    // Execute the query and add it to the list.
    featuredMovies = mongo.find(query, Media.class);

    // Throw an exception if there's no data.
    if (featuredMovies == null || featuredMovies.isEmpty())
      throw new Exception("There are currently no featured movies in the database.");

    return featuredMovies;
  }

  // This method will return all featured TV shows in the database.
  public List<Media> getAllFeaturedTVShows(String queryParam) throws Exception{
    Query query = new Query(); // For the MongoDB query.
    List<Media> featuredTVShows; // Return value.

    // Throw an exception if the query string is not "true".
    if (!"true".equals(queryParam))
      throw new Exception("Featured query paramater is invalid.");

    // Set the criteria for the query.
    query.addCriteria(Criteria.where("media_type").is("tv"));
    query.addCriteria(Criteria.where("is_featured").is(true));

    // Execute the query and add it to the list.
    featuredTVShows = mongo.find(query, Media.class);

    // Throw an exception if there's no data.
    if (featuredTVShows == null || featuredTVShows.isEmpty())
      throw new Exception("There are currently no featured TV shows in the database.");

    return featuredTVShows;
  }

  // This method will select a media entry from the database
  // that matches the given ID.
  public Optional<Media> getMedia(String id) throws Exception{
    Optional<Media> media = repo.findById(id); // Return value.

    // Throw an exception if the media ID does not exist in the database.
    if (!media.isPresent())
      throw new Exception("No media with the ID " + id + " was found in the database.");

    return media;
  }

  // This method will update an existing media entry in the database.
  public Media updateMedia(String id, Media updatedMedia) throws Exception{
    Optional<Media> media = repo.findById(id); // Get the media entry based on ID.
    Media updatedMovie; // Return value.
    
    // Throw an exception if the media ID does not exist in the database.
    if (!media.isPresent())
      throw new Exception("No media with the ID " + id + " was found in the database somehow.");

    // Update the found resource with the new data.
    media.get().setName(updatedMedia.getName());
    media.get().setOverview(updatedMedia.getOverview());
    media.get().setMedia_type(updatedMedia.getMedia_type());
    media.get().setBackdrop_path(updatedMedia.getBackdrop_path());
    media.get().setPoster_path(updatedMedia.getPoster_path());
    media.get().setRelease_date(updatedMedia.getRelease_date());
    media.get().setPrice(updatedMedia.getPrice());
    media.get().setRental_price(updatedMedia.getRental_price());
    media.get().setIs_featured(updatedMedia.getIs_featured());

    // Save the update to commit it to the database.
    updatedMovie = repo.save(media.get());

    return updatedMovie;
  }

  // This method will delete a media entry from the database.
  public void deleteMedia(String id) throws Exception{
    // Throw an exception if the media ID does not exist in the database.
    if (!repo.findById(id).isPresent())
      throw new Exception("No media with the ID " + id + " was found in the database.");
    else
      repo.deleteById(id);
  }
}
