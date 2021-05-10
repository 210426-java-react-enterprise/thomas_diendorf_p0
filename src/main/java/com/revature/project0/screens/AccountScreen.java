package com.revature.project0.screens;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDAO;

    public AccountScreen(BufferedReader consoleReader, ScreenRouter router, UserDAO userDAO){
        super("AccountScreen", "/account");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
    }


    public void render(){

        AppUser appUser = null;

        System.out.println("AccountScreen under construction!");
    }


}
