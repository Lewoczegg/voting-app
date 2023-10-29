package com.example.votingsystem.repositories;

import com.example.votingsystem.entities.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long> {
}
