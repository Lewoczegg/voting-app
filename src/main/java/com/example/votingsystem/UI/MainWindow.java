package com.example.votingsystem.UI;

import jexer.TAction;
import jexer.TApplication;
import jexer.TButton;
import jexer.TWindow;

import java.io.UnsupportedEncodingException;

public class MainWindow {
    private TApplication app;

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
}
