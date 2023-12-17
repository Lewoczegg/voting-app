package com.example.votingsystem;
import com.example.votingsystem.GUI.VotingApplication;
import com.example.votingsystem.services.DatabaseInitializerService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VotingSystemApplication{

    @Autowired
    private static DatabaseInitializerService databaseInitializerService;
    public static void main(String[] args) {
        // databaseInitializerService.initializeDatabase();
        Application.launch(VotingApplication.class, args);
    }
}
