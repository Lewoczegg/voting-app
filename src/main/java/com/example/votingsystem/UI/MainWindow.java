package com.example.votingsystem.UI;

import com.example.votingsystem.services.CandidateService;
import com.example.votingsystem.services.LoginService;
import com.example.votingsystem.services.PoliticalPartyService;
import jakarta.annotation.PostConstruct;
import jexer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class MainWindow {
    @Autowired
    private PoliticalPartyService politicalPartyService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private LoginService loginService;

    private TApplication app;
    private TWindow mainWindow;
    private PartyWindow partyWindow;
    private LoginWindow loginWindow;
    private VotePartyWindow votePartyWindow;

    TButton logInButton;
    TButton voteButton;
    TButton logOutButton;

    public MainWindow() throws UnsupportedEncodingException {
        app = new TApplication(TApplication.BackendType.SWING); // or XTERM for pure terminal experience

        // Create main window
        mainWindow = app.addWindow("Voting App", 0, 0, 80, 24);

        addLogInButton();

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
        this.loginWindow = new LoginWindow(app, loginService, this);
        this.votePartyWindow = new VotePartyWindow(app, politicalPartyService, candidateService, loginService);
    }

    public void run() {
        // Main event loop
        app.run();
    }

    public void addLogInButton() {
        logInButton = new TButton(mainWindow, "Zaloguj", 10, 5, new TAction() {
            @Override
            public void DO() {
                System.out.println("Button1");
                loginWindow.showLoginWindow();
            }
        });
    }

    public void removeLogInButton() {
        logInButton.remove();
    }

    public void addVoteButton() {
        voteButton = new TButton(mainWindow, "Vote", 10, 5, new TAction() {
            @Override
            public void DO() {
                votePartyWindow.show();
            }
        });
    }

    public void removeVoteButton() {
        voteButton.remove();
    }

    public void addLogOutButton() {
        logOutButton = new TButton(mainWindow, "Wyloguj", 10, 14, new TAction() {
            @Override
            public void DO() {
                System.out.println("Logout Button");
                loginService.logout();
                removeLogoutButton();
                removeVoteButton();
                addLogInButton();
            }
        });
    }

    public void removeLogoutButton() {
        logOutButton.remove();
    }
}
