package com.example.votingsystem.GUI;

import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.services.VotingService;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.chart.PieChart.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultsPartyGUI {
    private Stage stage;
    private VBox mainVBox;
    private final VotingService votingService;
    private VBox rootBox;
    final String buttonStyle = "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 15px; -fx-background-radius: 15px;";
    final String buttonHoverStyle = "-fx-background-color: #005BA1;";
    final String headingStyle = "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #003366;";
    final String resultLabelStyle = "-fx-font-size: 18px; -fx-padding: 5px;";


    public ResultsPartyGUI(Stage stage, VBox mainVBox, VotingService votingService) {
        this.stage = stage;
        this.mainVBox = mainVBox;
        this.votingService = votingService;
    }

    public VBox createResultsPartyUI() {
        HBox hbox = new HBox(20); // Container for labels and chart
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));

        VBox labelsBox = new VBox(10); // VBox for labels
        labelsBox.setAlignment(Pos.TOP_LEFT);

        // Heading label
        Label heading = new Label("Wyniki Wyborów");
        heading.setStyle(headingStyle);
        labelsBox.getChildren().add(heading);

        Map<PoliticalParty, Long> deputiesCountByParty = votingService.getDeputiesCountByParty();
        for (Map.Entry<PoliticalParty, Long> entry : deputiesCountByParty.entrySet()) {
            Label partyResultLabel = new Label(entry.getKey().getName() + ": " + entry.getValue() + " posłow");
            partyResultLabel.setStyle(resultLabelStyle);
            labelsBox.getChildren().add(partyResultLabel);
        }

        // Pie Chart
        PieChart pieChart = new PieChart();
        pieChart.setPrefSize(500, 500); // Set preferred size for the Pie Chart

        ObservableList<Data> pieChartData = pieChart.getData();
        for (Map.Entry<PoliticalParty, Long> entry : deputiesCountByParty.entrySet()) {
            Data slice = new PieChart.Data(entry.getKey().getName() + " -", entry.getValue());
            pieChartData.add(slice);
        }

        // Adding a label to each slice
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        javafx.beans.binding.Bindings.concat(
                                data.getName(), " ", Math.round(data.getPieValue()), " posłów"
                        )
                )
        );

        pieChart.setTitle("Wyniki Wyborów");

        hbox.getChildren().addAll(labelsBox, pieChart);

        rootBox = new VBox(heading, hbox); // VBox to hold the heading and HBox
        rootBox.setAlignment(Pos.CENTER);


        // Wroc button
        Button btnWroc = new Button("Wróć");
        btnWroc.setPrefWidth(300);
        btnWroc.setStyle(buttonStyle);
        btnWroc.setOnAction(event -> {
            stage.getScene().setRoot(mainVBox);
        });
        btnWroc.setOnMouseEntered(e -> btnWroc.setStyle(buttonStyle + buttonHoverStyle));
        btnWroc.setOnMouseExited(e -> btnWroc.setStyle(buttonStyle));

        rootBox.getChildren().add(btnWroc); // Add the button to the root VBox
        return rootBox;
    }
}
