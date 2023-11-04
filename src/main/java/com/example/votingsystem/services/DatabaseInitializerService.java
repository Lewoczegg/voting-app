package com.example.votingsystem.services;

import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DatabaseInitializerService {

    private final UserService userService;
    private final ConstituencyService constituencyService;
    private final PasswordEncoder passwordEncoder;

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

        userService.saveAll(usersToSave);
    }
}
