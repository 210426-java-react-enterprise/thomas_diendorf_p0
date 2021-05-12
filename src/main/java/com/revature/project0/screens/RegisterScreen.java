package com.revature.project0.screens;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.services.RegisterService;
import com.revature.project0.util.AppUserInfo;
import com.revature.project0.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private UserDAO userDAO;
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private AppUserInfo appUserInfo;

    private RegisterService registerService;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, AppUserInfo appUserInfo, UserDAO userDAO){
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
        this.appUserInfo = appUserInfo;

        this.registerService = new RegisterService(consoleReader, userDAO);
    }


    public void render() {

        String username = null;//PK for user, FK for account
        String password = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String address = null;
        String city = null;
        String state = null;
        //String phone;
        //int age;
        //String zipcode;
        String accountID;//PK for account, FK for user

        //username and accountID must be unique


        System.out.println("Register for a new account!");
        System.out.println("+-------------------------+");

        System.out.println("Enter 'e' at any time to quit registration.");



        int tries = 6;

        try {
            while (username == null && tries > 0) {
                tries--;
                if(tries == 5) {
                    System.out.print("Enter a username.  Must be 3-20 characters/symbols in length: ");
                } else {
                    System.out.print("You have " + tries + " tries left: ");
                }
                username = consoleReader.readLine();
                registerService.serviceUsername(username);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        if(username == null || tries <= 0){
            System.out.print("Invalid username. Redirecting to welcome screen...");
            return;
        } else if (username.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }




        tries = 6;
        try {
            while (password == null && tries > 0) {
                tries--;
                if(tries == 5) {
                    System.out.print("Enter a password.  Must be 7-20 characters and/or symbols in length: ");
                } else {
                    System.out.print("You have " + tries + " tries left: ");
                }
                password = consoleReader.readLine();
                password = registerService.servicePassword(password);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        if(password == null){
            System.out.print("Invalid password. Redirecting to welcome screen...");
            return;
        } else if (password.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        } else {
            System.out.print("Enter the password you just entered again: ");
            tries = 5;
            String password2 = null;
            try {
                labelW : while (true) {
                    password2 = consoleReader.readLine();
                    password2 = registerService.servicePassword(password2);
                    if (password2.equals(password)) {
                        break labelW;
                    } else if (tries > 0) {
                        System.out.print("Password doesn't match.  Try again.  Tries left: " + tries + "\n : ");
                    } else {
                        System.out.print("Invalid password. No tries left.  Redirecting to welcome screen...");
                        return;
                    }
                    tries--;
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }




        tries = 6;
        try {
            while (tries > 0 && firstName == null) {
                tries--;
                if(tries == 5) {
                    System.out.print("Enter your first name.  Must be 3-20 characters: ");
                } else{
                    System.out.print("Invalid first name. You have " + tries + " tries left.");
                    System.out.print("Enter your first name.  Must be 3-20 characters: ");
                }
                firstName = consoleReader.readLine();
                firstName = registerService.serviceFirstName(firstName);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        if(firstName == null){
            System.out.print("Invalid first name. Redirecting to welcome screen...");
            return;
        } else if (firstName.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }




        tries = 6;
        try {
            while(tries > 0 && lastName == null) {
                tries--;
                if (tries == 5) {
                    System.out.print("Enter your last name.  Must be 3-20 characters: ");
                } else {
                    System.out.print("Invalid last name.  You have " + tries + " left.");
                    System.out.print("Enter your last name.  Must be 3-20 characters: ");
                }
                lastName = consoleReader.readLine();
                lastName = registerService.serviceLastName(lastName);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        if(lastName == null){
            System.out.print("Invalid last name. Redirecting to welcome screen...");
            return;
        } else if (lastName.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }




        tries = 6;
        try {
            while(tries > 0 && email == null) {
                tries--;
                if(tries == 5) {
                    System.out.print("Enter your email address: ");
                } else{
                    System.out.print("Invalid email.  You have " + tries + " left.");
                    System.out.print("Enter your email: ");
                }
                email = consoleReader.readLine();
                email = registerService.serviceEmail(email);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        if(email == null){
            System.out.print("Invalid email. Redirecting to welcome screen...");
            return;
        } else if (email.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }




        tries = 6;
        try {
            while(tries > 0 && address == null) {
                tries--;
                if (tries == 5) {
                    System.out.print("Enter your address (7-40 characters): ");
                } else {
                    System.out.print("Invalid address.  You have " + tries + " tries left.");
                    System.out.print("Enter your address (7-40 characters): ");
                }
                address = consoleReader.readLine();
                address = registerService.serviceAddress(address);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        if(address == null){
            System.out.print("Invalid address. Redirecting to welcome screen...");
            return;
        } else if (address.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }




        tries = 6;
        try {
            while(tries > 0 && city == null) {
                tries--;
                if (tries == 5) {
                    System.out.print("Enter the name of your city (3-20 characters): ");
                } else {
                    System.out.print("Invalid city.  You have " + tries + "tries left.");
                    System.out.print("Enter the name of your city (3-20 characters): ");
                }
                city = consoleReader.readLine();
                city = registerService.serviceCity(city);
            }
        } catch(Exception e){
            e.printStackTrace();
            System.out.print("Redirecting to welcome screen...");
            return;
        }
        if(city == null){
            System.out.print("Invalid city. Redirecting to welcome screen...");
            return;
        } else if (city.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }




        tries = 6;
        try {
            while (tries > 0 && state == null) {
                tries--;
                if(tries == 5) {
                    System.out.print("Enter your State initials (2 capitalized letters only): ");
                }else{
                    System.out.print("Invalid state.  You have " + tries + " tries remaining.");
                    System.out.print("Enter your State initials (2 capitalized letters only): ");
                }
                state = consoleReader.readLine();
                state = registerService.serviceState(state);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(state == null){
            System.out.print("Invalid state. Redirecting to welcome screen...");
            return;
        } else if (state.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }



        AppUser newUser = new AppUser(username, password, firstName, lastName, email,
                address, city, state/*, zipcode, phone*/);

        if(userDAO.save(newUser)){
            router.navigate("/login");
        }
        //else go back to welcome screen


        //Once user is created, can login with that username and password.
        //If no account is detected after login, will create one at login screen



    }


}
