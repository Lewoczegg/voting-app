package com.example.votingsystem.GUI;

import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.PoliticalPartyService;
import com.example.votingsystem.services.VotingService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class VotePartyGUI {
    private Stage stage;
    private VBox mainVBox;
    private VBox partiesVBox;
    private final PoliticalPartyService politicalPartyService;
    private final CandidateService candidateService;
    private final VotingService votingService;

    public VotePartyGUI(Stage stage, VBox mainVBox, PoliticalPartyService politicalPartyService, CandidateService candidateService, VotingService votingService) {
        this.stage = stage;
        this.mainVBox = mainVBox;
        this.politicalPartyService = politicalPartyService;
        this.candidateService = candidateService;
        this.votingService = votingService;
    }

    // Button CSS styles
    String buttonStyle = "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 15px; -fx-background-radius: 15px;";
    String buttonHoverStyle = "-fx-background-color: #005BA1;";

    public VBox createVotePartyUI() {
        VBox vbox = new VBox();
        vbox.setSpacing(14);
        vbox.setAlignment(Pos.CENTER);

        // Fetch all political parties
        List<PoliticalParty> parties = politicalPartyService.getAllPoliticalParties();

        for (PoliticalParty party : parties) {
            Button partyButton = new Button(party.getName());
            partyButton.setPrefWidth(500);
            partyButton.setStyle(buttonStyle);
            partyButton.setOnAction(event -> {
                // Handle voting logic for the selected party
                System.out.println("Voted for " + party.getName());
            });
            partyButton.setOnMouseEntered(e -> partyButton.setStyle(buttonStyle + buttonHoverStyle));
            partyButton.setOnMouseExited(e -> partyButton.setStyle(buttonStyle));
            vbox.getChildren().add(partyButton);
        }

        // Button: Wróć
        Button btnWroc = new Button("Wróć");
        btnWroc.setPrefWidth(300);
        btnWroc.setStyle(buttonStyle);
        btnWroc.setOnAction(event -> {
            stage.getScene().setRoot(mainVBox);
        });
        btnWroc.setOnMouseEntered(e -> btnWroc.setStyle(buttonStyle + buttonHoverStyle));
        btnWroc.setOnMouseExited(e -> btnWroc.setStyle(buttonStyle));
        vbox.getChildren().add(btnWroc);

        return vbox;
    }
}
