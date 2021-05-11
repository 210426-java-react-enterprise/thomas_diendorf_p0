package com.revature.project0.services;

import com.revature.project0.daos.UserDAO;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterService {

    private BufferedReader consoleReader;
    private UserDAO userDAO;
    //private int tries;

    public RegisterService(BufferedReader consoleReader, UserDAO userDAO){

        this.consoleReader = consoleReader;
        this.userDAO = userDAO;

    }

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



    public String serviceFirstName(String firstName){

        firstName = firstName.trim();

        if(firstName.equals("e")){
            return firstName;
        }

        if (firstName.length() >= 3 && firstName.length() <= 20) {
            return firstName;
        }

        return null;

    }



    public String serviceLastName(String lastName){

        lastName = lastName.trim();

        if(lastName.equals("e")){
            return lastName;
        }

        if (lastName.length() >= 3 && lastName.length() <= 20) {
            return lastName;
        }

        return null;

    }



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



    public String serviceAddress(String address){

        address = address.trim();

        String regex = "[0-9]{3,5} [A-Z a-z]{3,34}";
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



    public String serviceCity(String city){

        city = city.trim();

        String regex = "[A-Z a-z]{3,20}";
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


    public String serviceState(String state){

        state = state.trim();

        String regex = "[A-Z]{2}";
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
