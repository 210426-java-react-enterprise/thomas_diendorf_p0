package com.revature.project0.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
Loads application.properties file to known AWS hosted location of SQL server.
Also loads postgresql Driver class.
 Gets access to banking database.
 */

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;
    private Properties props = new Properties();

    private File file;
    PrintStream printStream;

    static {
        try {
            Class.forName("org.postgresql.Driver");//ensures this class loads.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /** ConnectionFactory doesn't initialize here just yet, even though this is constructor.
     * Must call getInstance() to initialize.  Necessary so that only one connection
     * is running for the duration of app use.
     */
    private ConnectionFactory() {

        file = new File("exceptionLog.txt");//TODO: cleanup this gross thing
        try {
            printStream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(printStream);
        }

        try {//connects to application.properties file
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace(printStream);
        }
    }

    /**
     *  Actually initializes ConnectionFactory when this is called
     *
     * @return ConnectionFactory
     */
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;

    }

    /** Gets SQL server connection.
     *
     * @return Connection
     */
    public Connection getConnection() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(//DriverManager manages set of JDBC drivers
                    props.getProperty("host-url"),//url to SQL server hosted on AWS
                    props.getProperty("username"),
                    props.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace(printStream);
        }

        return conn;

    }

}
