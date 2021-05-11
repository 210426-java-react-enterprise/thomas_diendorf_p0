package com.revature.project0.daos;

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
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setAccountID(rs.getString("account_id"));//reference with account(s) user starts with
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }



    //to be used only for checking if username is actually taken
    //TODO: fix this thing!!!
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


}
