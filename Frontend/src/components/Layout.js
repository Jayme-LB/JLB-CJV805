import React from "react";
import { Outlet } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";
import Box from "@mui/material/Box";

// This component renders the general layout of each web page
// (i.e. the navigational header, the body of an appropriate web page, and the footer).
const Layout = () => {
  return (
    <Box>
      <Header />
      <Box>
        <Outlet />
      </Box>
      <Footer />
    </Box>
  );
};

export default Layout;