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


        System.out.println("Register for a new account!");
        System.out.println("+-------------------------+");

        System.out.println("Enter 'e' at any time to quit registration.");


        username = registerService.serviceUsername();
        if(username == null){
            System.out.print("Invalid username. Redirecting to welcome screen...");
            return;
        } else if (username.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }

        password = registerService.servicePassword();
        if(password == null){
            System.out.print("Invalid password. Redirecting to welcome screen...");
            return;
        } else if (password.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }


        firstName = registerService.serviceFirstName();
        if(firstName == null){
            System.out.print("Invalid first name. Redirecting to welcome screen...");
            return;
        } else if (firstName.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }


        lastName = registerService.serviceLastName();
        if(lastName == null){
            System.out.print("Invalid last name. Redirecting to welcome screen...");
            return;
        } else if (lastName.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }


        email = registerService.serviceEmail();
        if(email == null){
            System.out.print("Invalid email. Redirecting to welcome screen...");
            return;
        } else if (email.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }


        address = registerService.serviceAddress();
        if(address == null){
            System.out.print("Invalid address. Redirecting to welcome screen...");
            return;
        } else if (address.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }


        city = registerService.serviceCity();
        if(city == null){
            System.out.print("Invalid city. Redirecting to welcome screen...");
            return;
        } else if (city.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }


        state = registerService.serviceState();
        if(state == null){
            System.out.print("Invalid state. Redirecting to welcome screen...");
            return;
        } else if (state.equals("e")){
            System.out.print("Redirecting to welcome screen...");
            return;
        }


        //System.out.print("Zipcode: ");
        //zipcode = consoleReader.readLine();


        //System.out.print("Phone: ");
        //phone = consoleReader.readLine();

        AppUser newUser = new AppUser(username, password, firstName, lastName, email,
                address, city, state/*, zipcode, phone*/);

        userDAO.save(newUser);

        //Once user is created, can login with that username and password.
        //If no account is detected after login, will create one at login screen

        router.navigate("/login");

    }


}
