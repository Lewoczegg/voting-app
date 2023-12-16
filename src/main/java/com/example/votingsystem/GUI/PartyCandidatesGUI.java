package com.example.votingsystem.GUI;

import com.example.votingsystem.entities.Candidate;
import com.example.votingsystem.entities.Constituency;
import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.services.CandidateService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class PartyCandidatesGUI {

    private Stage stage;
    private VBox partiesVBox;
    private final CandidateService candidateService;

    public PartyCandidatesGUI(Stage stage, VBox partiesVBox, CandidateService candidateService) {
        this.stage = stage;
        this.partiesVBox = partiesVBox;
        this.candidateService = candidateService;
    }

    public BorderPane createPartyDetailUI(PoliticalParty party) {
        // Button CSS styles
        String buttonStyle = "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 15px; -fx-background-radius: 15px;";
        String buttonHoverStyle = "-fx-background-color: #005BA1;"; // Darker shade on hover


        VBox wrapperBox = new VBox();
        wrapperBox.setPadding(new Insets(10, 10, 10, 10));

        VBox contentBox = new VBox();
        contentBox.setSpacing(5);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(10, 10, 10, 10));

        Map<Constituency, List<Candidate>> candidatesByConstituency = candidateService.getCandidatesGroupedByConstituency(party);

        Image partyLogo = getPartyLogo(party.getPolitical_party_id());
        ImageView logoView = new ImageView(partyLogo);
        logoView.setFitHeight(200); // Set the desired height
        logoView.setPreserveRatio(true); // Preserve the aspect ratio
        contentBox.getChildren().add(logoView);

        for (Map.Entry<Constituency, List<Candidate>> entry : candidatesByConstituency.entrySet()) {
            Label constituencyLabel = new Label("Okręg wyborczy nr" + entry.getKey().getConstituency_id() + ":");
            constituencyLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            contentBox.getChildren().add(constituencyLabel);

            for (Candidate candidate : entry.getValue()) {
                Label candidateLabel = new Label(candidate.getName() + " " + candidate.getSurname());
                candidateLabel.setStyle("-fx-font-size: 14px;");
                contentBox.getChildren().add(candidateLabel);
            }
        }

        // ScrollPane to make the content scrollable
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10, 10, 10, 10));

        wrapperBox.getChildren().add(scrollPane);

        // Party name label
        Label partyNameLabel = new Label(party.getName());
        partyNameLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        partyNameLabel.setAlignment(Pos.CENTER);

        // BorderPane as the root layout
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(partyNameLabel);
        BorderPane.setAlignment(partyNameLabel, Pos.CENTER);
        borderPane.setCenter(wrapperBox);


        // Button: Wróć
        Button btnWroc = new Button("Wróć do listy partii");
        btnWroc.setPrefWidth(300);
        btnWroc.setStyle(buttonStyle);
        btnWroc.setOnAction(event -> {
            stage.getScene().setRoot(partiesVBox);
        });
        btnWroc.setOnMouseEntered(e -> btnWroc.setStyle(buttonStyle + buttonHoverStyle));
        btnWroc.setOnMouseExited(e -> btnWroc.setStyle(buttonStyle));
        BorderPane.setAlignment(btnWroc, Pos.CENTER);
        borderPane.setBottom(btnWroc);
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        return borderPane;
    }

    private Image getPartyLogo(Long partyId) {

        Image image = null;

        if (partyId == 1) {
            image = new Image("Bezpartyjni_Samorządowcy_logo.png");
        } else if (partyId == 2) {
            image = new Image("TD_1.png");
        } else if (partyId == 3) {
            image = new Image("logo-lewica.png");
        } else if (partyId == 4) {
            image = new Image("PiS.png");
        } else if (partyId == 5) {
            image = new Image("konfa.jpg");
        } else if (partyId == 6) {
            image = new Image("KO.png");
        } else if (partyId == 7) {
            image = new Image("PJJ.jpg");
        }

        return image;
    }
}
