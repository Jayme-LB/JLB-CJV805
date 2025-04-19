import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Swiper, SwiperSlide } from "swiper/react";
import { EffectFade, Autoplay } from "swiper/modules";
import "swiper/css";
import "swiper/css/effect-fade";
import "swiper/css/autoplay";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Featured from "../components/Featured";
import ContentSection from "../images/ContentSection.png";

// This component renders the home page that will display featured movies,
// TV shows, and actor information.
const Home = () => {
  const [heroMedia, setHeroMedia] = useState([]); // Uses the backdrop images in the API for the hero slider.

  useEffect(() => {
    const fetchData = () => {
      return new Promise((resolve, reject) =>
        fetch("http://localhost:8080/Listing")
          .then(response => response.json())
          .then(mediaData => resolve(mediaData))
          .catch(err => reject(err))
      );
    };

    // Call the above function with the proper endpoint to fetch the featured media.
    fetchData()
      .then(mediaData => setHeroMedia(mediaData.body))
      .catch(err => 
        console.error("There was a problem fetching the featured media data: ", err));
    }, []);
  
  return (
    <Box
      sx={{
        maxWidth: "1280px",
        margin: "auto",
        marginBottom: "1em"
      }}
    >
      <Swiper
        modules={[EffectFade, Autoplay]}
        effect="fade"
        crossfade="true"
        autoplay={{
          delay: 8000,
          disableOnInteraction: false
        }}
        loop
        slidesPerView={1}
      >
        {heroMedia.map((media, index) => (
          <SwiperSlide key={index}>
            <Box
              component="img"
              src={`https://image.tmdb.org/t/p/w1280/${media.backdrop_path}`}
              alt={`Slide #${index + 1}: ${media.name}`}
              sx={{
                width: "100%",
                height: "450px",
                objectFit: "cover"
              }}
            />
            <Box
              sx={{
                position: "absolute",
                top: "30%",
                left: "50%",
                transform: "translate(-50%, -30%)"
              }}
            >
              <Typography
                variant="h4"
                sx={{
                  color: "white",
                  fontWeight: "bold",
                  textShadow: "1px 1px 10px black, 2px 2px 2px orangered"
                }}
              >
                Get the latest and hottest movies & TV shows at low prices! All
                you need to do is create an account and then ordering your
                faves is just a few clicks away. Buy 'em or rent 'em, and then
                just...
              </Typography>
              <Typography
                variant="h2"
                fontWeight="bold"
                sx={{
                  color: "white",
                  fontWeight: "bold",
                  textShadow: "1px 1px 1px black, 4px 4px 3px orangered"
                }}
              >
                Watch 'Em™!
              </Typography>
            </Box>
          </SwiperSlide>
        ))}
      </Swiper>
      <Featured featuredMediaType={"Movies"}/>
      <Featured featuredMediaType={"TVShows"}/>
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          paddingTop: "25px"
        }}
      >
        <Link to="https://www.themoviedb.org/">
          <Box
            component="img"
            alt="Content Section"
            src={ContentSection}
          />
        </Link>
      </Box>
    </Box>
  );
};

export default Home;