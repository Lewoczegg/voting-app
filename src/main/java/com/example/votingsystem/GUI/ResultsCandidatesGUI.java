package com.example.votingsystem.GUI;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.services.VotingService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultsCandidatesGUI {
    private Stage stage;
    private VBox partiesResultsBox;
    private final VotingService votingService;

    public ResultsCandidatesGUI(Stage stage, VBox partiesResultsBox, VotingService votingService) {
        this.stage = stage;
        this.partiesResultsBox = partiesResultsBox;
        this.votingService = votingService;
    }

    final String buttonStyle = "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 15px; -fx-background-radius: 15px;";
    final String buttonHoverStyle = "-fx-background-color: #005BA1;";
    final String headingStyle = "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #003366;";
    final String resultLabelStyle = "-fx-font-size: 18px; -fx-padding: 5px;";

    public VBox createResultsCandidatesUI() {
        VBox contentBox = new VBox(10); // Box to hold the results
        contentBox.setPadding(new Insets(10, 10, 10, 10));
        contentBox.setAlignment(Pos.CENTER);

        // Heading label
        Label headingLabel = new Label("Zwycięzcy kandydaci w każdym okręgu");
        headingLabel.setStyle(headingStyle);

        Map<Constituency, List<Candidate>> unsortedResults = votingService.getVotingResults();

        // Sorting the results by Constituency ID
        List<Map.Entry<Constituency, List<Candidate>>> sortedResults = unsortedResults.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().getConstituency_id()))
                .collect(Collectors.toList());

        for (Map.Entry<Constituency, List<Candidate>> entry : sortedResults) {
            Constituency constituency = entry.getKey();
            List<Candidate> winningCandidates = entry.getValue();

            Label constituencyLabel = new Label("Ogkęg wyborczy nr " + constituency.getConstituency_id() + " - Zwycięzcy Kandydaci:");
            constituencyLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            contentBox.getChildren().add(constituencyLabel);

            for (Candidate candidate : winningCandidates) {
                Label candidateLabel = new Label(candidate.getName() + " " + candidate.getSurname() + " - " + candidate.getPoliticalParty().getName());
                candidateLabel.setStyle(resultLabelStyle);
                contentBox.getChildren().add(candidateLabel);
            }
        }


        // ScrollPane to make the content scrollable
        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);

        VBox resultsBox = new VBox(); // Main VBox containing the ScrollPane
        resultsBox.setAlignment(Pos.CENTER);
        resultsBox.setSpacing(10);
        resultsBox.setPadding(new Insets(20));
        resultsBox.setPrefSize(600, 400); // You can adjust the size as needed


        // Wroc button
        Button btnWroc = new Button("Wróć");
        btnWroc.setPrefWidth(300);
        btnWroc.setStyle(buttonStyle);
        btnWroc.setOnAction(event -> {
            stage.getScene().setRoot(partiesResultsBox);
        });
        btnWroc.setOnMouseEntered(e -> btnWroc.setStyle(buttonStyle + buttonHoverStyle));
        btnWroc.setOnMouseExited(e -> btnWroc.setStyle(buttonStyle));

        resultsBox.getChildren().addAll(headingLabel, scrollPane, btnWroc);

        return resultsBox;
    }
}
