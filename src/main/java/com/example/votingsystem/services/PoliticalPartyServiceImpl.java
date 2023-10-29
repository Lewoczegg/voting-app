package com.example.votingsystem.services;

import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.repositories.PoliticalPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticalPartyServiceImpl implements PoliticalPartyService {

    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Override
    public List<PoliticalParty> getAllPoliticalParties() {
        return politicalPartyRepository.findAll();
    }
}
