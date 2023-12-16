package com.example.votingsystem.GUI;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.entities.User;
import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.LoginService;
import com.example.votingsystem.services.VotingService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class VoteCandidateGUI {
    private final Stage stage;
    private final VBox mainVBox;
    private final VBox partiesVBox;
    private final CandidateService candidateService;
    private final VotingService votingService;
    private final LoginService loginService;

    public VoteCandidateGUI(Stage stage, VBox mainVBox, VBox partiesVBox, CandidateService candidateService, VotingService votingService, LoginService loginService) {
        this.stage = stage;
        this.mainVBox = mainVBox;
        this.partiesVBox = partiesVBox;
        this.candidateService = candidateService;
        this.votingService = votingService;
        this.loginService = loginService;
    }

    // Button CSS styles
    String buttonStyle = "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 15px; -fx-background-radius: 15px;";
    String buttonHoverStyle = "-fx-background-color: #005BA1;";
    VBox vbox;
    User currentUser;

    public VBox createVoteCandidateUI(PoliticalParty selectedParty) {
        vbox = new VBox();
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);

        // Heading label
        Label heading = new Label("Wybierz kandydata");
        heading.setStyle("-fx-font-size: 24px; -fx-text-fill: black; -fx-padding: 10px;");
        vbox.getChildren().add(heading);

        VBox candidatesBox = new VBox();
        candidatesBox.setSpacing(10);
        candidatesBox.setPadding(new Insets(10, 10, 10, 10));
        candidatesBox.setAlignment(Pos.CENTER);

        currentUser = loginService.getCurrentUser();
        List<Candidate> candidates = candidateService.findByPoliticalPartyAndConstituency(selectedParty, currentUser.getConstituency());

        for (Candidate candidate : candidates) {
            Button candidateButton = new Button(candidate.getName() + " " + candidate.getSurname());
            candidateButton.setPrefWidth(300);
            candidateButton.setStyle(buttonStyle);

            candidateButton.setOnAction(event -> {
                VBox confirmationBox = createVoteConfirmationUI(candidate);
                stage.getScene().setRoot(confirmationBox);
            });

            candidateButton.setOnMouseEntered(e -> candidateButton.setStyle(buttonStyle + buttonHoverStyle));
            candidateButton.setOnMouseExited(e -> candidateButton.setStyle(buttonStyle));
            candidatesBox.getChildren().add(candidateButton);
        }

        // ScrollPane for candidate buttons
        ScrollPane scrollPane = new ScrollPane(candidatesBox);
        scrollPane.setFitToWidth(true);
        vbox.getChildren().add(scrollPane);

        // Button: Wróć
        Button btnWroc = new Button("Wróć");
        btnWroc.setPrefWidth(300);
        btnWroc.setStyle(buttonStyle);
        btnWroc.setOnAction(event -> {
            stage.getScene().setRoot(partiesVBox);
        });
        btnWroc.setOnMouseEntered(e -> btnWroc.setStyle(buttonStyle + buttonHoverStyle));
        btnWroc.setOnMouseExited(e -> btnWroc.setStyle(buttonStyle));
        vbox.getChildren().add(btnWroc);

        return vbox;
    }

    private VBox createVoteConfirmationUI(Candidate candidate) {
        VBox confirmationBox = new VBox(10);
        confirmationBox.setAlignment(Pos.CENTER);
        confirmationBox.setPadding(new Insets(20));

        Label heading = new Label("Czy napewno chcesz zagłosować na " + candidate.getName() + " " + candidate.getSurname() + "?");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button btnVote = new Button("Zagłosuj");
        btnVote.setStyle(buttonStyle);
        btnVote.setPrefWidth(200);

        btnVote.setOnAction(event -> {
            votingService.vote(currentUser.getUser_id(), candidate.getCandidate_id());
            stage.getScene().setRoot(mainVBox);
        });

        btnVote.setOnMouseEntered(e -> btnVote.setStyle(buttonStyle + buttonHoverStyle));
        btnVote.setOnMouseExited(e -> btnVote.setStyle(buttonStyle));

        Button btnCancel = new Button("Cofnij");
        btnCancel.setStyle(buttonStyle);
        btnCancel.setPrefWidth(200);

        btnCancel.setOnAction(event -> {
            stage.getScene().setRoot(vbox);
        });

        btnCancel.setOnMouseEntered(e -> btnCancel.setStyle(buttonStyle + buttonHoverStyle));
        btnCancel.setOnMouseExited(e -> btnCancel.setStyle(buttonStyle));

        confirmationBox.getChildren().addAll(heading, btnVote, btnCancel);
        return confirmationBox;
    }
}
