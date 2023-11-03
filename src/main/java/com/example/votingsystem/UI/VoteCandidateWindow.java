package com.example.votingsystem.UI;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.entities.User;
import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.LoginService;
import com.example.votingsystem.services.VotingService;
import jexer.*;

import java.util.List;
import java.util.stream.Collectors;

public class VoteCandidateWindow {
    private TApplication app;
    private CandidateService candidateService;
    private LoginService loginService;
    private VotingService votingService;
    private User currentUser;
    private TList candidateList;
    private TWindow voteCandidateWindow;
    private TWindow votePartyWindow;

    public VoteCandidateWindow(TApplication app, CandidateService candidateService, LoginService loginService, VotingService votingService, TWindow votePartyWindow) {
        this.app = app;
        this.candidateService = candidateService;
        this.loginService = loginService;
        this.votingService = votingService;
        this.votePartyWindow = votePartyWindow;
    }

    public void show(PoliticalParty politicalParty) {
        currentUser = loginService.getCurrentUser();
        voteCandidateWindow = new TWindow(app, "Wybierz kandydata", 0, 0, 80, 24);

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

        TWindow confirmationWindow = new TWindow(app, "Potwierdzenie", 0, 0, 80, 24);

        // Display a confirmation message
        new TLabel(confirmationWindow, "Czy na pewno chcesz zagłosować na " + selectedCandidate.getName() + " " + selectedCandidate.getSurname() + "?", 5, 2);

        // "Yes" button to confirm the vote
        new TButton(confirmationWindow, "Tak", 20, 5, new TAction() {
            @Override
            public void DO() {
                votingService.vote(currentUser.getUser_id(), selectedCandidate.getCandidate_id());
                confirmationWindow.close();
                voteCandidateWindow.close();
                votePartyWindow.close();
            }
        });

        // "No" button to cancel the vote
        new TButton(confirmationWindow, "Nie", 55, 5, new TAction() {
            @Override
            public void DO() {
                confirmationWindow.close();
            }
        });
    }
}
