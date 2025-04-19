import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import AppButton from "../components/AppButton";

// This component renders the Details page, including the appropriate
// movie or TV show details depending on the given URL parameter.
const Details = () => {
  const { mediaID } = useParams(); // Grabs the route parameter from the URL.
  const [mediaDetails, setMediaDetails] = useState([]);

  useEffect(() => {
    const fetchData = () => {
      return new Promise((resolve, reject) =>
        fetch(`http://localhost:8080/Details/${mediaID}`)
          .then(response => response.json())
          .then(mediaData => {
            // Convert the release date into a more user-friendly format.
            mediaData.body.at(0).release_date = new Date(mediaData.body.at(0).release_date)
            .toLocaleDateString("en-CA",
              {
                timeZone: "UTC",
                month: "long",
                day: "numeric",
                year: "numeric",
              }
            );
            // Return the single index of the entity body.
            resolve(mediaData.body.at(0));
          })
          .catch(err => reject(err))
      );
    };

    // Call the above function with the proper endpoint to fetch the featured media.
    fetchData()
      .then(mediaData => setMediaDetails(mediaData))
      .catch(err => 
        console.error("There was a problem fetching the media details: ", err));
  }, []);
    
  if (!mediaDetails) {
    return (
      <Box sx={{ padding: "20px" }}>
        <Typography variant="h5" color="error">
          No movie or TV show with that ID has been found.
        </Typography>
      </Box>
    );
  }

  return (
    <Box>
      <Box
        sx={{
          backgroundImage: `url(https://image.tmdb.org/t/p/w1280/${mediaDetails.backdrop_path})`,
          backgroundRepeat: "no-repeat",
          width: "1280px",
          padding: "5px 15px 10px",
        }}
      >
        <Typography
          variant="h2"
          fontWeight="bold"
          sx={{
            color: "white",
            fontWeight: "bold",
            textShadow: "1px 1px 1px black, 4px 4px 3px orangered",
          }}
        >
          {mediaDetails.name}
        </Typography>
        <Box
          sx={{
            display: "flex",
            width: "1260px",
          }}
        >
          <Box
            component="img"
            alt={mediaDetails.name}
            src={`https://image.tmdb.org/t/p/w342/${mediaDetails.poster_path}`}
            sx={{
              borderRadius: "10px",
              boxShadow: 24,
            }}
          />
          <Box sx={{ paddingLeft: "5px" }}>
            <Typography
              variant="h6"
              sx={{
                color: "white",
                fontWeight: "bold",
                textShadow: "1px 1px 1px black, 2px 2px 2px orangered",
              }}
            >
              Released on {mediaDetails.release_date}
            </Typography>
            <Typography
              variant="body1"
              sx={{
                color: "white",
                textShadow: "3px 2px 2px black, 1px 1px 1px orangered",
              }}
            >
              {mediaDetails.overview}
            </Typography>
            <AppButton variant="outlined">Buy for ${mediaDetails.price}</AppButton>
            <AppButton variant="outlined">Rent for ${mediaDetails.rental_price}</AppButton>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default Details;