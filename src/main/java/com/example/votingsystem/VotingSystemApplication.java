package com.example.votingsystem;
import com.example.votingsystem.UI.MainWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class VotingSystemApplication implements CommandLineRunner {

    @Autowired
    MainWindow mainWindow;
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(VotingSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainWindow.run();
    }
}
