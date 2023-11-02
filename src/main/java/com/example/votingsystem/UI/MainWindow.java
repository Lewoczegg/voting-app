package com.example.votingsystem.UI;

import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.PoliticalPartyService;
import jakarta.annotation.PostConstruct;
import jexer.*;
import jexer.bits.CellAttributes;
import jexer.bits.Color;
import jexer.bits.ColorTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class MainWindow {
    @Autowired
    private PoliticalPartyService politicalPartyService;
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private TApplication app;
    private TWindow mainWindow;
    private PartyWindow partyWindow;

    public MainWindow() throws UnsupportedEncodingException {
        app = new TApplication(TApplication.BackendType.SWING); // or XTERM for pure terminal experience

        // Create main window
        mainWindow = app.addWindow("Voting App", 0, 0, 80, 24);

        TButton button1 = new TButton(mainWindow, "Zaloguj", 10, 5, new TAction() {
            @Override
            public void DO() {
                System.out.println("Button1");
                showLoginWindow();
                updateUIBasedOnAuthenticationStatus();
            }
        });

        TButton button2 = new TButton(mainWindow, "Pokaż partie", 10, 8, new TAction() {
            @Override
            public void DO() {
                System.out.println("Button2");
                partyWindow.show();
            }
        });

        TButton button3 = new TButton(mainWindow, "Pokaż wyniki", 10, 11, new TAction() {
            @Override
            public void DO() {
                System.out.println("Button3");
            }
        });
    }

    @PostConstruct
    public void init() {
        this.partyWindow = new PartyWindow(app, politicalPartyService, candidateService);
    }

    public void run() {
        // Main event loop
        app.run();
    }

    private boolean authenticateAndLogin(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(username, password);
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(request));
            return true;
            // Handle successful login, e.g., navigate to another screen
        } catch (AuthenticationException e) {
            return false;
            // Handle failed login, e.g., show an error message
        }
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    private void updateUIBasedOnAuthenticationStatus() {
        if (isLoggedIn()) {
            // Display elements or messages for authenticated users
            System.out.println("User is logged in!");
        } else {
            // Display elements or messages for non-authenticated users
            System.out.println("User is not logged in.");
        }
    }

    private void showLoginWindow() {
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
                authenticateAndLogin(username, password);
                updateUIBasedOnAuthenticationStatus();
                if (isLoggedIn()) {
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
