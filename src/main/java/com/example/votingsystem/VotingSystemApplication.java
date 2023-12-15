package com.example.votingsystem;
import com.example.votingsystem.GUI.VotingApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VotingSystemApplication{
    public static void main(String[] args) {
        Application.launch(VotingApplication.class, args);
    }
}
