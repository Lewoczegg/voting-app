package com.example.votingsystem.services;

import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.repositories.PoliticalPartyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PoliticalPartyServiceImpl implements PoliticalPartyService {
    private final PoliticalPartyRepository politicalPartyRepository;

    @Override
    public List<PoliticalParty> getAllPoliticalParties() {
        return politicalPartyRepository.findAll();
    }
}
