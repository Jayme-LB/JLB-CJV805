package com.justwatchem.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// This class holds the details of a type of media (movie/TV show).
@Document("media")
public class Media {
  @Id
  private String id; // The object ID of the media entry.
  private String name; // The name of the media, in English.
  private String overview; // The synopsis of the media.
  private String media_type; // Determines whether the media is a movie or a TV show.
  private String backdrop_path; // The file path for the media's backdrop image.
  private String poster_path; // The file path for the media's poster image.
  private String release_date; // The date that the media originally released/aired.
  private double price; // The price to buy the movie/TV show.
  private double rental_price; // The price to rent the movie/TV show.
  private boolean is_featured; // If true, then the media will be featured on front page of the website.

  // The constructor populates all fields with the appropriate data.
  public Media(String id, String name, String overview, String media_type, String backdrop_path, String poster_path, String release_date, double price, double rental_price, boolean is_featured){
    this.id = id;
    this.name = name;
    this.overview = overview;
    this.media_type = media_type;
    this.backdrop_path = backdrop_path;
    this.poster_path = poster_path;
    this.release_date = release_date;
    this.price = price;
    this.rental_price = rental_price;
    this.is_featured = is_featured;
  }

  // This method is the Getter for the ID.
  public String getId(){
    return id;
  }

  // This method is the Setter for the ID.
  public void setId(String id){
    this.id = id;
  }

  // This method is the Getter for the name.
  public String getName(){
    return name;
  }

  // This method is the Setter for the name.
  public void setName(String name){
    this.name = name;
  }

  // This method is the Getter for the synopsis.
  public String getOverview(){
    return overview;
  }

  // This method is the Setter for the synopsis.
  public void setOverview(String overview){
    this.overview = overview;
  }

  // This method is the Getter for the media type.
  public String getMedia_type(){
    return media_type;
  }

  // This method is the Setter for the media type.
  public void setMedia_type(String media_type) {
    this.media_type = media_type;
  }

  // This method is the Getter for the backdrop image's file path.
  public String getBackdrop_path(){
    return backdrop_path;
  }

  // This method is the Setter for the backdrop image's file path.
  public void setBackdrop_path(String backdrop_path){
    this.backdrop_path = backdrop_path;
  }

  // This method is the Getter for the poster image's file path.
  public String getPoster_path(){
    return poster_path;
  }

  // This method is the Setter for the poster image's file path.
  public void setPoster_path(String poster_path){
    this.poster_path = poster_path;
  }
  
  // This method is the Getter for the release date.
  public String getRelease_date(){
    return release_date;
  }

  // This method is the Setter for the release date.
  public void setRelease_date(String release_date){
    this.release_date = release_date;
  }

  // This method is the Getter for the buy price.
  public double getPrice(){
    return price;
  }

  // This method is the Setter for buy price.
  public void setPrice(double price) {
    this.price = price;
  }

  // This method is the Getter for rental price.
  public double getRental_price(){
    return rental_price;
  }

  // This method is the Setter for rental price.
  public void setRental_price(double rental_price) {
    this.rental_price = rental_price;
  }

  // This method is the Getter for whether the media is featured or not.
  public boolean getIs_featured(){
    return is_featured;
  }

  // This method is the Setter for whether the media is featured or not.
  public void setIs_featured(boolean is_featured) {
    this.is_featured = is_featured;
  }

  // This method overrides the toString() method by returning the media object.
  @Override
  public String toString(){
    return "Media {" +
           "id=" + id + "," +
           "name=\"" + name + "\"," +
           "overview=\"" + overview + "\"," +
           "media_type=\"" + media_type + "\"," +
           "backdrop_path\"" + backdrop_path + "\"," +
           "poster_path\"" + poster_path + "\"," +
           "release_date\"" + release_date + "\"," +
           "price" + price + "," +
           "rental_price" + rental_price + "," +
           "is_featured" + is_featured + "," +
           "}";
  }
}
