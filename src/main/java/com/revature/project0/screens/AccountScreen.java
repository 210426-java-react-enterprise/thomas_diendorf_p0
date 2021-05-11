package com.revature.project0.screens;

import com.revature.project0.daos.AccountDAO;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppAccount;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.AppUserInfo;
import com.revature.project0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class AccountScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private AppUserInfo appUserInfo;
    private UserDAO userDAO;
    private AccountDAO accountDAO;
    private AppAccount appAccount;//for use when needing username for account cross-reference

    public AccountScreen(BufferedReader consoleReader, ScreenRouter router, AppUserInfo appUserInfo,
                         UserDAO userDAO, AccountDAO accountDAO){
        super("AccountScreen", "/account");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        this.appUserInfo = appUserInfo;//stores username and password for access
        this.appAccount = null;
    }


    public void render(){

        AppUser appUser = userDAO.findUserByUsernameAndPassword(appUserInfo.getCurrentUser(),
                appUserInfo.getCurrentUserPassword());

        String username = appUser.getUsername();
        boolean hasAccount = true;
        appAccount = null;//just in case

        try {

            appAccount = accountDAO.findAccountByUsername(username);

            if(appAccount != null && appAccount.getAccountOwner().equals(username)){
                hasAccount = true;
            } else {
                hasAccount = false;
            }

        } catch (NullPointerException e) {
            hasAccount = false;
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }



        if(hasAccount) {
            System.out.println("Welcome to your account " + appUser.getUsername() + ".");
            System.out.println("What do you wish to do?.");
            System.out.println("1) View account balance");
            System.out.println("2) Make a deposit");
            System.out.println("3) Make a withdrawal");
            System.out.println("4) Delete your bank account");
            System.out.println("5) Delete bank and user account");
            System.out.println("6) Exit");

            try {

                String userSelection = consoleReader.readLine();

                double amount;
                int tries = 5;

                whileL : while(tries > 0) {
                    switch (userSelection) {
                        case "1"://balance
                            getBalance(appAccount.getAccountOwner());
                            tries = 5;
                            break;

                        case "2"://deposit
                            System.out.println("How much do you wish to deposit?");
                            System.out.print("$");
                            amount = Double.parseDouble(consoleReader.readLine());//TODO: make sure this value is valid
                            makeDeposit(amount);
                            tries = 5;
                            break;

                        case "3"://withdraw
                            System.out.println("How much do you wish to withdraw?");
                            System.out.print("$");
                            amount = Double.parseDouble(consoleReader.readLine());//TODO: make sure this value is valid
                            makeWithdrawal(amount);
                            tries = 5;
                            break;

                        case "4"://close account
                            System.out.println("Are you sure you want to close your account?");
                            System.out.println("1) I'm sure.");
                            System.out.println("2) On second thought, maybe not.");

                            String userSelection2 = consoleReader.readLine();
                            if (userSelection2.equals("1")) {
                                if( closeAccount() ){
                                    System.out.println("Your bank account is deleted!");
                                    break whileL;
                                } else {
                                    System.out.println("Your account failed to close!");
                                    tries = 5;
                                    break;
                                }
                            } else if (userSelection2.equals("2")) {
                                System.out.println("Your account remains open.");
                                tries = 5;
                                break;
                            } else {
                                System.out.println("Invalid selection.  Returning to main selection.");
                                tries = 5;
                                break;
                            }

                        case "5"://close account and delete user
                            System.out.println("Are you sure you want to close your bank account and delete your user account?");
                            System.out.println("1) I'm sure.");
                            System.out.println("2) On second thought, maybe not.");

                            String userSelection3 = consoleReader.readLine();
                            if (userSelection3.equals("1")) {
                                if( closeAccountAndDeleteUser(username) ){
                                    System.out.println("Your bank account and user account are deleted!");
                                    break whileL;
                                } else {
                                    System.out.println("Your user account failed to delete!");
                                    tries = 5;
                                    break;
                                }
                            } else if (userSelection3.equals("2")) {
                                System.out.println("Your bank account and user account remain open.");
                                tries = 5;
                                break;
                            } else {
                                System.out.println("Invalid selection.  Returning to main selection.");
                                tries = 5;
                                break;
                            }

                        case "6"://exit
                            System.out.println("Exiting account...");
                            break whileL;
                    }

                    System.out.println("What else do you wish to do?");
                    tries--;

                    System.out.println("1) View account balance");
                    System.out.println("2) Make a deposit");
                    System.out.println("3) Make a withdrawal");
                    System.out.println("4) Delete your bank account");
                    System.out.println("5) Exit");
                    userSelection = consoleReader.readLine();
                }
            } catch (IOException e) {
                System.out.println("Invalid input!");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please make an account " + appUser.getUsername() + "!");

            String checkOrSave = null;
            String userSelection;
            boolean willMakeAccount = true;



            try {
                int tries = 5;
                //userSelection = consoleReader.readLine();

                whileL : while(tries > 0) {
                    System.out.println("1) Create checking account");
                    System.out.println("2) Create savings account");
                    userSelection = consoleReader.readLine();

                    switch (userSelection) {
                        case "1":
                            checkOrSave = "checking";
                            break whileL;
                        case "2":
                            checkOrSave = "savings";
                            break whileL;
                        default:
                            System.out.println("Invalid selection.  Try again.");
                            tries--;
                    }
                }

                if(checkOrSave == null){
                    System.out.println("There was a problem creating your account.  Redirecting...");
                    willMakeAccount = false;
                }


            } catch (IOException e) {
                willMakeAccount = false;
                System.out.println("There was a problem creating your account.  Redirecting...");
                e.printStackTrace();
            }

            if(willMakeAccount) {
                accountDAO.createAccount(appUser, checkOrSave);
            }
        }

    }

    private void getBalance(String username){
        double balance = 0;

        System.out.println("Getting the balance in your account...");

        try {

            appAccount = accountDAO.findAccountByUsername(username);

            balance = appAccount.getBalance();

        } catch (NullPointerException e){
            System.out.println("There was an issue in acquiring the balance to your account.");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("You current balance is " + String.format("$%.2f", balance));

    }


    private void makeDeposit(double amount){
        if(amount <= 0){
            System.out.println("Invalid value!");
            return;
        }

        try {

            appAccount = accountDAO.makeDeposit(appAccount.getAccountOwner(), amount);

            if(appAccount != null) {
                System.out.println("You successfully deposited " + String.format("$%.2f", amount));
                System.out.println("Your current balance is " + String.format("$%.2f", (appAccount.getBalance())));
            }

        } catch (NullPointerException e){
            System.out.println("There was an error in making your deposit!");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    private void makeWithdrawal(double amount){
        if(amount <= 0){
            System.out.println("Invalid value!");
            return;
        }

        try {
            double initialBalance = appAccount.getBalance();
            appAccount = accountDAO.makeWithdrawal(appAccount.getAccountOwner(), amount);

        } catch (NullPointerException e){
            System.out.println("There was an error in making your withdrawal!");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    private boolean closeAccount(){

        return accountDAO.removeUserAccount(appAccount.getAccountOwner());

    }


    private boolean closeAccountAndDeleteUser(String username){

        return userDAO.removeUserWithAccount(username, accountDAO);

    }


}
