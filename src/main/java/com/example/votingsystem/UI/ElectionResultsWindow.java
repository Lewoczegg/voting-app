package com.example.votingsystem.UI;

import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.services.VotingService;
import jexer.*;

import java.util.Map;

public class ElectionResultsWindow {
    private TApplication app;
    private VotingService votingService;
    private ElectionStatisticsWindow electionStatisticsWindow;

    public ElectionResultsWindow(TApplication app, VotingService votingService) {
        this.app = app;
        this.votingService = votingService;
        this.electionStatisticsWindow = new ElectionStatisticsWindow(app, votingService);
    }

    public void show() {
        TWindow partyResultsWindow = new TWindow(app, "Wyniki wyborów", 0, 0, 80, 24);

        Map<PoliticalParty, Long> deputiesCountByParty = votingService.getDeputiesCountByParty();

        int yPosition = 2;
        for (Map.Entry<PoliticalParty, Long> entry : deputiesCountByParty.entrySet()) {
            String partyName = entry.getKey().getName();
            Long count = entry.getValue();

            new TLabel(partyResultsWindow, partyName + ": " + count + " posłow", 10, yPosition);
            yPosition += 2;
        }

        TButton closeButton = new TButton(partyResultsWindow, "Cofnij", 10, yPosition, new TAction() {
            @Override
            public void DO() {
                partyResultsWindow.close();
            }
        });

        TButton advancedStatisticsButton = new TButton(partyResultsWindow, "Statystyki", 10, yPosition + 2, new TAction() {
            @Override
            public void DO() {
                electionStatisticsWindow.show();
            }
        });
    }
}
