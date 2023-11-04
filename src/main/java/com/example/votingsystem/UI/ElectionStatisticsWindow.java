package com.example.votingsystem.UI;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.services.VotingService;
import jexer.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ElectionStatisticsWindow {
    private TApplication app;
    private VotingService votingService;

    public ElectionStatisticsWindow(TApplication app, VotingService votingService) {
        this.app = app;
        this.votingService = votingService;
    }
    public void show() {
        TWindow statisticsWindow = new TWindow(app, "Statystyki wyborów", 0, 0, 80, 24);

        Map<Constituency, List<Candidate>> deputiesByConstituency = votingService.getVotingResults();

        // Sort the constituencies by their ID
        Map<Constituency, List<Candidate>> sortedDeputiesByConstituency = deputiesByConstituency.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Constituency::getConstituency_id)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        StringBuilder content = new StringBuilder();

        for (Map.Entry<Constituency, List<Candidate>> entry : sortedDeputiesByConstituency.entrySet()) {
            Long constituencyId = entry.getKey().getConstituency_id();
            content.append("OKRĘG WYBRCZY NR: ").append(constituencyId).append("\n");

            for (Candidate deputy : entry.getValue()) {
                content.append("** ")
                        .append(deputy.getName())
                        .append(" ")
                        .append(deputy.getSurname())
                        .append(" - ")
                        .append(deputy.getPoliticalParty().getName())
                        .append("\n");
            }
            content.append("\n"); // Add an extra line for separation
        }

        TText statisticsText = new TText(statisticsWindow, content.toString(), 2, 1, 76, 19);

        TButton closeButton = new TButton(statisticsWindow, "Cofnij", 65, 20, new TAction() {
            @Override
            public void DO() {
                statisticsWindow.close();
            }
        });

        new TLabel(statisticsWindow, "Do poruszania się pomiędzy elementami używaj klawisza TAB.", 5, 20);
    }
}
