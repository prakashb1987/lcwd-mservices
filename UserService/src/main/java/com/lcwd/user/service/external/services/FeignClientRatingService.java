package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entity.Rating;
import feign.Body;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface FeignClientRatingService {

    @PostMapping("/ratings")
    Rating createRating(Rating rating);

    @PutMapping("/ratings/{ratingId]")
    Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

    @DeleteMapping("/ratings/ratingId")
    Rating deleteRating(@PathVariable("ratingId") String ratingId);
}
