package com.revature.project0.screens;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.AppUserInfo;
import com.revature.project0.util.ScreenRouter;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.revature.project0.Driver.app;

public class RegisterScreen extends Screen {

    private UserDAO userDAO;
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private AppUserInfo appUserInfo;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, AppUserInfo appUserInfo, UserDAO userDAO){
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
        this.appUserInfo = appUserInfo;
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
            int tries = 5;
            //System.out.print("Age: ");
            //age = consoleReader.readLine();


            System.out.print("Enter a username.  Must be 3-20 characters/symbols in length: ");
            labelW : while(true) {
                username = consoleReader.readLine();
                if(username.length() >= 3 && username.length() <= 20){
                    if(userDAO.findUserByUsername(username)){//if username is found in database
                        System.out.println("Username is already taken.");
                        tries--;
                        if(tries <= 0){
                            System.out.println("Redirecting to welcome screen...");
                            return;
                        } else {
                            System.out.println("Enter a different username:");
                        }
                    }else {
                        System.out.println("Username " + username + " is available.");
                        break labelW;
                    }

                } else if(tries > 0){
                    System.out.print("Invalid username.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid username. No tries left.  Redirecting to welcome screen...");
                    return;
                }
                //tries--;
            }
            tries = 5;

            System.out.print("Enter a password.  Must be 7-20 characters and/or symbols in length: ");
            labelW : while(true) {
                password = consoleReader.readLine();
                if(password.length() >= 7 && password.length() <= 20){
                    break labelW;
                } else if(tries > 0){
                    System.out.print("Invalid password.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid password. No tries left.  Redirecting to welcome screen...");
                    return;
                }
                tries--;
            }
            tries = 5;

            System.out.print("Enter the password you just entered again: ");
            labelW : while(true) {
                String password2 = consoleReader.readLine();
                if(password2.equals(password)){
                    break labelW;
                } else if(tries > 0){
                    System.out.print("Password doesn't match.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid password. No tries left.  Redirecting to welcome screen...");
                    return;
                }
                tries--;
            }
            tries = 5;


            System.out.print("Enter your first name.  Must be 3-20 characters: ");
            labelW : while(true) {
                firstName = consoleReader.readLine();
                if(firstName.length() >= 3 && firstName.length() <= 20){
                    break labelW;
                } else if(tries > 0){
                    System.out.print("Invalid name.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid name. No tries left.  Redirecting to welcome screen...");
                    return;
                }
                tries--;
            }
            tries = 5;


            System.out.print("Enter your last name.  Must be 3-20 characters: ");
            labelW : while(true) {
                lastName = consoleReader.readLine();
                if(lastName.length() >= 3 && lastName.length() <= 20){
                    break labelW;
                } else if(tries > 0){
                    System.out.print("Invalid name.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid name. No tries left.  Redirecting to welcome screen...");
                    return;
                }
                tries--;
            }
            tries = 5;


            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher;
            System.out.print("Email: ");
            labelW : while(true) {
                email = consoleReader.readLine();
                matcher = pattern.matcher(email);

                if(matcher.matches()){
                    break labelW;
                } else if(tries > 0){
                    System.out.print("Invalid email.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid email. No tries left.  Redirecting to welcome screen...");
                    return;
                }
                tries--;
            }
            tries = 5;


            regex = "[0-9]{3,5} [A-Z a-z]{3,34}";
            pattern = Pattern.compile(regex);
            System.out.print("Enter your address (7-40 characters): ");
            labelW : while(true) {
                address = consoleReader.readLine();
                matcher = pattern.matcher(address);

                if(/*address.length() >= 7 && address.length() <= 40*/ matcher.matches()){
                    break labelW;
                } else if(tries > 0){
                    System.out.print("Invalid address.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid address. No tries left.  Redirecting to welcome screen...");
                    return;
                }
                tries--;
            }
            tries = 5;

            regex = "[A-Z a-z]{3,20}";
            pattern = Pattern.compile(regex);
            System.out.print("Enter the name of your city (3-20 characters): ");
            labelW : while(true) {
                city = consoleReader.readLine();
                matcher = pattern.matcher(city);

                if(/*city.length() >= 3 && city.length() <= 20*/ matcher.matches()){
                    break labelW;
                } else if(tries > 0){
                    System.out.print("Invalid city.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid city. No tries left.  Redirecting to welcome screen...");
                    return;
                }
                tries--;
            }
            tries = 5;


            regex = "[A-Z]{2}";
            pattern = Pattern.compile(regex);
            System.out.print("Enter your State initials (2 capitalized letters only): ");
            labelW : while(true) {
                state = consoleReader.readLine();
                matcher = pattern.matcher(state);

                if(matcher.matches()){
                    break labelW;
                } else if(tries > 0){
                    System.out.print("Invalid State.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid State. No tries left.  Redirecting to welcome screen...");
                    return;
                }
                tries--;
            }
            tries = 5;


            //System.out.print("Zipcode: ");
            //zipcode = consoleReader.readLine();


            //System.out.print("Phone: ");
            //phone = consoleReader.readLine();

            AppUser newUser = new AppUser(username, password, firstName, lastName, email,
                    address, city, state/*, zipcode, phone*/);




            userDAO.save(newUser);

            //Once user is created, can login with that username and password.
            //If no account is detected after login, will create one at login screen

        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
