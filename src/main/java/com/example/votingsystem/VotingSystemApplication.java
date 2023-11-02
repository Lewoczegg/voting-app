package com.example.votingsystem;
import com.example.votingsystem.UI.MainWindow;
import com.example.votingsystem.entities.User;
import com.example.votingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.List;

@SpringBootApplication
public class VotingSystemApplication implements CommandLineRunner {

    @Autowired
    MainWindow mainWindow;
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(VotingSystemApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void hashAndStorePasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            String plainPassword = user.getPassword();
            String hashedPassword = passwordEncoder.encode(plainPassword);
            user.setPassword(hashedPassword);
            userRepository.save(user);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        //hashAndStorePasswords();
        mainWindow.run();
    }
}
