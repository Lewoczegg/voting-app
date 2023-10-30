package com.example.votingsystem.services;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CandidateServiceImpl implements CandidateService {
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> findByPoliticalParty(PoliticalParty politicalParty) {
        return candidateRepository.findByPoliticalParty(politicalParty);
    }

    @Override
    public List<Candidate> findByConstituency(Constituency constituency) {
        return candidateRepository.findByConstituency(constituency);
    }
}
