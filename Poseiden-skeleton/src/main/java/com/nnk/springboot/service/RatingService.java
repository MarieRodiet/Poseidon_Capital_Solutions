package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
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
        return repository.findById(id).orElse(null);
    }
}
