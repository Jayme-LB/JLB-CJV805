import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import AppButton from "../components/AppButton";

// This component renders the Registration page.
const SignUp = () => {
  const navigate = useNavigate(); // For redirecting user after they register.
  // Hold the form field inputs using a state.
  const [formInfo, setFormInfo] = useState({
    first_name: "",
    last_name: "",
    email: "",
    password: ""
  });

  // This function handles form submission.
  const submitFormHandler = async (event) => {
    // Prevent the default action for a form submission (also stop the page from reloading).
    event.preventDefault();

    await fetch("http://localhost:8080/Users", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(formInfo)
    })
    .then(response => {
      if (!response.ok) {
        alert("That email is already in use. Please use another.");
        throw new Error(`HTTP request error! Response status code: ${response.status}`);
      }

      response.json();
    })
    .then(userData => {
      alert("User registration is successful!");
      navigate("/LogIn");
    })
    .catch(err => console.log(err));
  }

  return (
    <form className="form" onSubmit={submitFormHandler}>
      <Typography variant="h4" fontWeight="bold" marginBottom={1}>
        Sign Up
      </Typography>
      <TextField
        label="First Name"
        name="firstName"
        fullWidth
        required
        sx={{
          backgroundColor: "white",
          marginBottom: "1em"
        }}
        value={formInfo.first_name}
        onChange={(event) => {
          setFormInfo({...formInfo, first_name: event.target.value})
        }}
      />
      <TextField
        label="Last Name"
        name="lastName"
        fullWidth
        required
        sx={{
          backgroundColor: "white",
          marginBottom: "1em"
        }}
        value={formInfo.last_name}
        onChange={(event) => {
          setFormInfo({...formInfo, last_name: event.target.value})
        }}
      />
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
          marginBottom: "1em"
        }}
        value={formInfo.password}
        onChange={(event) => {
          setFormInfo({...formInfo, password: event.target.value})
        }}
      />
      <AppButton variant="outlined" type="submit">Sign Me Up!</AppButton>
    </form>
  );
};

export default SignUp;
