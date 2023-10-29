package com.example.votingsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Constituency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long constituency_id;

    private int numberOfSeats;

    @OneToMany(mappedBy = "constituency")
    private Set<User> users;

    @OneToMany(mappedBy = "constituency")
    private Set<Candidate> candidates;
}
