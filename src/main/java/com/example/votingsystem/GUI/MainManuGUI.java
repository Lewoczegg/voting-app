package com.example.votingsystem.GUI;

import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.LoginService;
import com.example.votingsystem.services.PoliticalPartyService;
import com.example.votingsystem.services.VotingService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainManuGUI {

    private Stage stage;
    private final PoliticalPartyService politicalPartyService;
    private final CandidateService candidateService;
    private final LoginService loginService;
    private final VotingService votingService;

    // Button CSS styles
    String buttonStyle = "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 15px; -fx-background-radius: 15px;";
    String buttonHoverStyle = "-fx-background-color: #005BA1;";

    VBox vbox = new VBox();

    public MainManuGUI(Stage stage, PoliticalPartyService politicalPartyService, CandidateService candidateService, LoginService loginService, VotingService votingService) {
        this.stage = stage;
        this.politicalPartyService = politicalPartyService;
        this.candidateService = candidateService;
        this.loginService = loginService;
        this.votingService = votingService;
    }

    public VBox createMainManuUI() {

        vbox.setSpacing(14); // Spacing between elements
        vbox.setAlignment(Pos.CENTER);

        // Heading label
        vbox.getChildren().add(createHeadingLabel());

        // Button 1: Zaloguj | Vote
        if (loginService.isLoggedIn()) {
            vbox.getChildren().add(createVoteBtn());
        } else {
            vbox.getChildren().add(createLogInBtn());
        }

        // Button 2: Pokaż partie
        vbox.getChildren().add(createShowPartiesBtn());

        // Button 3: Pokaż wyniki
        vbox.getChildren().add(createShowResultsBtn());

        //Button 4: Wyloguj
        if (loginService.isLoggedIn()) {
            vbox.getChildren().add(createLogOutBtn());
        }

        // Button 5: Zamknij
        vbox.getChildren().add(createCloseBtn());

        return vbox;
    }

    private Label createHeadingLabel() {
        Label heading = new Label("System głosowania");
        heading.setStyle("-fx-font-size: 28px; -fx-text-fill: black;");

        return heading;
    }

    private Button createLogInBtn() {
        Button btnZaloguj = new Button("Zaloguj");
        btnZaloguj.setPrefWidth(200);
        btnZaloguj.setStyle(buttonStyle);
        btnZaloguj.setOnAction(event -> {
            LoginGUI loginGUI = new LoginGUI(stage, loginService, politicalPartyService, candidateService, votingService);
            VBox loginUI = loginGUI.createLoginUI();
            stage.getScene().setRoot(loginUI);
        });
        btnZaloguj.setOnMouseEntered(e -> btnZaloguj.setStyle(buttonStyle + buttonHoverStyle));
        btnZaloguj.setOnMouseExited(e -> btnZaloguj.setStyle(buttonStyle));

        return btnZaloguj;
    }

    private Button createVoteBtn() {
        Button btnVote = new Button("Zagłosuj");
        btnVote.setPrefWidth(200);
        btnVote.setStyle(buttonStyle);
        btnVote.setOnAction(event -> {
            if (loginService.getCurrentUser().isVoted()) {
                vbox.getChildren().clear();
                vbox.getChildren().add(createHeadingLabel());
                vbox.getChildren().add(createVoteBtn());
                vbox.getChildren().add(createAlreadyVotedLabel());
                vbox.getChildren().add(createShowPartiesBtn());
                vbox.getChildren().add(createShowResultsBtn());
                vbox.getChildren().add(createLogOutBtn());
                vbox.getChildren().add(createCloseBtn());
            } else {
                VotePartyGUI votePartyGUI = new VotePartyGUI(stage, vbox, politicalPartyService, candidateService, votingService, loginService);
                VBox votePartyUI = votePartyGUI.createVotePartyUI();
                stage.getScene().setRoot(votePartyUI);
            }
        });
        btnVote.setOnMouseEntered(e -> btnVote.setStyle(buttonStyle + buttonHoverStyle));
        btnVote.setOnMouseExited(e -> btnVote.setStyle(buttonStyle));

        return btnVote;
    }

    private Button createShowPartiesBtn() {
        Button btnPokazPartie = new Button("Pokaż partie");
        btnPokazPartie.setPrefWidth(200);
        btnPokazPartie.setStyle(buttonStyle);
        btnPokazPartie.setOnAction(event -> {
            PoliticalPartyGUI politicalParty = new PoliticalPartyGUI(stage, vbox, politicalPartyService, candidateService);
            VBox partyVBox = politicalParty.createPartySceneUI();
            stage.getScene().setRoot(partyVBox);
        });
        btnPokazPartie.setOnMouseEntered(e -> btnPokazPartie.setStyle(buttonStyle + buttonHoverStyle));
        btnPokazPartie.setOnMouseExited(e -> btnPokazPartie.setStyle(buttonStyle));

        return btnPokazPartie;
    }

    private Button createShowResultsBtn() {
        Button btnPokazWyniki = new Button("Pokaż wyniki");
        btnPokazWyniki.setPrefWidth(200);
        btnPokazWyniki.setStyle(buttonStyle);
        btnPokazWyniki.setOnAction(event -> {
            // Logic for Pokaż wyniki
            System.out.println("Pokaż wyniki clicked");
        });
        btnPokazWyniki.setOnMouseEntered(e -> btnPokazWyniki.setStyle(buttonStyle + buttonHoverStyle));
        btnPokazWyniki.setOnMouseExited(e -> btnPokazWyniki.setStyle(buttonStyle));

        return btnPokazWyniki;
    }

    private Button createLogOutBtn() {
        Button btnWyloguj = new Button("Wyloguj");
        btnWyloguj.setPrefWidth(200);
        btnWyloguj.setStyle(buttonStyle);
        btnWyloguj.setOnAction(event -> {
            loginService.logout();
            vbox.getChildren().clear();
            vbox.getChildren().add(createHeadingLabel());
            vbox.getChildren().add(createLogInBtn());
            vbox.getChildren().add(createShowPartiesBtn());
            vbox.getChildren().add(createShowResultsBtn());
            vbox.getChildren().add(createCloseBtn());
        });
        btnWyloguj.setOnMouseEntered(e -> btnWyloguj.setStyle(buttonStyle + buttonHoverStyle));
        btnWyloguj.setOnMouseExited(e -> btnWyloguj.setStyle(buttonStyle));

        return btnWyloguj;
    }

    private Button createCloseBtn() {
        Button btnZamknij = new Button("Zamknij");
        btnZamknij.setPrefWidth(200);
        btnZamknij.setStyle(buttonStyle);
        btnZamknij.setOnAction(event -> {
            stage.close();
        });
        btnZamknij.setOnMouseEntered(e -> btnZamknij.setStyle(buttonStyle + buttonHoverStyle));
        btnZamknij.setOnMouseExited(e -> btnZamknij.setStyle(buttonStyle));

        return btnZamknij;
    }

    private Label createAlreadyVotedLabel() {
        Label messageLabel = new Label();
        messageLabel.setText("Twój głos już został oddany");
        messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
        return messageLabel;
    }
}
