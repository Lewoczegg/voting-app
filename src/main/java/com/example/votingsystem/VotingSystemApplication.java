package com.example.votingsystem;
import com.example.votingsystem.UI.MainWindow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class VotingSystemApplication {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(VotingSystemApplication.class, args);

        MainWindow mainWindow = new MainWindow();
        mainWindow.run();
    }
}
