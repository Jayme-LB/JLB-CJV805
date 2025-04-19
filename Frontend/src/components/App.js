import "../css/App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./Layout";
import Home from "../pages/Home";
import LogIn from "../pages/LogIn";
import SignUp from "../pages/SignUp";
import Dashboard from "../pages/Dashboard";
import Listing from "../pages/Listing";
import Search from "../pages/Search";
import Details from "../pages/Details";
import PageNotFound from "../pages/404";

// The default App component will handle the router configurations for this app,
// as well as "fetch" the data from the "API".
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="LogIn" element={<LogIn />} />
          <Route path="SignUp" element={<SignUp />} />
          <Route path="Dashboard" element={<Dashboard />} />
          <Route path="User" element={<Dashboard />} />
          <Route path="Listing" element={<Listing />} />
          <Route path="Search" element={<Search />} />
          <Route path="Details/:mediaID" element={<Details />} />
          <Route path="*" element={<PageNotFound />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;