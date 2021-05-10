package com.revature.project0.screens;

import com.revature.project0.daos.AccountDAO;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppAccount;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.AppUserInfo;
import com.revature.project0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDAO;
    private AccountDAO accountDAO;
    private AppUserInfo appUserInfo;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router,
                       AppUserInfo appUserInfo, UserDAO userDAO, AccountDAO accountDAO) {
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
        this.appUserInfo = appUserInfo;
        this.accountDAO = accountDAO;
    }

    public void render() {
        System.out.println("Login to your account!");
        System.out.println("+-------------------------+");

        String username = null;
        String password = null;
        AppUser appUser = null;

        //TODO: make this a while loop that continues until values are not null
        try {
            System.out.println("Enter username: ");
            username = consoleReader.readLine();

            System.out.println("Enter password: ");
            password = consoleReader.readLine();

            System.out.println("Locating user...");

        } catch (IOException e) {
            e.printStackTrace();
        }


        if(username == null || password == null){
            System.out.println("User not found!");
            return;
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
            e.printStackTrace();
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
