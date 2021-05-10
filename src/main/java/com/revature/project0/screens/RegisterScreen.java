package com.revature.project0.screens;

import com.revature.project0.daos.AccountDAO;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ScreenRouter;

import java.io.BufferedReader;

import static com.revature.project0.Driver.app;

public class RegisterScreen extends Screen {


    private UserDAO userDAO;
    private AccountDAO accountDAO = new AccountDAO();//TODO: cleanup this gross thing
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserDAO userDAO){
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
    }


    public void render() {

        String username;//PK for user, FK for account
        String password;
        String firstName;
        String lastName;
        String email;
        String address;
        String city;
        String state;
        //String phone;
        //int age;
        //String zipcode;
        String accountID;//PK for account, FK for user

        //username and accountID must be unique

        try {
            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            //System.out.print("Age: ");
            //age = consoleReader.readLine();

            //TODO: ensure this is unique, and at least 3 char
            System.out.print("Username: ");
            username = consoleReader.readLine();

            //TODO: ensure at least 7 char, and repeat this to ensure user uses correct password
            System.out.print("Password: ");
            password = consoleReader.readLine();

            //TODO: ensure at least 3 char
            System.out.print("First name: ");
            firstName = consoleReader.readLine();

            //TODO: ensure at least 3 char
            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            //TODO: ensure at least 1 char before an @ char
            System.out.print("Email: ");
            email = consoleReader.readLine();

            //TODO: ensure first 3 char are digits
            System.out.print("Address: ");
            address = consoleReader.readLine();

            System.out.print("City: ");
            city = consoleReader.readLine();

            //TODO: ensure 2 char
            System.out.print("State (2 letters only): ");
            state = consoleReader.readLine();

            //TODO: ensure 5 digits
            //System.out.print("Zipcode: ");
            //zipcode = consoleReader.readLine();

            //TODO: ensure proper format
            //System.out.print("Phone: ");
            //phone = consoleReader.readLine();

            AppUser newUser = new AppUser(username, password, firstName, lastName, email,
                    address, city, state/*, zipcode, phone*/);

            System.out.println("Created user: " + newUser);

            userDAO.save(newUser);

            /*

            System.out.println("What type of account do you want?");
            System.out.println("1) Checking");
            System.out.println("2) Savings");

            String userSelection = consoleReader.readLine();

            createCheckingOrSavings(newUser, userSelection);

            */

            //Once user is created, can login with that username and password.
            //If no account is detected after login, will create one at login screen

        } catch (Exception e){
            e.printStackTrace();
        }

    }



    //TODO: move this to AccountDAO
    private boolean createCheckingOrSavings(AppUser appUser, String userSelection){

        switch (userSelection) {
            case "1":
                System.out.println("Creating Checking account...");
                accountDAO.createAccount(appUser, "checking");
                break;
            case "2":
                System.out.println("Creating Savings account...");
                accountDAO.createAccount(appUser, "savings");
                break;
        }

        return false;
    }

}
