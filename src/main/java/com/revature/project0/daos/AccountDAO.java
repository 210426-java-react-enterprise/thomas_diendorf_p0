package com.revature.project0.daos;

import com.revature.project0.models.AppAccount;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ConnectionFactory;

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
            pstmt.setDouble(3, 0.00);

            pstmt.executeUpdate();

            System.out.println("Account created!");

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public AppAccount findAccountByUsernameAndAccountType(String username, String accountType){

        AppAccount account = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from bank_account where username = ? and account_type = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, accountType);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                account = new AppAccount();
                account.setAccountID(rs.getString("account_id"));
                account.setAccountOwner(rs.getString("username"));
                account.setAccountType(rs.getString("account_type"));
                account.setDateCreated(rs.getDate("date_created"));
                account.setBalance(rs.getDouble("balance"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return account;
    }


    //TODO: temporary until allowing for multiple accounts
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
                account.setBalance(rs.getDouble("balance"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return account;
    }




}
