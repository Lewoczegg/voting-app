package com.example.votingsystem;
import com.example.votingsystem.GUI.ChartApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VotingSystemApplication{
    public static void main(String[] args) {
        Application.launch(ChartApplication.class, args);
    }
}
