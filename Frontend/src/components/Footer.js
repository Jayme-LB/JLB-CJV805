import React from "react";
import Typography from "@mui/material/Typography";

// This component renders the website's footer that will appear on every page.
const Footer = () => {
  return (
    <footer>
      <Typography
        variant="body2"
        align="center"
        sx={{ marginTop: "5px", marginBottom: "2px" }}
      >
        © 2025 Watch 'Em Media Co. All rights reserved.<br />
      </Typography>
    </footer>
  );
};

export default Footer;