import { getTitleOrName } from "../utilities/mediaPropertyHandler";
import React from "react";
import { Link } from "react-router-dom";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid2";

// This component renders the featured section of either movies or TV shows,
// depending on the given prop.
const Featured = ({ mediaData, featuredTitle }) => {
  // Use the first six media entries in the API to use for the "Featured" section.
  const featuredMedia = mediaData.slice(0, 6);

  return (
    <Box>
      <Typography
        variant="h4"
        fontWeight="bold"
        marginBottom={1}
        sx={{ textShadow: "orangered 2px 1px 5px" }}
      >
        {featuredTitle}
      </Typography>
      <Grid container columns={6} spacing={4}>
        {featuredMedia.map((media, index) => (
          <Grid key={index} sx={{ width: 185 }} size={1}>
            <Link to={`/Details/${media.id}`} className="media-link">
              <Box
                component="img"
                alt={getTitleOrName(media)}
                src={`https://image.tmdb.org/t/p/w185/${media.poster_path}`}
              />
              {getTitleOrName(media)}
            </Link>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
};

export default Featured;