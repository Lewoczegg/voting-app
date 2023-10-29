package com.example.votingsystem.repositories;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByPoliticalParty(PoliticalParty politicalParty);
    List<Candidate> findByConstituency(Constituency constituency);
}
