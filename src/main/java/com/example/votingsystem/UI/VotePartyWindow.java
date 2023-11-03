package com.example.votingsystem.UI;

import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.PoliticalPartyService;
import jexer.TAction;
import jexer.TApplication;
import jexer.TButton;
import jexer.TWindow;

import java.util.List;

public class VotePartyWindow {
    private final TApplication app;
    private final PoliticalPartyService politicalPartyService;
    private final CandidateService candidateService;


    public VotePartyWindow(TApplication app, PoliticalPartyService politicalPartyService, CandidateService candidateService) {
        this.app = app;
        this.politicalPartyService = politicalPartyService;
        this.candidateService = candidateService;
    }

    public void show() {
        TWindow votePartyWindow = app.addWindow("Wybierz parite", 0, 0, 80, 24);
        List<PoliticalParty> parties = politicalPartyService.getAllPoliticalParties();

        int buttonYPosition = 0;
        int buttonHeight = 2;

        for (PoliticalParty party : parties) {
            new TButton(votePartyWindow, party.getName(), 0, buttonYPosition, new TAction() {
                @Override
                public void DO() {
                    System.out.println(party.getName());
                }
            });
            buttonYPosition += buttonHeight;
        }

        new TButton(votePartyWindow, "Cofnij", 20, buttonYPosition, new TAction() {
            @Override
            public void DO() {
                votePartyWindow.close();
            }
        });
    }
}
