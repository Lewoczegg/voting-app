package com.example.votingsystem.UI;

import com.example.votingsystem.services.LoginService;
import jexer.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginWindow {
    private final TApplication app;
    private final LoginService loginService;

    public LoginWindow(TApplication app, LoginService loginService) {
        this.app = app;
        this.loginService = loginService;
    }

    private void updateUIBasedOnAuthenticationStatus() {
        if (loginService.isLoggedIn()) {
            // Display elements or messages for authenticated users
            System.out.println("User is logged in!");
        } else {
            // Display elements or messages for non-authenticated users
            System.out.println("User is not logged in.");
        }
    }

    public void showLoginWindow() {
        TWindow loginWindow = new TWindow(app, "Login", 0, 0, 80, 24, TWindow.NOZOOMBOX);

        TLabel usernameLabel = new TLabel(loginWindow, "Nazwa użytkownika:", 2, 2);
        TField usernameField = new TField(loginWindow, 23, 2, 20, false);

        TLabel passwordLabel = new TLabel(loginWindow, "Hasło:", 2, 4);
        TField passwordField = new TField(loginWindow, 23, 4, 20, false);

        TLabel errorMessage = new TLabel(loginWindow, "", 2, 10);

        TButton loginButton = new TButton(loginWindow, "Zaloguj", 23, 6, new TAction() {
            @Override
            public void DO() {
                String username = usernameField.getText();
                String password = passwordField.getText();
                loginService.authenticateAndLogin(username, password);
                updateUIBasedOnAuthenticationStatus();
                if (loginService.isLoggedIn()) {
                    loginWindow.close();  // Close the login window if login is successful
                } else {
                    errorMessage.setLabel("Invalid login or password!");
                }
            }
        });

        TButton closeButton = new TButton(loginWindow, "Cofnij", 23, 8, new TAction() {
            @Override
            public void DO() {
                loginWindow.close();  // Close the login window if login is successful
            }
        });
    }
}
