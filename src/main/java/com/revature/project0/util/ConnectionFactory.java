package com.revature.project0.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
Loads application.properties file to know AWS hosted location of SQL server.
Also loads postgresql Driver class.
 */

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;
    private Properties props = new Properties();

    static {
        try {
            Class.forName("org.postgresql.Driver");//ensures this class loads.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //doesn't initialize just yet
    private ConnectionFactory() {
        try {//connects to application.properties file
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //actually initializes when this is called
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;

    }

    //get SQL server connection
    public Connection getConnection() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(//DriverManager manages set of JDBC drivers
                    props.getProperty("host-url"),//url to SQL server hosted on AWS
                    props.getProperty("username"),
                    props.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

}
