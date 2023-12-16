package com.example.votingsystem.GUI;

import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.LoginService;
import com.example.votingsystem.services.PoliticalPartyService;
import com.example.votingsystem.services.VotingService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginGUI {
    private Stage stage;
    private final LoginService loginService;
    private final PoliticalPartyService politicalPartyService;
    private final CandidateService candidateService;
    private final VotingService votingService;

    public LoginGUI(Stage stage, LoginService loginService, PoliticalPartyService politicalPartyService, CandidateService candidateService, VotingService votingService) {
        this.stage = stage;
        this.loginService = loginService;
        this.politicalPartyService = politicalPartyService;
        this.candidateService = candidateService;
        this.votingService = votingService;
    }

    public VBox createLoginUI() {
        // CSS styles
        String labelStyle = "-fx-font-size: 24px; -fx-font-weight: bold;";
        String textFieldStyle = "-fx-padding: 10px; -fx-border-radius: 5px; -fx-background-color: lightgrey; -fx-font-size: 16px;";
        String buttonStyle = "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 15px; -fx-background-radius: 15px;";
        String buttonHoverStyle = "-fx-background-color: #005BA1;";

        VBox vbox = new VBox(15);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 20, 20, 20));

        // Login label
        Label loginLabel = new Label("Login");
        loginLabel.setStyle(labelStyle);

        // Username and password fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle(textFieldStyle);
        usernameField.setMaxWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle(textFieldStyle);
        passwordField.setMaxWidth(300);

        // Label for displaying login status
        Label statusLabel = new Label("");
        statusLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");

        // Login button
        Button btnZaloguj = new Button("Zaloguj");
        btnZaloguj.setPrefWidth(300);
        btnZaloguj.setStyle(buttonStyle);
        btnZaloguj.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean isAuthenticated = loginService.authenticateAndLogin(username, password);

            if (isAuthenticated) {
                System.out.println("Login successful");
                statusLabel.setText("");

                MainManuGUI mainManuGUI = new MainManuGUI(stage, politicalPartyService, candidateService, loginService, votingService);
                VBox mainVBox = mainManuGUI.createMainManuUI();
                stage.getScene().setRoot(mainVBox);
            } else {
                System.out.println("Login failed");
                statusLabel.setText("Login failed. Please try again.");
            }
        });
        btnZaloguj.setOnMouseEntered(e -> btnZaloguj.setStyle(buttonStyle + buttonHoverStyle));
        btnZaloguj.setOnMouseExited(e -> btnZaloguj.setStyle(buttonStyle));


        // Wroc button
        Button btnWroc = new Button("Wróć");
        btnWroc.setPrefWidth(300);
        btnWroc.setStyle(buttonStyle);
        btnWroc.setOnAction(event -> {
            MainManuGUI mainManuGUI = new MainManuGUI(stage, politicalPartyService, candidateService, loginService, votingService);
            VBox mainVBox = mainManuGUI.createMainManuUI();
            stage.getScene().setRoot(mainVBox);
        });
        btnWroc.setOnMouseEntered(e -> btnWroc.setStyle(buttonStyle + buttonHoverStyle));
        btnWroc.setOnMouseExited(e -> btnWroc.setStyle(buttonStyle));

        // Add elements to VBox
        vbox.getChildren().addAll(loginLabel, usernameField, passwordField, statusLabel, btnZaloguj, btnWroc);

        return vbox;
    }
}
