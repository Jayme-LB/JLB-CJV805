import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import AppButton from "../components/AppButton";

// This component renders the Log In page.
const LogIn = () => {
  const navigate = useNavigate(); // For redirecting user after they log in.
  // Hold the form field inputs using a state.
  const [formInfo, setFormInfo] = useState({
    email: "",
    password: ""
  });

  // This function handles form submission.
  const submitFormHandler = async (event) => {
    // Prevent the default action for a form submission (also stop the page from reloading).
    event.preventDefault();

    await fetch("http://localhost:8080/Users/LogIn", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(formInfo)
    })
    .then(response => {
      if (!response.ok) {
        alert("Invalid credentials. Please try again.")
        throw new Error(`HTTP request error! Response status code: ${response.status}`);
      }
      
      response.json();
    })
    .then(userData => {
      navigate(`/Dashboard`);
    })
    .catch(err => console.log(err));
  }
  
  return (
    <form className="form" onSubmit={submitFormHandler}>
      <Typography variant="h4" fontWeight="bold" marginBottom={1}>
        Log In
      </Typography>
      <TextField
        label="Email"
        name="email"
        fullWidth
        required
        sx={{
          backgroundColor: "white",
          marginBottom: "1em"
        }}
        value={formInfo.email}
        onChange={(event) => {
          setFormInfo({...formInfo, email: event.target.value})
        }}
      />
      <TextField
        label="Password"
        name="password"
        type="password"
        fullWidth
        required
        sx={{
          backgroundColor: "white",
          borderColor: "black",
          marginBottom: "1em"
        }}
        value={formInfo.password}
        onChange={(event) => {
          setFormInfo({...formInfo, password: event.target.value})
        }}
      />
      <AppButton variant="outlined" type="submit">Log In</AppButton>
      <AppButton variant="outlined">Forgot Password</AppButton>
    </form>
  );
};

export default LogIn;
