package com.example.votingsystem.repositories;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByPoliticalParty(PoliticalParty politicalParty);
    List<Candidate> findByConstituency(Constituency constituency);

    @Query("SELECT c FROM Candidate c WHERE c.politicalParty = ?1 ORDER BY c.constituency.constituency_id ASC")
    List<Candidate> findByPoliticalPartyOrderByConstituencyIdAsc(PoliticalParty politicalParty);

    List<Candidate> findByPoliticalPartyAndConstituency(PoliticalParty politicalParty, Constituency constituency);
}
