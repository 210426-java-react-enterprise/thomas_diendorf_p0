package com.revature.project0.daos;

import com.revature.project0.models.AppAccount;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ConnectionFactory;
import org.postgresql.util.PSQLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * Saves AppAccount data to SQL table bank_account.
 *
 * This includes username, account_type, balance, etc.
 *
 */
public class AccountDAO {

    File file;
    PrintStream printStream;

    public AccountDAO(){
        file = new File("exceptionLog.txt");//TODO: gross

        try {
            printStream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(printStream);
        }
    }


    /**
     * Creates financial account for user in database.  Requires a personal account to exist.
     * @param user AppUser
     * @param accountType String, "checking" or "savings"
     */
    public void createAccount(AppUser user, String accountType){

        assert (user != null);
        assert (accountType != null);


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Creating checking account...");

            String sql = "insert into bank_account (username, account_type, balance)" +
                    " values(?, ?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, accountType);
            pstmt.setString(3, "$0.00");

            pstmt.executeUpdate();

            System.out.println("Account created!");

        } catch(SQLException e) {
            e.printStackTrace(printStream);
        }
    }


    /**
     * Locates user's financial account in the database, primarily for purposes
     * of ensuring it exists for the sake of either making a new one, or
     * for updating it or getting the balance.
     *
     * @param username String
     * @return instance of user's AppAccount.
     */
    public AppAccount findAccountByUsername(String username){

        AppAccount account = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from bank_account where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                account = new AppAccount();
                account.setAccountID(rs.getString("account_id"));
                account.setAccountOwner(rs.getString("username"));
                account.setAccountType(rs.getString("account_type"));
                account.setDateCreated(rs.getDate("date_created"));
                account.setBalance(stringCurrencyToDouble(rs.getString("balance")));
            }
        } catch(SQLException e){
            e.printStackTrace(printStream);
        }

        return account;
    }


    /**
     * Deposits USD into user's account, converting inputted double
     * into a proper string format, with a '$' and commas.
     *
     * @param username String
     * @param amount double
     * @return instance of user's AppAccount.
     */
    public AppAccount makeDeposit(String username, double amount){

        AppAccount account = findAccountByUsername(username);

        if(amount <= 0){
            System.out.println("Invalid amount!");
            return account;
        } else if(amount > 99999999.99){
            System.out.println("The amount is too high!");
            return account;
        }

        double currentBalance = 0;

        try {
            if (account != null) {//throw nullpointerexception if false
                currentBalance = account.getBalance();
            }
        } catch (NullPointerException e){
            System.out.println("Error in making withdrawal!");
            e.printStackTrace(printStream);
            return account;
        }

        currentBalance = currentBalance + amount;

        if(currentBalance > 99999999.99){
            System.out.println("Your account cannot hold this amount!");
            return account;
        }

        String newBalance = doubleToStringCurrency(currentBalance);

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "update bank_account " +
                    "set balance = ? " +
                    "where username = ?;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newBalance);
            pstmt.setString(2, username);

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace(printStream);
        }

        account = findAccountByUsername(username);

        return account;

    }


    /**
     * Withdraws USD from user's account.
     *
     * @param username String
     * @param amount double
     * @return instance of user's AppAccount.
     */
    public AppAccount makeWithdrawal(String username, double amount){

        AppAccount account = findAccountByUsername(username);

        double currentBalance;

        try {
            if (account.getBalance() < amount) {
                System.out.println("You do not possess enough funds in this account to make that withdrawal!");
                return account;
            } else {
                currentBalance = account.getBalance();
            }
        } catch (NullPointerException e){
            System.out.println("Error in making withdrawal!");
            e.printStackTrace(printStream);
            return account;
        }

        amount *= -1;

        currentBalance = currentBalance + amount;

        String newBalance = doubleToStringCurrency(currentBalance);

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "update bank_account " +
                    "set balance = ? " +
                    "where username = ?;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newBalance);
            pstmt.setString(2, username);

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace(printStream);
        }

        account = findAccountByUsername(username);

        System.out.println("Successfully withdrew " + doubleToStringCurrency(amount));
        return account;

    }


    /**
     * Deletes user's account from database.
     *
     * @param username String
     * @return boolean: true if successfully deleted, false if it failed.
     */
    public boolean removeUserAccount(String username){

        //first check to see if balance is 0
        //if it isn't, state they must withdraw funds until no balance is left
        AppAccount account = findAccountByUsername(username);
        if(account.getBalance() > 0){
            System.out.println("You must withdraw all funds from your account before you can close it!");
            return false;
        }


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "delete from bank_account where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            pstmt.executeQuery();

            System.out.println("Deleting account...");

        } catch (PSQLException e) {
            System.out.println("Deleting account...");
            e.printStackTrace(printStream);
            return true;//this thrown exception indicates no results returned by the query, indicating deletion
        } catch(SQLException e){
            System.out.println("There was a problem trying to close your account!");
            e.printStackTrace(printStream);
            return false;
        }

        //System.out.println("Account successfully removed!");
        return true;
    }



    //TODO: put 2 methods below into a new utility class for converting strings and doubles adhering to USD currency

    /**
     * Converts a double into a String for USD currency conversion.
     *
     * @param dValue double
     * @return same value as inputted double but in String format
     */
    public String doubleToStringCurrency(double dValue){
        String sValue = NumberFormat.getCurrencyInstance(Locale.US).format(dValue);
        return sValue;
    }


    /**
     * Converts a String into a double for USD currency conversion.
     *
     * @param sValue String
     * @return same value as inputted String but in double format.
     */
    public double stringCurrencyToDouble(String sValue){

        double dValue = 0;
        String noCommas = "";

        noCommas = sValue.replace("$", "");//in case a '$' is in front of the string
        noCommas = noCommas.replaceAll(",", "");//in case large number with commas is passed

        dValue = Double.parseDouble(noCommas);

        return dValue;

    }


}
