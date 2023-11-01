package com.example.votingsystem.UI;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.PoliticalPartyService;
import jexer.*;

import java.util.List;
import java.util.Map;


public class CandidatesWindow {
    private final TApplication app;
    private final PoliticalPartyService politicalPartyService;
    private CandidateService candidateService;

    public CandidatesWindow(TApplication app, PoliticalPartyService politicalPartyService, CandidateService candidateService) {
        this.app = app;
        this.politicalPartyService = politicalPartyService;
        this.candidateService = candidateService;
    }

    public void showCandidatesForParty(PoliticalParty party) {
        TWindow candidatesWindow = app.addWindow("Kandydaci dla " + party.getName(), 0, 0, 80, 24);

        Map<Constituency, List<Candidate>> groupedCandidates = candidateService.getCandidatesGroupedByConstituency(party);

        StringBuilder displayText = new StringBuilder();
        displayText.append(politicalPartyService.getASCIIArt(party.getPolitical_party_id())).append("\n");
        groupedCandidates.forEach((constituency, candidates) -> {
            displayText.append("OKRĘG WYBORCZY NR " + constituency.getConstituency_id() + ":\n");
            for (Candidate candidate : candidates) {
                displayText.append("**  " + candidate.getName() + " " + candidate.getSurname() + "\n");
            }
            displayText.append("\n");
        });

        new TText(candidatesWindow, displayText.toString(), 2, 0, 75, 19);

        new TButton(candidatesWindow, "Cofnij", 66, 20, new TAction() {
            @Override
            public void DO() {
                candidatesWindow.close();
            }
        });

        new TLabel(candidatesWindow, "Do poruszania się pomiędzy elementami używaj klawisza TAB.", 5, 20);
    }
}
