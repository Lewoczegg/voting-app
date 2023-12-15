package com.example.votingsystem.GUI;

import com.example.votingsystem.services.PoliticalPartyService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainManuGUI {

    private Stage stage;
    private VBox mainVBox;
    private final PoliticalPartyService politicalPartyService;

    public MainManuGUI(Stage stage, PoliticalPartyService politicalPartyService) {
        this.stage = stage;
        this.politicalPartyService = politicalPartyService;
        this.mainVBox = createMainManuUI();
    }

    public VBox createMainManuUI() {
        VBox vbox = new VBox();
        vbox.setSpacing(14); // Spacing between elements
        vbox.setAlignment(Pos.CENTER);

        // Heading label
        Label heading = new Label("System głosowania");
        heading.setStyle("-fx-font-size: 28px; -fx-text-fill: black;");
        vbox.getChildren().add(heading);

        // Button CSS styles
        String buttonStyle = "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 15px; -fx-background-radius: 15px;";
        String buttonHoverStyle = "-fx-background-color: #005BA1;"; // Darker shade on hover

        // Button 1: Zaloguj
        Button btnZaloguj = new Button("Zaloguj");
        btnZaloguj.setPrefWidth(200);
        btnZaloguj.setStyle(buttonStyle);
        btnZaloguj.setOnAction(event -> {
            // Logic for Zaloguj
            System.out.println("Zaloguj clicked");
        });
        btnZaloguj.setOnMouseEntered(e -> btnZaloguj.setStyle(buttonStyle + buttonHoverStyle));
        btnZaloguj.setOnMouseExited(e -> btnZaloguj.setStyle(buttonStyle));
        vbox.getChildren().add(btnZaloguj);

        // Button 2: Pokaż partie
        Button btnPokazPartie = new Button("Pokaż partie");
        btnPokazPartie.setPrefWidth(200);
        btnPokazPartie.setStyle(buttonStyle);
        btnPokazPartie.setOnAction(event -> {
            PoliticalPartyGUI politicalParty = new PoliticalPartyGUI(stage, mainVBox, politicalPartyService);
            VBox partyVBox = politicalParty.createPartySceneUI();
            stage.getScene().setRoot(partyVBox);
        });
        btnPokazPartie.setOnMouseEntered(e -> btnPokazPartie.setStyle(buttonStyle + buttonHoverStyle));
        btnPokazPartie.setOnMouseExited(e -> btnPokazPartie.setStyle(buttonStyle));
        vbox.getChildren().add(btnPokazPartie);

        // Button 3: Pokaż wyniki
        Button btnPokazWyniki = new Button("Pokaż wyniki");
        btnPokazWyniki.setPrefWidth(200);
        btnPokazWyniki.setStyle(buttonStyle);
        btnPokazWyniki.setOnAction(event -> {
            // Logic for Pokaż wyniki
            System.out.println("Pokaż wyniki clicked");
        });
        btnPokazWyniki.setOnMouseEntered(e -> btnPokazWyniki.setStyle(buttonStyle + buttonHoverStyle));
        btnPokazWyniki.setOnMouseExited(e -> btnPokazWyniki.setStyle(buttonStyle));
        vbox.getChildren().add(btnPokazWyniki);

        // Button 4: Zamknij
        Button btnZamknij = new Button("Zamknij");
        btnZamknij.setPrefWidth(200);
        btnZamknij.setStyle(buttonStyle);
        btnZamknij.setOnAction(event -> {
            stage.close();
        });
        btnZamknij.setOnMouseEntered(e -> btnZamknij.setStyle(buttonStyle + buttonHoverStyle));
        btnZamknij.setOnMouseExited(e -> btnZamknij.setStyle(buttonStyle));
        vbox.getChildren().add(btnZamknij);

        return vbox;
    }
}
