package com.example.votingsystem.services;

        import com.example.votingsystem.entities.Candidate;
        import com.example.votingsystem.entities.Constituency;
        import com.example.votingsystem.entities.PoliticalParty;
        import com.example.votingsystem.repositories.CandidateRepository;
        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Service;

        import java.util.LinkedHashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public List<Candidate> findByPoliticalParty(PoliticalParty politicalParty) {
        return candidateRepository.findByPoliticalParty(politicalParty);
    }

    @Override
    public List<Candidate> findByConstituency(Constituency constituency) {
        return candidateRepository.findByConstituency(constituency);
    }

    @Override
    public Map<Constituency, List<Candidate>> getCandidatesGroupedByConstituency(PoliticalParty politicalParty) {
        List<Candidate> candidates = candidateRepository.findByPoliticalPartyOrderByConstituencyIdAsc(politicalParty);
        return candidates.stream().collect(Collectors.groupingBy(Candidate::getConstituency, LinkedHashMap::new, Collectors.toList()));
    }

    @Override
    public List<Candidate> findByPoliticalPartyAndConstituency(PoliticalParty politicalParty, Constituency constituency) {
        return candidateRepository.findByPoliticalPartyAndConstituency(politicalParty, constituency);
    }
}
