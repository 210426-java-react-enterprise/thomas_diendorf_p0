package com.revature.project0.util;

import com.revature.project0.daos.AccountDAO;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.screens.AccountScreen;
import com.revature.project0.screens.LoginScreen;
import com.revature.project0.screens.RegisterScreen;
import com.revature.project0.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
Initializes the BufferedReader and ScreenRouter to be used across all classes.

Instantiates all "new" Screens, so that no other class utilizing this one need do so.

 */
public class AppState {
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private AppUserInfo appUserInfo;
    private boolean appRunning;
    private final UserDAO userDAO;
    private final AccountDAO accountDAO;

    public AppState(){

        System.out.println("Initializing application...");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        router = new ScreenRouter();
        userDAO = new UserDAO();
        accountDAO = new AccountDAO();
        appUserInfo = new AppUserInfo();

        router.addScreen(new WelcomeScreen(consoleReader, router));
        router.addScreen(new RegisterScreen(consoleReader, router, appUserInfo, userDAO));
        router.addScreen(new LoginScreen(consoleReader, router, appUserInfo, userDAO, accountDAO));
        router.addScreen(new AccountScreen(consoleReader, router, appUserInfo, userDAO, accountDAO));

        System.out.println("Application initialized!");

    }

    /**
     Returns the Screen class that is to be rendered.

     @return ScreenRouter

     */
    public ScreenRouter getRouter() {
        return router;
    }


    /**
     Returns a boolean stating if the app is running, or not.

     @return boolean
     */
    public boolean isAppRunning() {
        return appRunning;
    }


    /**
     Makes the app stop running next time a screen ends.
     */
    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

}
