package com.example.votingsystem.services;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.PoliticalParty;

import java.util.List;

public interface CandidateService {
    List<Candidate> findByPoliticalParty(PoliticalParty politicalParty);
    List<Candidate> findByConstituency(Constituency constituency);
}
