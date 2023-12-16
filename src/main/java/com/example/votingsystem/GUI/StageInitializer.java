package com.example.votingsystem.GUI;

import com.example.votingsystem.GUI.VotingApplication.StageReadyEvent;
import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.LoginService;
import com.example.votingsystem.services.PoliticalPartyService;
import com.example.votingsystem.services.VotingService;
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
    private final LoginService loginService;
    private final VotingService votingService;

    public StageInitializer(ApplicationContext applicationContext, PoliticalPartyService politicalPartyService, CandidateService candidateService, LoginService loginService, VotingService votingService) {
        this.applicationContext = applicationContext;
        this.politicalPartyService = politicalPartyService;
        this.candidateService = candidateService;
        this.loginService = loginService;
        this.votingService = votingService;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        stage.setTitle("Voting System");


        MainManuGUI mainManu = new MainManuGUI(stage, politicalPartyService, candidateService, loginService, votingService);
        VBox vBox = mainManu.createMainManuUI();
        stage.setScene(new Scene(vBox, 1366, 766));

        stage.setFullScreen(true);
        stage.show();
    }
}
