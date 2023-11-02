package com.example.votingsystem.services;

public interface VotingService {
    void vote(Long userId, Long candidateId);
}
