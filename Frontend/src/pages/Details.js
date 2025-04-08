import { getTitleOrName, getReleaseOrAirDate } from "../utilities/mediaPropertyHandler";
import React from "react";
import { useParams } from "react-router-dom";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import AppButton from "../components/AppButton";

// This component renders the Details page, including the appropriate
// movie or TV show details depending on the given URL parameter.
const Details = ({ movies, tvShows }) => {
  const { mediaID } = useParams(); // Grabs the route parameter from the URL.
 
  // Wait for data to load, then find the item
  const mediaDetails = [...movies, ...tvShows].find(
    (media) => String(media.id) === mediaID
  );
 
  if (!mediaDetails) {
    return (
      <Box sx={{ padding: "20px" }}>
        <Typography variant="h5" color="error">
          No movie or TV show with that ID has been found.
        </Typography>
      </Box>
    );
  }

  const mediaName = getTitleOrName(mediaDetails); // Title/name of the media object.
  const mediaDate = getReleaseOrAirDate(mediaDetails); // Initial release/air date of the media object.
  const releaseDate = new Date(mediaDate) // Alters "mediaDate" into a more user-friendly format.
    .toLocaleDateString("en-CA",
      {
        timeZone: "UTC",
        month: "long",
        day: "numeric",
        year: "numeric",
      }
  );

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
          {mediaName}
        </Typography>
        <Box
          sx={{
            display: "flex",
            width: "1260px",
          }}
        >
          <Box
            component="img"
            alt={mediaName}
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
              Released on {releaseDate}
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
            <AppButton variant="outlined">Buy for $19.99</AppButton>
            <AppButton variant="outlined">Rent for $5.99</AppButton>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default Details;