package com.example.votingsystem.services;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;

import java.util.List;
import java.util.Map;

public interface VotingService {
    void vote(Long userId, Long candidateId);
    Map<Constituency, List<Candidate>> getVotingResults();
}
