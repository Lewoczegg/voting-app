package com.example.votingsystem.services;

import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.repositories.ConstituencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConstituencyServiceImpl implements ConstituencyService {
    private final ConstituencyRepository constituencyRepository;

    @Override
    public Constituency findById(Long id) {
        Optional<Constituency> constituencyOptional = constituencyRepository.findById(id);
        if (constituencyOptional.isPresent()) {
            return constituencyOptional.get();
        } else {
            throw new RuntimeException("Constituency not found for ID: " + id);
        }
    }
}
