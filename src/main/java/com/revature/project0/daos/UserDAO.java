package com.revature.project0.daos;

import com.revature.project0.models.AppAccount;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ConnectionFactory;
import org.postgresql.util.PSQLException;

import java.sql.*;

public class UserDAO {

    /**
     * Saves AppUser data to SQL table app_user.
     *
     * This includes username, password, email, firstname, lastname, etc.
     *
     */
    public boolean save(AppUser newUser){

        System.out.println("Connecting to SQL database...");

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            System.out.println("Uploading created user information into database...");

            /*
            String sql = "insert into app_user (username, password, email, first_name, last_name, address, city, state)" +
                    " values(?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getFirstName());
            pstmt.setString(5, newUser.getLastName());
            pstmt.setString(6, newUser.getAddress());
            pstmt.setString(7, newUser.getCity());
            pstmt.setString(8, newUser.getState());
            //pstmt.setString(9, newUser.getAccountID());
             */

            String sql = "insert into user_info (email, username, first_name, last_name, address, city, state)" +
                    " values(?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newUser.getEmail());
            pstmt.setString(2, newUser.getUsername());
            pstmt.setString(3, newUser.getFirstName());
            pstmt.setString(4, newUser.getLastName());
            pstmt.setString(5, newUser.getAddress());
            pstmt.setString(6, newUser.getCity());
            pstmt.setString(7, newUser.getState());

            pstmt.executeUpdate();


            sql = "insert into app_user (username, password, email)" +
                    " values(?, ?, ?);";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getEmail());

            pstmt.executeUpdate();

            System.out.println("Upload complete!");

        } catch (SQLException e) {
            System.out.println("Upload failed!");
            e.printStackTrace();
            return false;
        }
        return true;
    }




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
                user.setEmail(rs.getString("email"));
            }


            sql = "select * from user_info where username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            rs = pstmt.executeQuery();

            //this while loop should only loop once
            while (rs.next()) {
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }



    //to be used only for checking if username is actually taken
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
            e.printStackTrace();
            return false;//username is not taken
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            return false;//ditto, but see errors
        }

        return false;

    }


    //to be used only for checking if email is actually taken
    public boolean findEmailByEmail(String email){

        //Connection is a connection with an SQL database.
        //SQL statements are executed, and results returned, within the Connection.
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select email from app_user where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            String userGot = null;

            while(rs.next()){
                userGot = rs.getString("email");
            }

            if(userGot.equals(email)){
                return true;//email is taken
            }

        } catch (NullPointerException e) {
            return false;
        } catch (PSQLException e) {
            //System.out.println("PSQL error caught.  Column name not found.");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            return false;
        }

        return false;

    }


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
