package com.revature.project0.screens;

import com.revature.project0.util.ScreenRouter;
import static com.revature.project0.Driver.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * First screen displayed for users.  From here, they may opt to navigate
 * to the Login screen, Register screen, or exit the app.
 */
public class WelcomeScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;

    File file;
    PrintStream printStream;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("WelcomeScreen", "/welcome");
        this.consoleReader = consoleReader;
        this.router = router;

        file = new File("exceptionLog.txt");//TODO: gross

        try {
            printStream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(printStream);
        }
    }


    /**
     * Displays information for user to respond to with input.
     */
    @Override
    public void render(){

        System.out.println("Welcome to the Banking app!");
        System.out.println("1) Login to account");
        System.out.println("2) Register a new account");
        System.out.println("3) Exit application");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to login screen...");
                    router.navigate("/login");//calls a screen class which will put up text to screen
                    break;
                case "2":
                    System.out.println("Navigating to register screen...");
                    router.navigate("/register");
                    break;
                case "3":
                    System.out.println("Exiting application...");
                    app().setAppRunning(false);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
