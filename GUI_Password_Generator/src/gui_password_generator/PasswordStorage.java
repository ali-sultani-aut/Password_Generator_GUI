/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gui_password_generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordStorage extends FileIO {

    private static final Logger LOGGER = Logger.getLogger(PasswordStorage.class.getName());
    final private PasswordEncryptor encryptor; // Variable for password encryption/decryption
    private final DBManager dbManager;
    private PasswordListener listener; // Listener for updates

    public PasswordStorage(PasswordEncryptor encryptor, DBManager dbManager) {
        this.encryptor = encryptor;
        this.dbManager = dbManager;
    }

    // Set the listener to notify the view
    public void setListener(PasswordListener listener) {
        this.listener = listener;
    }

    // Notify the listener with a message
    private void notifyListener(String message) {
        if (listener != null) {
            listener.onPasswordsUpdated(message);
        }
    }

    public void savePassword(String password) {
        saveToFile(password);
    }

    @Override
    public void saveToFile(String password) {
        try {
            String encryptedPassword = encryptor.encrypt(password);
            try ( Connection conn = dbManager.getConnection();  PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO PassTable (encryptedPassword) VALUES (?)")) {
                pstmt.setString(1, encryptedPassword);
                pstmt.executeUpdate();
                notifyListener("Password saved to database successfully.");
            }
        } catch (SQLException e) {
            notifyListener("Error saving password to database: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Error saving password", e);
        }
    }

    @Override
    public String loadFromFile() {
        StringBuilder content = new StringBuilder();
        try ( Connection conn = dbManager.getConnection();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery("SELECT encryptedPassword FROM PassTable")) {

            while (rs.next()) {
                content.append(rs.getString("encryptedPassword")).append("\n");
            }
            notifyListener("Loaded passwords from database.");
        } catch (SQLException e) {
            notifyListener("Error loading passwords from database: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Error loading passwords", e);
        }
        return content.toString();
    }

    public void deleteAllPasswords() {
        try ( Connection conn = dbManager.getConnection();  Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM PassTable");
            notifyListener("All passwords deleted from the database.");
        } catch (SQLException e) {
            notifyListener("Error deleting passwords from database: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Error deleting passwords", e);
        }
    }

    public List<String> retrieveAllPasswordsDecrypted() {
        List<String> decryptedPasswords = new ArrayList<>();
        try ( Connection conn = dbManager.getConnection();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery("SELECT encryptedPassword FROM PassTable")) {

            while (rs.next()) {
                String encryptedPassword = rs.getString("encryptedPassword");
                try {
                    decryptedPasswords.add(encryptor.decrypt(encryptedPassword));
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error decrypting password", e);
                    decryptedPasswords.add(null);
                }
            }
            notifyListener("Retrieved all decrypted passwords.");
        } catch (SQLException e) {
            notifyListener("Error retrieving passwords from database: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Error retrieving passwords", e);
        }
        return decryptedPasswords;
    }
}
