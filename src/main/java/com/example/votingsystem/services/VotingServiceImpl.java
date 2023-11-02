package com.example.votingsystem.services;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.User;
import com.example.votingsystem.repositories.CandidateRepository;
import com.example.votingsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VotingServiceImpl implements VotingService {

    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public void vote(Long userId, Long candidateId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));

        if (user.isVoted()) {
            throw new RuntimeException("User has already voted");
        }

        candidate.setVotes(candidate.getVotes() + 1);
        user.setVoted(true);

        userRepository.save(user);
        candidateRepository.save(candidate);
    }
}
