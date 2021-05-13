package com.revature.project0.daos;

import com.revature.project0.models.AppUser;
import com.revature.project0.util.ConnectionFactory;
import org.postgresql.util.PSQLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.*;

/**
 * Updates and creates user's personal information on database.
 * Can also delete account from database, including financial account.
 */
public class UserDAO {

    File file;
    PrintStream printStream;

    public UserDAO(){
        file = new File("exceptionLog.txt");//TODO: gross

        try {
            printStream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(printStream);
        }
    }


    /**
     * Saves AppUser data to SQL table app_user.
     *
     * This includes username, password, email, firstname, lastname, etc.
     * @param newUser AppUser
     * @return boolean: true if user's personal account has been created, false
     * if it could not be created.
     */
    public boolean save(AppUser newUser){

        System.out.println("Connecting to SQL database...");

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            System.out.println("Uploading created user information into database...");

            String sql = "insert into app_user (username, password)" +
                    " values(?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            //pstmt.setString(3, newUser.getEmail());

            pstmt.executeUpdate();


            sql = "insert into user_info (email, username, first_name, last_name, address, city, state)" +
                    " values(?, ?, ?, ?, ?, ?, ?);";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newUser.getEmail());
            pstmt.setString(2, newUser.getUsername());
            pstmt.setString(3, newUser.getFirstName());
            pstmt.setString(4, newUser.getLastName());
            pstmt.setString(5, newUser.getAddress());
            pstmt.setString(6, newUser.getCity());
            pstmt.setString(7, newUser.getState());

            pstmt.executeUpdate();


            System.out.println("Upload complete!");

        } catch (SQLException e) {
            System.out.println("Upload failed!");
            e.printStackTrace(printStream);
            return false;
        }
        return true;
    }


    /**
     * Locates user's database information by searching for an entry with the user's
     * username and password.  Primarily used for verifying user's information exists
     * in the database.
     *
     * @param username String
     * @param password String
     * @return instance of AppUser
     */
    public AppUser findUserByUsernameAndPassword(String username, String password){
        AppUser user = null;

        //Connection is a connection with an SQL database.
        //SQL statements are executed, and results returned, within the Connection.
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from app_user where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            //this while loop should only loop once
            while (rs.next()) {
                user = new AppUser();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                //user.setEmail(rs.getString("email"));
            }


            sql = "select * from user_info where username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            rs = pstmt.executeQuery();

            //this while loop should only loop once
            while (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
            }


        } catch (SQLException e) {
            e.printStackTrace(printStream);
        }

        return user;

    }


    /**
     * To be used only for checking if username is actually taken.
     *
     * @param username String
     * @return boolean: true if username is found in database,
     * false if it is not.
     */
    public boolean findUserByUsername(String username){
        //AppUser user = null;

        //Connection is a connection with an SQL database.
        //SQL statements are executed, and results returned, within the Connection.
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select username from app_user where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            String userGot = null;

            while(rs.next()){
                userGot = rs.getString("username");
            }

            if(userGot.equals(username)){
                return true;//username is taken
            }

        } catch (NullPointerException e) {
            return false;//username not found
        } catch (PSQLException e) {//why is this catch happening?
            //System.out.println("PSQL error caught.  Column name not found.");
            e.printStackTrace(printStream);
            return false;//username is not taken
        } catch (SQLException e) {
            e.printStackTrace(printStream);
            e.printStackTrace(printStream);
            return false;//ditto, but see errors
        }

        return false;

    }


    /**
     * To be used only for checking if email is actually taken.
     * @param email String
     * @return boolean: true if email is found, false if it is not.
     */
    public boolean findEmailByEmail(String email){

        //Connection is a connection with an SQL database.
        //SQL statements are executed, and results returned, within the Connection.
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select email from user_info where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();


            String userGot =null;

            while(rs.next()){
                userGot = rs.getString("email");
            }


            if(userGot.equals(email)){
                return true;//email is found, thus email name is taken
            }

        } catch (NullPointerException e) {
            return false;//no email found
        } catch (PSQLException e) {
            //System.out.println("PSQL error caught.  Column name not found.");
            e.printStackTrace(printStream);
            return false;
        } catch (SQLException e) {
            e.printStackTrace(printStream);
            e.printStackTrace(printStream);
            return false;
        }

        return false;

    }


    /**
     * Locates user's financial account in database, then deletes it along
     * with their personal account.
     *
     * @param username String
     * @param accountDAO AccountDAO
     * @return boolean: true if both accounts successfully deleted from database,
     * false if they were not.
     */
    public boolean removeUserWithAccount(String username, AccountDAO accountDAO){

        //first check to see if account can be removed
        if(!accountDAO.removeUserAccount(username)){
            return false;
        }
        //else the account has been removed, so continue with user deletion

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "delete from app_user where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            pstmt.executeUpdate();

            System.out.println("Deleting user...");

        } catch (PSQLException e) {
            System.out.println("Deleting user...");
            return true;//this thrown exception indicates no results returned by the query, indicating deletion
        } catch(SQLException e){
            System.out.println("There was a problem trying to close your account!");
            e.printStackTrace(printStream);
            return false;
        }

        //System.out.println("Account successfully removed!");
        return true;
    }


    /**
     * Locates user's account in the database, then deletes it.  They must not have a
     * financial account when doing this.
     *
     * @param username String
     * @return boolean: true if account was deleted, false if it wasn't.
     */
    public boolean removeUserWithoutAccount(String username){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "delete from app_user where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            pstmt.executeUpdate();

            System.out.println("Deleting user...");

        } catch (PSQLException e) {
            System.out.println("You may have a bank account you need to close first!");
            e.printStackTrace(printStream);
            return true;
        } catch(SQLException e){
            System.out.println("There was a problem trying to delete your user account!");
            e.printStackTrace(printStream);
            return false;
        }

        return true;
    }


}
