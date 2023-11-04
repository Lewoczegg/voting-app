package com.example.votingsystem.services;

import com.example.votingsystem.entities.Constituency;

public interface ConstituencyService {


    Constituency findById(Long id);
}
