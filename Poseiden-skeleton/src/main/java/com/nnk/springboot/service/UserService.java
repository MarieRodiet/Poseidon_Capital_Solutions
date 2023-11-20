package com.nnk.springboot.service;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<DBUser> findAll() {
        return repository.findAll();
    }

    public void save(DBUser user){
        repository.save(user);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public DBUser findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public DBUser findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public DBUser findCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return findByUsername(username);
    }
}
