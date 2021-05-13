package com.revature.project0.screens;

import com.revature.project0.daos.AccountDAO;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.AppUserInfo;
import com.revature.project0.util.ScreenRouter;

import java.io.*;

public class LoginScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDAO;
    private AccountDAO accountDAO;
    private AppUserInfo appUserInfo;

    File file;
    PrintStream printStream;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router,
                       AppUserInfo appUserInfo, UserDAO userDAO, AccountDAO accountDAO) {
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
        this.appUserInfo = appUserInfo;
        this.accountDAO = accountDAO;

        file = new File("/resources/exceptionLog.txt");//TODO: gross

        try {
            printStream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(printStream);
        }
    }

    public void render() {
        System.out.println("Login to your account!");
        System.out.println("+-------------------------+");

        String username = null;
        String password = null;
        AppUser appUser;//null by default

        int tries = 5;
        whileL : while (true){
            try {
                System.out.println("Enter username: ");
                username = consoleReader.readLine();

                if(username.equals("e")){
                    System.out.println("Redirecting to welcome screen...");
                    return;
                }

                System.out.println("Enter password: ");
                password = consoleReader.readLine();

                System.out.println("Locating user...");

            } catch (IOException e) {
                e.printStackTrace(printStream);
            }


            if (username.length() >= 3 && username.length() <=20 &&
                    password.length() > 7 && password.length() < 20) {
                break whileL;
            } else if (tries > 0) {
                tries--;
                System.out.println("Invalid username/password! Try again, or enter 'e' to exit to welcome screen.");
            } else {
                System.out.println("Too many invalid login attempts.  Redirecting to welcome screen...");
                return;
            }
        }

        boolean userFound = false;

        try {
            appUser = userDAO.findUserByUsernameAndPassword(username, password);


            if (appUser.getPassword().equals(password) && appUser.getUsername().equals(username)) {
                System.out.println("User " + appUser.getUsername() + " found!");
                System.out.println("Logging in to account...");

                appUserInfo.setCurrentUser(appUser.getUsername());
                appUserInfo.setCurrentUserPassword(appUser.getPassword());

                //if there is an account, move to next portion of login screen (logged on)
                userFound = true;
            } else {
                System.out.println("User not found!");
            }
        } catch (NullPointerException e) {
            //System.out.println("User not found!");
            e.printStackTrace(printStream);
        }

        //appUser is still set to above, unless null, in which case this won't happen
        //have router navigate to AccountScreen, which AppState can pass appUser into
        //as an argument at app start, which will still be pointing to the same app
        //the one in this method is pointing to.  Because there should only be one
        //userDAO all the classes are pointing to.  It was only instantiated once in
        //the AppState class.
        if(userFound) {
            router.navigate("/account");
        }


    }//exits back to AppState (in loop from Driver.java



}
