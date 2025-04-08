import React from "react";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import AppButton from "../components/AppButton";

// This component renders the Registration page.
const SignUp = () => {
  return (
    <form className="form">
      <Typography
        variant="h4"
        fontWeight="bold"
        marginBottom={1}
      >
        Sign Up
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
          backgroundColor: "white"
        }}
      />
      <TextField
        label="Confirm Password"
        name="confirmPassword"
        fullWidth
        required
        sx={{
          backgroundColor: "white",
          marginBottom: "1em"
        }}
      />
      <TextField
        label="Email"
        name="email"
        fullWidth
        required
        sx={{
          backgroundColor: "white"
        }}
      />
      <TextField
        label="Confirm Email"
        name="confirmEmail"
        fullWidth
        required
        sx={{
          backgroundColor: "white",
          marginBottom: "1em"
        }}
      />
      <AppButton variant="outlined">
        Sign Me Up!
      </AppButton>
    </form>
  )
}

export default SignUp;