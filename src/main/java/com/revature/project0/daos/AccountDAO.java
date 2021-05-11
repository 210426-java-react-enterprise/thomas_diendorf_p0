package com.revature.project0.daos;

import com.revature.project0.models.AppAccount;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ConnectionFactory;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Saves AppAccount data to SQL table bank_account.
 *
 * This includes username, account_type, balance, etc.
 *
 */
public class AccountDAO {

    //relies on having an account, and a user for the account
    public void save (AppUser appUser, AppAccount appAccount){

        System.out.println("Connecting to SQL database...");

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            System.out.println("Uploading created account information into database...");

            String sql = "insert into bank_account (username, account_type, balance)" +
                    " values(?, ?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, appAccount.getAccountOwner());
            pstmt.setString(2, appAccount.getAccountType());
            pstmt.setDouble(3, appAccount.getBalance());

            pstmt.executeUpdate();

            System.out.println("Upload complete!");

        } catch (SQLException e){
            e.printStackTrace();
        }

    }


    public void createAccount(AppUser user, String accountType){
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Creating checking account...");

            String sql = "insert into bank_account (username, account_type, balance)" +
                    " values(?, ?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, accountType);
            //pstmt.setDouble(3, 0.00);
            pstmt.setString(3, "$0.00");

            pstmt.executeUpdate();

            System.out.println("Account created!");

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }



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
                //account.setBalance(rs.getDouble("balance"));

                //some string to double conversion
                StringBuilder builder = new StringBuilder(rs.getString("balance"));
                builder.delete(0,1);//remove '$'
                double balance = Double.valueOf(builder.toString());
                account.setBalance(balance);

            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return account;
    }



    public AppAccount makeDeposit(String username, double amount){

        AppAccount account = findAccountByUsername(username);

        double currentBalance = 0;

        try {
            if (account != null) {//throw nullpointerexception if false
                currentBalance = account.getBalance();
            }
        } catch (NullPointerException e){
            System.out.println("Error in making withdrawal!");
            e.printStackTrace();
            return account;
        }

        double setBalanceTo = currentBalance + amount;

        String newBalance = "$" + String.valueOf(setBalanceTo);

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "update bank_account " +
                    "set balance = ? " +
                    "where username = ?;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newBalance);
            pstmt.setString(2, username);

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }

        account = findAccountByUsername(username);

        return account;

    }


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
            e.printStackTrace();
            return account;
        }

        amount *= -1;

        double setBalanceTo = currentBalance + amount;

        String newBalance = String.format("$%.2f", setBalanceTo);

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "update bank_account " +
                    "set balance = ? " +
                    "where username = ?;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newBalance);
            pstmt.setString(2, username);

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }

        account = findAccountByUsername(username);

        System.out.println("Successfully withdrew " + String.format("$%.2f", amount * -1));
        return account;

    }




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
            return true;//this thrown exception indicates no results returned by the query, indicating deletion
        } catch(SQLException e){
            System.out.println("There was a problem trying to close your account!");
            e.printStackTrace();
            return false;
        }

        //System.out.println("Account successfully removed!");
        return true;
    }






}
