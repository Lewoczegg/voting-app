package com.example.votingsystem.services;

import com.example.votingsystem.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findById(Long id);
    List<User> findAll();
    List<User> findAllByVotedFalse();
    void saveAll(List<User> users);
    User save(User user);
    Optional<User> findByUsername(String username);
}
