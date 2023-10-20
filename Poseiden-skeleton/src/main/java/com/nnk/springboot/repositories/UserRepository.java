package com.nnk.springboot.repositories;


import com.nnk.springboot.domain.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<DBUser, Integer> {

    DBUser findByUsername(String username);
}
