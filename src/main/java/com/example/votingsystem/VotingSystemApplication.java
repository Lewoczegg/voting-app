package com.example.votingsystem;
import com.example.votingsystem.UI.MainWindow;
import com.example.votingsystem.services.DatabaseInitializerService;
import com.example.votingsystem.services.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class VotingSystemApplication implements CommandLineRunner {

    @Autowired
    MainWindow mainWindow;
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(VotingSystemApplication.class, args);
    }

    @Autowired
    private DatabaseInitializerService databaseInitializerService;

    @Override
    public void run(String... args) throws Exception {
        // databaseInitializerService.createUsers();
        // databaseInitializerService.autoVoteForUnvotedUsers();
        mainWindow.run();
    }
}
