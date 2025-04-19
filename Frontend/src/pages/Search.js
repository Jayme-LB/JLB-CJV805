import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid2";

// This component renders the Search page.
const Search = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [matchingMedia, setMatchingMedia] = useState([]); // Gets ALL available media in the API.

  useEffect(() => {
    const fetchData = () => {
      return new Promise((resolve, reject) =>
        fetch(`http://localhost:8080/Listing/${searchTerm}`)
          .then(response => response.json())
          .then(mediaData => resolve(mediaData))
          .catch(err => reject(err))
      );
    };

    // Call the above function with the proper endpoint to fetch the featured media.
    if (searchTerm === "")
      setMatchingMedia([]);
    else
      fetchData()
        .then(mediaData => setMatchingMedia(mediaData.body))
        .catch(err => 
          console.error("There was a problem searching for media in the database: ", err));
    }, [searchTerm]);
    
  return (
    <Box
      sx={{
        maxWidth: "1280px",
        margin: "auto",
        marginTop: "5px",
        marginBottom: "2em"
      }}
    >
      <Typography
        variant="h4"
        fontWeight="bold"
        marginBottom={1}
        sx={{ textShadow: "orangered 2px 1px 5px" }}
      >
        Search Em'
      </Typography>
      <TextField
        label="Search for movies and TV shows"
        name="search"
        fullWidth
        sx={{
          backgroundColor: "white",
          marginTop: "2px",
          marginBottom: "10px"
        }}
        value={searchTerm}
        onChange={(event) => {
          setSearchTerm(event.target.value)
        }}
      />
      {matchingMedia ? (
        <Grid
          container
          columns={6}
          spacing={2}
        >
          {matchingMedia.map((media, index) => (
            <Grid key={index} size={1} sx={{ width: 185 }}>
              <Link to={`/Details/${media.id}`} className="media-link">
                <Box
                  component="img"
                  alt={media.name}
                  src={`https://image.tmdb.org/t/p/w185/${media.poster_path}`}
                />
                {media.name}
              </Link>
            </Grid>
          ))}
        </Grid>
      ) : (
        <></>
      )}
    </Box>
  )
}

export default Search;