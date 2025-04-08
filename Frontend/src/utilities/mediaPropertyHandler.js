// This function will return the TITLE of a movie or NAME of a TV Show,
// depending on the media type in the given object.
const getTitleOrName = (media) => {
  let mediaType = media.media_type; // Gets media type from the object.
  let mediaProperty; // Returns the appropriate property from the object.

  if (mediaType === "movie")
    mediaProperty = media.title;
  else if (mediaType === "tv")
    mediaProperty = media.name;

  return mediaProperty;
};

// This function will return the RELEASE DATE of a movie, or FIRST AIR DATE of
// a TV show, depending on the media type in the given object.
const getReleaseOrAirDate = (media) => {
  let mediaType = media.media_type; // Gets media type from the object.
  let mediaProperty; // Returns the appropriate property from the object.

  if (mediaType === "movie")
    mediaProperty = media.release_date;
  else if (mediaType === "tv")
    mediaProperty = media.first_air_date;

  return mediaProperty;
};

export {
  getTitleOrName,
  getReleaseOrAirDate
};