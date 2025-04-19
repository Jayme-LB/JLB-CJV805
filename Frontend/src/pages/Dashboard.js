import React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

// This component renders the Dashboard for a logged in user.
const Dashboard = () => {
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
        User's Dashboard
      </Typography>
    </Box>
  )
}

export default Dashboard;