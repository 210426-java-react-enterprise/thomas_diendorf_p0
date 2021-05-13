package com.revature.project0.services;

import com.revature.project0.daos.UserDAO;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used primarily in conjunction with RegisterScreen class.  Verifies formats of anything
 * user would input while on the Register Screen, e.g. username, password, email, address, etc.
 */
public class RegisterService {

    private BufferedReader consoleReader;
    private UserDAO userDAO;

    /**
     *
     * @param consoleReader for user input
     * @param userDAO for uploading user information to database
     */
    public RegisterService(BufferedReader consoleReader, UserDAO userDAO){

        this.consoleReader = consoleReader;
        this.userDAO = userDAO;

    }


    /**
     * Verifies inputted username is a valid format.
     *
     * @param username that user has chosen
     * @return string of username; null if not valid, or "e" if user wishes to exit the screen
     */
    public String serviceUsername(String username) {

        username = username.trim();

        if (username.equals("e")) {
            return username;
        }

        if (username.length() >= 3 && username.length() <= 20) {
            if (userDAO.findUserByUsername(username)) {//if username is found in database
                System.out.println("Username is already taken.");
                return null;
            } else {
                System.out.println("Username " + username + " is available.");
                return username;
            }
        }

        return null;

    }


    /**
     * Verifies inputted password is a valid format.
     *
     * @param password that user has chosen
     * @return string of the password; null if not valid, or "e" if user wishes to exit the screen
     */
    public String servicePassword(String password){

        password = password.trim();

        if(password.equals("e")){
            return password;
        }

        if (password.length() >= 7 && password.length() <= 20) {
            return password;
        }

        return null;

    }


    /**
     * Verifies inputted first name is a valid format.
     *
     * @param firstName that user has chosen
     * @return string of first name; null if not valid, or "e" if user wishes to exit the screen
     */
    public String serviceFirstName(String firstName){

        firstName = firstName.trim();

        String regex = "^[A-Za-z]{3,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        if(firstName.equals("e")){
            return firstName;
        }

        matcher = pattern.matcher(firstName);

        if (matcher.matches()) {
            return firstName;
        }

        return null;

    }


    /**
     * Verifies inputted last name is a valid format.
     *
     * @param lastName that user has chosen
     * @return string of lastName; null if not valid, or "e" if user wishes to exit the screen
     */
    public String serviceLastName(String lastName){

        lastName = lastName.trim();

        String regex = "^[A-Za-z]{3,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        if(lastName.equals("e")){
            return lastName;
        }

        matcher = pattern.matcher(lastName);

        if (matcher.matches()) {
            return lastName;
        }

        return null;

    }


    /**
     * Verifies inputted email is a valid format.
     *
     * @param email that user has chosen
     * @return string of email; null if not valid, or "e" if user wishes to exit the screen
     */
    public String serviceEmail(String email){

        email = email.trim();

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;


        if(email.equals("e")){
            return email;
        }

        matcher = pattern.matcher(email);

        if (matcher.matches()) {
            if (userDAO.findEmailByEmail(email)) {//if email is found in database
                System.out.println("Email is already taken.");
                return null;
            } else {
                System.out.println("Email " + email + " is available.");
                return email;
            }
        }

        return null;
    }


    /**
     * Verifies inputted address is a valid format.
     *
     * @param address that user has chosen
     * @return string of address; null if not valid, or "e" if user wishes to exit the screen
     */
    public String serviceAddress(String address){

        address = address.trim();

        String regex = "^[0-9]{3,5} [A-Z a-z]{3,35}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        if(address.equals("e")){
            return address;
        }

        matcher = pattern.matcher(address);

        if ( matcher.matches()) {
            return address;
        }

        return null;

    }


    /**
     * Verifies inputted city is a valid format.
     *
     * @param city that user has chosen
     * @return string of city; null if not valid, or "e" if user wishes to exit the screen
     */
    public String serviceCity(String city){

        city = city.trim();

        String regex = "^[A-Z a-z]{3,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        if(city.equals("e")){
            return city;
        }

        matcher = pattern.matcher(city);

        if (matcher.matches()) {
            return city;
        }

        return null;

    }


    /**
     * Verifies inputted state is a valid format.  Must only be 2 capitalized letters.
     *
     * @param state that user has chosen
     * @return string of state; null if not valid, or "e" if user wishes to exit the screen
     */
    public String serviceState(String state){

        state = state.trim();

        String regex = "^[A-Z]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        if(state.equals("e")){
            return state;
        }

        matcher = pattern.matcher(state);

        if (matcher.matches()) {
            return state;
        }

        return null;

    }

}
