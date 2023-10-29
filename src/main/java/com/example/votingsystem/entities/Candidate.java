package com.example.votingsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidate_id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private Long votes = 0L;

    @ManyToOne
    @JoinColumn(name = "political_party_id")
    private PoliticalParty politicalParty;

    @ManyToOne
    @JoinColumn(name = "constituency_id")
    private Constituency constituency;
}
