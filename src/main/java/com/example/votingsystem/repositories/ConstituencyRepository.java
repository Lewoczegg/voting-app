package com.example.votingsystem.repositories;

import com.example.votingsystem.entities.Constituency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstituencyRepository extends JpaRepository<Constituency, Long> {
}
