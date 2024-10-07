/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PasswordStorage extends FileIO // extends the FileIO class for certain handling methods required
{

    private PasswordEncryptor encryptor; // variable for passwordEncryptior class(encrypt and decrypt)
    private static final String PASSWORD_FILE_PATH = "passwords.txt";

    public PasswordStorage(PasswordEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public void savePassword(String password) throws IOException {
        saveToFile(password);
    }

    @Override
    public void saveToFile(String password) throws IOException {
        try {
            String encryptedPassword = encryptor.encrypt(password); // generated password encrypted utilizing
                                                                    // passwordEncryptor class
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE_PATH, true))) {
                writer.write(encryptedPassword);
                writer.newLine();
            }
        } catch (Exception e) {
            throw new IOException("Error encrypting password", e);
        }
    }

    @Override
    public String loadFromFile() throws IOException {
        StringBuilder content = new StringBuilder(); // gather file content
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // every line gets appended to stringBuilder with a newline ahead
            }
        } catch (IOException e) {
            throw new IOException("Error loading passwords", e);
        }
        return content.toString(); // return content as string
    }

    public void deleteAllPasswords() throws IOException // all stored passwords deleted using empty string to overwite
                                                        // file
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE_PATH))) {
            writer.write("");
        } catch (IOException e) {
            throw new IOException("Error deleting passwords", e);
        }
    }

    public List<String> retrieveAllPasswordsDecrypted() throws IOException // retrieve and decrypt password
    {
        List<String> decryptedPasswords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE_PATH))) {
            String encryptedPassword;
            while ((encryptedPassword = reader.readLine()) != null) {
                try {
                    decryptedPasswords.add(encryptor.decrypt(encryptedPassword));
                } catch (Exception e) {
                    e.printStackTrace();
                    decryptedPasswords.add(null);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error retrieving passwords", e);
        }
        return decryptedPasswords;
    }
}