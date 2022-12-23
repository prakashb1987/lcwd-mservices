package com.lcwd.user.service.Services;

import com.lcwd.user.service.entity.Hotel;
import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.FeignHotelClient;
import com.lcwd.user.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UserServeImpl implements UserService{

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    private FeignHotelClient feignHotelClient;

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // Get Rating and set to User.
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not found" + userId));
        Rating[] ratingArray =  restTemplate.getForObject("http://RATING-SERVICE/ratings", Rating[].class);
        assert ratingArray != null;
        List<Rating> ratingsList =  Arrays.stream(ratingArray).toList();
        List<Rating> ratingList = ratingsList.stream().peek(rating -> {
            System.out.printf("Hotel Id from rating is: "+ rating.getHotelId());
            Hotel hotelObjFromRatingId = feignHotelClient.getHotel(rating.getHotelId());
            //Hotel hotelObjFromRatingId = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            rating.setHotel(hotelObjFromRatingId);
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
