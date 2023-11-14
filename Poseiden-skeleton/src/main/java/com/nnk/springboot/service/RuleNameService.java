package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository repository;


    public List<RuleName> findAll() {
        return repository.findAll();
    }

    public void save(RuleName ruleName){
        repository.save(ruleName);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public RuleName findById(Integer id) {
        RuleName ruleName = repository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid Rulename id " + id));
        if(ruleName == null){
            return null;
        }
        return ruleName;
    }
}
