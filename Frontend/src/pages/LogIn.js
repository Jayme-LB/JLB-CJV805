import React from "react";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import AppButton from "../components/AppButton";

// This component renders the Log In page.
const LogIn = () => {
  return (
    <form className="form">
      <Typography
        variant="h4"
        fontWeight="bold"
        marginBottom={1}
      >
        Log In
      </Typography>
      <TextField
        label="Username"
        name="username"
        fullWidth
        required
        sx={{
          backgroundColor: "white",
          marginBottom: "1em"
        }}
      />
      <TextField
        label="Password"
        name="password"
        fullWidth
        required
        sx={{
          backgroundColor: "white",
          marginBottom: "1em"
        }}
      />
      <AppButton variant="outlined">
        Log In
      </AppButton>
      <AppButton variant="outlined">
        Forgot Password
      </AppButton>
    </form>
  )
}

export default LogIn;