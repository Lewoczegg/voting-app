package com.example.votingsystem.services;

import com.example.votingsystem.entities.Constituency;

import java.util.List;

public interface ConstituencyService {


    Constituency findById(Long id);
    List<Constituency> findAll();
}
