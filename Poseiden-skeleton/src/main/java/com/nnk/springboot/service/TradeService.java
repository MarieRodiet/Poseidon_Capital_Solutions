package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeRepository repository;


    public List<Trade> findAll() {
        return repository.findAll();
    }

    public void save(Trade trade){
        repository.save(trade);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public Trade findById(Integer id) {
        Trade trade = repository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid Trade id " + id));
        if(trade == null){
            return null;
        }
        return trade;
    }
}
