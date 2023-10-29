package com.example.votingsystem.UI;

import com.example.votingsystem.entities.PoliticalParty;
import com.example.votingsystem.services.PoliticalPartyService;
import jexer.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MainWindow {

    @Autowired
    private PoliticalPartyService politicalPartyService;

    private TApplication app;
    private TWindow mainWindow;

    public MainWindow() throws UnsupportedEncodingException {
        app = new TApplication(TApplication.BackendType.SWING); // or XTERM for pure terminal experience

        // Create main window
        TWindow mainWindow = app.addWindow("Voting App", 0, 0, 80, 24);

        TButton button1 = new TButton(mainWindow, "Log IN", 10, 5, new TAction() {
            @Override
            public void DO() {
                System.out.println("Button1");
            }
        });

        TButton button2 = new TButton(mainWindow, "Pokaż partie", 10, 8, new TAction() {
            @Override
            public void DO() {
                System.out.println("Button2");
                showParties();
            }
        });

        TButton button3 = new TButton(mainWindow, "Pokaż wyniki", 10, 11, new TAction() {
            @Override
            public void DO() {
                System.out.println("Button3");
            }
        });


    }

    public void run() {
        // Main event loop
        app.run();
    }

    private void showParties() {

        TWindow partyWindow = app.addWindow("Partie", 0, 0, 80, 24);

        List<PoliticalParty> parties = politicalPartyService.getAllPoliticalParties();

        int buttonYPosition = 0; // Starting vertical position for the party buttons
        int buttonHeight = 2; // Assuming each button takes up 2 vertical spaces

        for (PoliticalParty party : parties) {
            new TButton(partyWindow, party.getName(), 0, buttonYPosition, new TAction() {
                @Override
                public void DO() {
                    // Handle party button click if needed
                    System.out.println("Selected party: " + party.getName());
                }
            });
            buttonYPosition += buttonHeight;
        }

        // Add a button to close the window at the bottom or another suitable position
        TButton closeButton = new TButton(partyWindow, "Close", 20, buttonYPosition, new TAction() {
            @Override
            public void DO() {
                partyWindow.close();
            }
        });
    }
}
