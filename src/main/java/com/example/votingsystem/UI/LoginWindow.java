package com.example.votingsystem.UI;

import com.example.votingsystem.services.LoginService;
import jexer.*;

public class LoginWindow {
    private final TApplication app;
    private final LoginService loginService;
    private final MainWindow mainWindow;

    public LoginWindow(TApplication app, LoginService loginService, MainWindow mainWindow) {
        this.app = app;
        this.loginService = loginService;
        this.mainWindow = mainWindow;
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
                if (loginService.isLoggedIn()) {
                    mainWindow.addLogOutButton();
                    mainWindow.removeLogInButton();
                    mainWindow.addVoteButton();
                    loginWindow.close();
                } else {
                    errorMessage.setLabel("Invalid login or password!");
                }
            }
        });

        TButton closeButton = new TButton(loginWindow, "Cofnij", 23, 8, new TAction() {
            @Override
            public void DO() {
                loginWindow.close();
            }
        });
    }
}
