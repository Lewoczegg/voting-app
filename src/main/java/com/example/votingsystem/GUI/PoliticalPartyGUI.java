package com.example.votingsystem.GUI;

import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.services.PoliticalPartyService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class PoliticalPartyGUI {
    private Stage stage;
    private VBox mainVbox;
    private final PoliticalPartyService politicalPartyService;

    public PoliticalPartyGUI(Stage stage, VBox mainVbox, PoliticalPartyService politicalPartyService) {
        this.stage = stage;
        this.mainVbox = mainVbox;
        this.politicalPartyService = politicalPartyService;
    }

    public VBox createPartySceneUI() {
        VBox vbox = new VBox();
        vbox.setSpacing(14);
        vbox.setAlignment(Pos.CENTER);

        // Heading label
        Label heading = new Label("Partie polityczne");
        heading.setStyle("-fx-font-size: 28px; -fx-text-fill: black;");
        vbox.getChildren().add(heading);

        // Button CSS styles
        String buttonStyle = "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 15px; -fx-background-radius: 15px;";
        String buttonHoverStyle = "-fx-background-color: #005BA1;"; // Darker shade on hover

        // Get all political parties
        List<PoliticalParty> parties = politicalPartyService.getAllPoliticalParties();

        for (PoliticalParty party : parties) {
            Button partyButton = new Button(party.getName());
            partyButton.setPrefWidth(500);
            partyButton.setStyle(buttonStyle);
            // Add your event handler here for button click
            // partyButton.setOnAction(...)
            partyButton.setOnMouseEntered(e -> partyButton.setStyle(buttonStyle + buttonHoverStyle));
            partyButton.setOnMouseExited(e -> partyButton.setStyle(buttonStyle));
            vbox.getChildren().add(partyButton);
        }

        // Button: Wróć
        Button btnWroc = new Button("Wróć");
        btnWroc.setPrefWidth(300);
        btnWroc.setStyle(buttonStyle);
        btnWroc.setOnAction(event -> {
            stage.getScene().setRoot(mainVbox);
        });
        btnWroc.setOnMouseEntered(e -> btnWroc.setStyle(buttonStyle + buttonHoverStyle));
        btnWroc.setOnMouseExited(e -> btnWroc.setStyle(buttonStyle));
        vbox.getChildren().add(btnWroc);

        return vbox;
    }
}
