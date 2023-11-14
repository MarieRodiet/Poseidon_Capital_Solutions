package com.nnk.springboot.service;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
        DBUser user = repository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid DBUser id " + id));
        if(user == null){
            return null;
        }
        return user;
    }

    public DBUser findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
