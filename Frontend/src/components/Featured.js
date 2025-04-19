import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid2";

// This component renders the featured section of either movies or TV shows,
// depending on the given prop.
const Featured = ({ featuredMediaType }) => {
  // Use the first six media entries in the API to use for the "Featured" section.
  const [featuredMedia, setFeaturedMedia] = useState([]); // All of the featured media type from the API.
  const [featuredMediaTitle, setFeaturedMediaTitle] = useState(""); // Heading of the featured media type.

  useEffect(() => {
    const fetchData = () => {
      return new Promise((resolve, reject) =>
        fetch(`http://localhost:8080/Listing/${featuredMediaType}/Featured?featured=true`)
          .then(response => response.json())
          .then(mediaData => resolve(mediaData))
          .catch(err => reject(err))
      );
    };

    // Set the heading.
    if (featuredMediaType === "Movies") {
      setFeaturedMediaTitle("Featured Movies");
    } else if (featuredMediaType === "TVShows") {
      setFeaturedMediaTitle("Featured TV Shows");
    }

    // Call the above function with the proper endpoint to fetch the featured media.
    fetchData()
      .then(mediaData => setFeaturedMedia(mediaData.body))
      .catch(err =>
        console.error("There was a problem fetching the featured media data: ", err));
  }, []);

  return (
    <Box>
      <Typography
        variant="h4"
        fontWeight="bold"
        marginBottom={1}
        sx={{ textShadow: "orangered 2px 1px 5px" }}
      >
        {featuredMediaTitle}
      </Typography>
      <Grid container columns={6} spacing={4}>
        {featuredMedia.map((media, index) => (
          <Grid key={index} sx={{ width: 185 }} size={1}>
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
    </Box>
  );
};

export default Featured;