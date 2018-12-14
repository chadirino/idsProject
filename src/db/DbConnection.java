package db;

import java.sql.*;

public class DbConnection {

    // database url
    static final String url = "jdbc:sqlite:bin/db/restaurantdb.db";

    // connection and statement variables
    static Connection con = null;
    static Statement stmt = null;
    
    // main method
    public static void main(String[] args) {
        connect();
        disconnect();
    } 

    // establish connection
    public static void connect() {
        try {
            System.out.println("Connecting...");
            // Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
            System.out.println("Connection established.");
        } catch (Exception err) {
            System.out.println("Connection was not established.");
            err.printStackTrace();
        }
    }

    // disconnect
    public static void disconnect() {
        try {
            if (stmt != null) {                
                stmt.close();
            } 
        } catch (SQLException err) {            
            err.printStackTrace();
        }
        try {
            if (con != null) {
                System.out.println("Disconnecting...");                
                con.close();
                System.out.println("Disconnected.");
            }
        } catch (SQLException err) {
            System.out.println("Disconnection unsuccesful.");            
            err.printStackTrace();
        }
    }
}