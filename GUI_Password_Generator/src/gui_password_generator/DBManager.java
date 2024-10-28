/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gui_password_generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bardiakhalifeh
 */

public final class DBManager {

    private static final String USER_NAME = "user";
    private static final String PASSWORD = "pass";
    private static final String URL = "jdbc:derby:PassGenDB;create=true";

    private Connection conn;

    public DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                establishConnection();
            }
        } catch (SQLException e) {
            System.out.println("Error checking database connection: " + e.getMessage());
            e.printStackTrace();
        }
        return this.conn;
    }

    private void establishConnection() {
        try {
            System.out.println("Attempting to establish a connection to the database...");
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            conn.setAutoCommit(true); // Ensure changes are committed automatically
            System.out.println("Successfully connected to the PassGenDB database.");
        } catch (SQLException ex) {
            System.out.println("Error establishing database connection: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection to PassGenDB closed.");
            } catch (SQLException ex) {
                System.out.println("Error closing connection: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void createTables() {
        String createPassTable = "CREATE TABLE PassTable ("
                + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                + "encryptedPassword VARCHAR(255))";

        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(createPassTable);
            System.out.println("PassTable created successfully.");
        } catch (SQLException ex) {
            if ("X0Y32".equals(ex.getSQLState())) {
                System.out.println("PassTable already exists.");
            } else {
                System.out.println("Error creating PassTable: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}