package com.example.votingsystem.services;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class DatabaseInitializerService {

    private final UserService userService;
    private final ConstituencyService constituencyService;
    private final PasswordEncoder passwordEncoder;
    private final VotingService votingService;
    private final CandidateService candidateService;

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

    public void autoVoteForUnvotedUsers() {
        List<User> unvotedUsers = userService.findAllByVotedFalse();

        Random random = new Random();

        for (User user : unvotedUsers) {
            List<Candidate> candidatesInConstituency = candidateService.findByConstituency(user.getConstituency());

            if (!candidatesInConstituency.isEmpty()) {
                int randomIndex = random.nextInt(candidatesInConstituency.size());
                Candidate randomCandidate = candidatesInConstituency.get(randomIndex);
                System.out.println(randomCandidate.getCandidate_id());
                votingService.vote(user.getUser_id(), randomCandidate.getCandidate_id());
            }
        }
    }
}
