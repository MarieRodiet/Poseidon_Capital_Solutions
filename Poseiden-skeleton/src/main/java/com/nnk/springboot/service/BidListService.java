package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class BidListService {

    @Autowired
    private BidListRepository repository;

    public List<BidList> findAll() {
        return repository.findAll();
    }

    public void save(BidList bid){
        repository.save(bid);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public BidList findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
