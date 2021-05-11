package com.revature.project0.services;

import com.revature.project0.daos.UserDAO;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterService {

    private BufferedReader consoleReader;
    private UserDAO userDAO;
    private int tries;

    public RegisterService(BufferedReader consoleReader, UserDAO userDAO){

        this.consoleReader = consoleReader;
        this.userDAO = userDAO;

    }

    public String serviceUsername(){

        String username = null;
        tries = 5;

        System.out.print("Enter a username.  Must be 3-20 characters/symbols in length: ");

        try {
            labelW : while (true) {
                username = consoleReader.readLine();

                if(username.equals("e")){
                    return username;
                }

                if (username.length() >= 3 && username.length() <= 20) {
                    if (userDAO.findUserByUsername(username)) {//if username is found in database
                        System.out.println("Username is already taken.");
                        tries--;
                        if (tries <= 0) {
                            //System.out.println("Redirecting to welcome screen...");
                            System.out.println("No tries left.");
                            return null;
                        } else {
                            System.out.println("Enter a different username:");
                        }
                    } else if(username.trim().length() < 3){
                        System.out.print("Invalid username.  Try again.  Tries left: " + tries + "\n : ");
                        tries--;
                    } else {
                        System.out.println("Username " + username + " is available.");
                        break labelW;
                    }

                } else if (tries > 0) {
                    System.out.print("Invalid username.  Try again.  Tries left: " + tries + "\n : ");
                    tries--;
                } else {
                    System.out.print("Invalid username. No tries left.  Redirecting to welcome screen...");
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return username;
    }

    //TODO:trim() for all of these!
    public String servicePassword(){

        System.out.print("Enter a password.  Must be 7-20 characters and/or symbols in length: ");

        String password = null;
        tries = 5;

        try {
            labelW:
            while (true) {
                password = consoleReader.readLine();

                if(password.equals("e")){
                    return password;
                }

                if (password.length() >= 7 && password.length() <= 20) {
                    break labelW;
                } else if (tries > 0) {
                    System.out.print("Invalid password.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid password. No tries left.  Redirecting to welcome screen...");
                    return null;
                }
                tries--;
            }

            tries = 5;//reset

            System.out.print("Enter the password you just entered again: ");
            labelW : while(true) {
                String password2 = consoleReader.readLine();
                if(password2.equals(password)){
                    break labelW;
                } else if(tries > 0){
                    System.out.print("Password doesn't match.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid password. No tries left.  Redirecting to welcome screen...");
                    return null;
                }
                tries--;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        return password;

    }


    public String serviceFirstName(){
        System.out.print("Enter your first name.  Must be 3-20 characters: ");

        String firstName = null;
        tries = 5;

        try {
            labelW:
            while (true) {
                firstName = consoleReader.readLine();

                if(firstName.equals("e")){
                    return firstName;
                }

                if (firstName.length() >= 3 && firstName.length() <= 20) {
                    break labelW;
                } else if (tries > 0) {
                    System.out.print("Invalid name.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid name. No tries left.");
                    return null;
                }
                tries--;
            }
        } catch (Exception e ){
            e.printStackTrace();
            return null;
        }

        return firstName;

    }


    public String serviceLastName(){
        System.out.print("Enter your last name.  Must be 3-20 characters: ");

        String lastName = null;
        tries = 5;

        try {
            labelW:
            while (true) {
                lastName = consoleReader.readLine();

                if(lastName.equals("e")){
                    return lastName;
                }

                if (lastName.length() >= 3 && lastName.length() <= 20) {
                    break labelW;
                } else if (tries > 0) {
                    System.out.print("Invalid name.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid name. No tries left.  Redirecting to welcome screen...");
                    return null;
                }
                tries--;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return lastName;

    }


    public String serviceEmail(){
        System.out.print("Enter your email address: ");

        String email = null;
        tries = 5;

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        try {
            labelW : while(true) {

                email = consoleReader.readLine();

                if(email.equals("e")){
                    return email;
                }

                matcher = pattern.matcher(email);

                if (matcher.matches()) {

                    if (userDAO.findEmailByEmail(email)) {//if email is found in database
                        System.out.println("Email is already taken.");
                        tries--;
                        if (tries <= 0) {
                            System.out.println("No tries left.");
                            return null;
                        } else {
                            System.out.println("Enter a different email:");
                        }
                    } else {
                        System.out.println("Email " + email + " is available.");
                        break labelW;
                    }

                    //break labelW;
                } else if (tries > 0) {
                    System.out.print("Invalid email.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid email. No tries left.");
                    return null;
                }
                tries--;
            }//end while

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return email;

    }


    public String serviceAddress(){
        System.out.print("Enter your address (7-40 characters): ");

        String address = null;
        tries = 5;

        String regex = "[0-9]{3,5} [A-Z a-z]{3,34}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        labelW : while(true) {

            try {
                address = consoleReader.readLine();

                if(address.equals("e")){
                    return address;
                }

                matcher = pattern.matcher(address);

                if (/*address.length() >= 7 && address.length() <= 40*/ matcher.matches()) {
                    break labelW;
                } else if (tries > 0) {
                    System.out.print("Invalid address.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid address. No tries left.  Redirecting to welcome screen...");
                    return null;
                }
                tries--;
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        return address;

    }


    public String serviceCity(){
        System.out.print("Enter the name of your city (3-20 characters): ");

        String city = null;
        tries = 5;

        String regex = "[A-Z a-z]{3,20}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        try {
            labelW:
            while (true) {
                city = consoleReader.readLine();

                if(city.equals("e")){
                    return city;
                }

                matcher = pattern.matcher(city);

                if (/*city.length() >= 3 && city.length() <= 20*/ matcher.matches()) {
                    break labelW;
                } else if (tries > 0) {
                    System.out.print("Invalid city.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid city. No tries left.  Redirecting to welcome screen...");
                    return null;
                }
                tries--;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return city;

    }


    public String serviceState(){
        System.out.print("Enter your State initials (2 capitalized letters only): ");

        String state = null;
        tries = 5;

        String regex = "[A-Z]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        try {
            labelW:
            while (true) {
                state = consoleReader.readLine();

                if(state.equals("e")){
                    return state;
                }

                matcher = pattern.matcher(state);

                if (matcher.matches()) {
                    break labelW;
                } else if (tries > 0) {
                    System.out.print("Invalid State.  Try again.  Tries left: " + tries + "\n : ");
                } else {
                    System.out.print("Invalid State. No tries left.  Redirecting to welcome screen...");
                    return null;
                }
                tries--;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return state;

    }

}
