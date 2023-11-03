package com.example.votingsystem.UI;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.entities.User;
import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.LoginService;
import jexer.*;

import java.util.List;
import java.util.stream.Collectors;

public class VoteCandidateWindow {
    private TApplication app;
    private CandidateService candidateService;
    private LoginService loginService;
    private User currentUser;
    private TList candidateList;

    public VoteCandidateWindow(TApplication app, CandidateService candidateService, LoginService loginService) {
        this.app = app;
        this.candidateService = candidateService;
        this.loginService = loginService;
    }

    public void show(PoliticalParty politicalParty) {
        currentUser = loginService.getCurrentUser();
        TWindow voteCandidateWindow = new TWindow(app, "Wybierz kandydata", 0, 0, 80, 24);

        List<Candidate> candidates = candidateService.findByPoliticalPartyAndConstituency(politicalParty, currentUser.getConstituency());
        List<String> candidateNames = candidates.stream().map(c -> c.getName() + " " + c.getSurname()).collect(Collectors.toList());

        new TLabel(voteCandidateWindow, "Aby zagłosować wsciśnij enter przy podświtlonym kandydacie.", 2, 0);

        candidateList = new TList(voteCandidateWindow, candidateNames, 2, 2, 75, 17, new TAction() {
            @Override
            public void DO() {
                vote(candidates);
            }
        });

        new TButton(voteCandidateWindow, "Cofnij", 66, 20, new TAction() {
            @Override
            public void DO() {
                voteCandidateWindow.close();
            }
        });

        new TLabel(voteCandidateWindow, "Do poruszania się pomiędzy elementami używaj klawisza TAB.", 2, 20);
    }

    private void vote(List<Candidate> candidates) {
        Candidate selectedCandidate = candidates.get(candidateList.getSelectedIndex());
        System.out.println("Voted for: " + selectedCandidate.getName());
    }
}
