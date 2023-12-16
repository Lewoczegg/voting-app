package com.example.votingsystem.GUI;

import com.example.votingsystem.GUI.VotingApplication.StageReadyEvent;
import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.PoliticalPartyService;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private ApplicationContext applicationContext;
    private final PoliticalPartyService politicalPartyService;
    private final CandidateService candidateService;

    public StageInitializer(ApplicationContext applicationContext, PoliticalPartyService politicalPartyService, CandidateService candidateService) {
        this.applicationContext = applicationContext;
        this.politicalPartyService = politicalPartyService;
        this.candidateService = candidateService;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        stage.setTitle("Voting System");


        MainManuGUI mainManu = new MainManuGUI(stage, politicalPartyService, candidateService);
        VBox vBox = mainManu.createMainManuUI();
        stage.setScene(new Scene(vBox, 1366, 766));

        stage.setFullScreen(true);
        stage.show();
    }
}
