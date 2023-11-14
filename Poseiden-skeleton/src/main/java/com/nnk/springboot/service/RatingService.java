package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repository;

    public List<Rating> findAll() {
        return repository.findAll();
    }

    public void save(Rating rating){
        repository.save(rating);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public Rating findById(Integer id) {
        Rating rating = repository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid Rating id " + id));
        if(rating == null){
            return null;
        }
        return rating;
    }
}
