package com.revature.project0;

//import java.util.Formatter;//used to format doubles to only have 2 decimals

import com.revature.project0.util.AppState;

/*
class is same name as: com.postgresql.Driver
see application.properties with driver=
 */

public class Driver {

    //initializes BufferedReader, for reading input
    //initializes ScreenRouter for keeping linkedlist of screens
    private static AppState app = new AppState();

    public static void main(String[] args) {

        //only way to exit while loop is via app() method below
        while (app.isAppRunning()){
            app.getRouter().navigate("/welcome");
        }

    }

    //method called by a class in screens package
    public static AppState app() {return app;}

}