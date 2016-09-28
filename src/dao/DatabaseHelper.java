package dao;

import java.sql.*;

public class DatabaseHelper {

    // connect to the database and return Connection object
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "ojtibe");
            return con;
        } catch (Exception e) {
            System.err.println("Connection error!");
            return null;
        }
    }

}
