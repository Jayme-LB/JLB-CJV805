import React from "react";
import { Link as RouterLink } from "react-router-dom";
import Link from '@mui/material/Link';
import Typography from "@mui/material/Typography";
import AppButton from "./AppButton";

// This component renders the website's header that will appear on every page.
const Header = () => {
  return (
    <header>
      <Link href="/" underline="none" color="orange">
        <Typography
          variant="h4"
          fontWeight="bold"
          alignContent="center"
        >
          👀 Watch 'Em
        </Typography>
      </Link>
      <nav>
        <AppButton
          variant="outlined"
          component={RouterLink}
          to="/Listing"
        >
          Browse All
        </AppButton>
        <AppButton
          variant="outlined"
          component={RouterLink}
          to="/LogIn"
        >
          Log In
        </AppButton>
        <AppButton
          variant="outlined"
          component={RouterLink}
          to="/SignUp"
        >
          Sign Up
        </AppButton>
      </nav>
    </header>
  );
};

export default Header;