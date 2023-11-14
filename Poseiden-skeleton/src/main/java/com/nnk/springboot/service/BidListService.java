package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        BidList bidList = repository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid bidList id " + id));
        if(bidList == null){
            return null;
        }
        return bidList;
    }
}
