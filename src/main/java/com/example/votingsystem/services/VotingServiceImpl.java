package com.example.votingsystem.services;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.User;
import com.example.votingsystem.repositories.CandidateRepository;
import com.example.votingsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VotingServiceImpl implements VotingService {

    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final ConstituencyService constituencyService;
    private final CandidateService candidateService;

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

    @Override
    public Map<Constituency, List<Candidate>> getVotingResults() {
        List<Constituency> allConstituencies = constituencyService.findAll();
        allConstituencies.sort(Comparator.comparing(Constituency::getConstituency_id));

        Map<Constituency, List<Candidate>> resultsByConstituency = new HashMap<>();

        for (Constituency constituency : allConstituencies) {
            List<Candidate> candidatesInConstituency = candidateService.findByConstituency(constituency);

            List<Candidate> winningCandidates = candidatesInConstituency.stream()
                    .sorted((c1, c2) -> Long.compare(c2.getVotes(), c1.getVotes())) // Sort by votes in descending order
                    .limit(constituency.getNumberOfSeats())
                    .collect(Collectors.toList());

            resultsByConstituency.put(constituency, winningCandidates);
        }
        return resultsByConstituency;
    }
}
