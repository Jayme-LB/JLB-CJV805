import "../css/App.css";
import { useState, useEffect } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./Layout";
import Home from "../pages/Home";
import LogIn from "../pages/LogIn";
import SignUp from "../pages/SignUp";
import Listing from "../pages/Listing";
import Details from "../pages/Details";
import PageNotFound from "../pages/404";

// The default App component will handle the router configurations for this app,
// as well as "fetch" the data from the "API".
function App() {
  const JSON_SERVER_URL = "http://localhost"; // Backend server URL.
  const JSON_SERVER_PORT = 8080; // Backend server port.
  const [movies, setMovies] = useState([]); // Holds the "API" movie data.
  const [tvShows, setTVShows] = useState([]); // Holds the "API" TV show data.

  useEffect(() => {
    /*
      This function fetches all media data the mock API (json-server).
      Remember to start up json-server first by running this command
      in the terminal (assuming you are in the project root directory):
      npx json-server -p 8080 .\public\data\db.json
    */
    const fetchData = (mediaType) => {
      return new Promise((resolve, reject) =>
        fetch(`${JSON_SERVER_URL}:${JSON_SERVER_PORT}/${mediaType}`)
          .then(response => response.json())
          .then(mediaData => resolve(mediaData))
          .catch(err => reject(err))
      );
    };

    // Call the above function to fetch only the movie data.
    fetchData("movies")
      .then(mediaData => setMovies(mediaData))
      .catch(err => console.error("There was a problem fetching the movie data: ", err)
      );

    // Call the function again to fetch only the TV show data.
    fetchData("tv")
      .then(mediaData => setTVShows(mediaData))
      .catch(err => console.error("There was a problem fetching the TV show data: ", err))
  }, []);
 
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home movies={movies} tvShows={tvShows} />} />
          <Route path="LogIn" element={<LogIn />} />
          <Route path="SignUp" element={<SignUp />} />
          <Route path="Listing" element={<Listing movies={movies} tvShows={tvShows} />} />
          <Route path="Details/:mediaID" element={<Details movies={movies} tvShows={tvShows} />} />
          <Route path="*" element={<PageNotFound />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;