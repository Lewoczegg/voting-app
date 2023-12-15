package com.example.votingsystem.GUI;

import com.example.votingsystem.GUI.VotingApplication.StageReadyEvent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private ApplicationContext applicationContext;

    public StageInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        stage.setTitle("Voting System");
        stage.setFullScreen(true);

        MainManu mainManu = new MainManu();
        VBox vbox = mainManu.createMainManuUI();

        Scene scene = new Scene(vbox, 1366, 768);
        stage.setScene(scene);
        stage.show();
    }
}
