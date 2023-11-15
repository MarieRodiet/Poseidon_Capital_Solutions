package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository repository;


    public List<CurvePoint> findAll() {
        return repository.findAll();
    }

    public void save(CurvePoint curvePoint){
        repository.save(curvePoint);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public CurvePoint findById(Integer id) {
        return  repository.findById(id).orElse(null);
    }
}
