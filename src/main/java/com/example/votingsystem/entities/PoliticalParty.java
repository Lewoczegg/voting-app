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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoliticalParty that)) return false;

        if (getPolitical_party_id() != null ? !getPolitical_party_id().equals(that.getPolitical_party_id()) : that.getPolitical_party_id() != null)
            return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getPolitical_party_id() != null ? getPolitical_party_id().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
