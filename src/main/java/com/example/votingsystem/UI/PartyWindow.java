package com.example.votingsystem.UI;

import com.example.votingsystem.services.CandidateService;
import jexer.TAction;
import jexer.TApplication;
import jexer.TButton;
import jexer.TWindow;
import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.services.PoliticalPartyService;

import java.util.List;

public class PartyWindow {

    private final TApplication app;
    private final PoliticalPartyService politicalPartyService;
    private final CandidateService candidateService;
    private CandidatesWindow candidatesWindow;

    public PartyWindow(TApplication app, PoliticalPartyService politicalPartyService, CandidateService candidateService) {
        this.app = app;
        this.politicalPartyService = politicalPartyService;
        this.candidateService = candidateService;
        this.candidatesWindow = new CandidatesWindow(app, politicalPartyService, candidateService);
    }

    public void show() {
        TWindow partyWindow = app.addWindow("Partie", 0, 0, 80, 24);
        List<PoliticalParty> parties = politicalPartyService.getAllPoliticalParties();

        int buttonYPosition = 0;
        int buttonHeight = 2;

        for (PoliticalParty party : parties) {
            new TButton(partyWindow, party.getName(), 0, buttonYPosition, new TAction() {
                @Override
                public void DO() {
                    candidatesWindow.showCandidatesForParty(party);
                }
            });
            buttonYPosition += buttonHeight;
        }

        new TButton(partyWindow, "Cofnij", 20, buttonYPosition, new TAction() {
            @Override
            public void DO() {
                partyWindow.close();
            }
        });
    }
}
