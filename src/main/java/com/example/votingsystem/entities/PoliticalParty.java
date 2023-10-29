package com.example.votingsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class PoliticalParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long political_party_id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "politicalParty")
    private Set<Candidate> candidates;
}
