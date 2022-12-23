package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingsById(String ratingId);

    List<Rating> getRatingsByHotelId(String hotelId);
}
