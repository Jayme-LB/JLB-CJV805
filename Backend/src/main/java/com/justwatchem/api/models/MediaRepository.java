package com.justwatchem.api.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// This interface will handle CRUD operations with MongoDB for the media collection.
@Repository
public interface MediaRepository extends MongoRepository<Media, String>{

}
