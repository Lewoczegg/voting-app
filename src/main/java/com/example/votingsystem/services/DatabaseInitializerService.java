package com.example.votingsystem.services;

import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.User;
import com.example.votingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseInitializerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConstituencyService constituencyService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUsers() {
        List<User> usersToSave = new ArrayList<>();

        for (Long constituency = 1L; constituency <= 41; constituency++) {
            for (int userNum = 1; userNum <= 200; userNum++) {
                User user = new User();
                user.setUsername("user" + constituency + "_" + userNum);
                System.out.println(user.getUsername());

                // Hashing the password before saving
                user.setPassword(passwordEncoder.encode("password" + constituency + "_" + userNum));
                Constituency userConstituency = constituencyService.findById(constituency);
                user.setVoted(false);
                user.setConstituency(userConstituency);
                usersToSave.add(user);
            }
        }

        userRepository.saveAll(usersToSave);
    }
}
