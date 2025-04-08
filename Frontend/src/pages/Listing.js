import { getTitleOrName } from "../utilities/mediaPropertyHandler";
import React from "react";
import { Link } from "react-router-dom";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid2";

const Listing = ({ movies, tvShows }) => {
  // Because both JSON object arrays have near identical structures, we can simply
  // concatenate them before mapping and rendering them to a Grid.
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
        All Movies & TV Shows
      </Typography>
      <Grid
        container
        columns={6}
        spacing={2}
      >
        {movies.concat(tvShows).map((media, index) => (
          <Grid key={index} size={1} sx={{ width: 185 }}>
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

export default Listing;