/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;

import java.io.IOException;
import java.util.Scanner;

public class CommandHandler {
    private DisplayOptions displayOptions;
    private PasswordStorage passwordStorage;
    private GeneratorConfig config;
    private ConfigStorage configStorage;
    private Scanner scanner;
    private String currentPassword; // Variable which stores the most recently generated password

    public CommandHandler(DisplayOptions displayOptions, PasswordStorage passwordStorage, GeneratorConfig config, ConfigStorage configStorage, Scanner scanner) {
        this.displayOptions = displayOptions;
        this.passwordStorage = passwordStorage;
        this.config = config;
        this.configStorage = configStorage;
        this.scanner = scanner;
        this.currentPassword = null;
    }

    // Handles user commands and runs the appropriate methods from the different classes
    public void handleCommand(String command) {
        switch (command) {
            case "g":
                PasswordGenerator generator = new PasswordGenerator();
                currentPassword = generator.generatePassword(config); // Store the generated password in the variable
                displayOptions.displayMessage("Generated Password: " + currentPassword);
                break;
            case "s":
                try {
                    if (currentPassword == null) {
                        displayOptions.displayMessage("No password to save. Please generate a password first.");
                    } else {
                        passwordStorage.savePassword(currentPassword); // Save the current password to the file
                        displayOptions.displayMessage("Password saved successfully.");
                        currentPassword = null; // Clear the current password after saving
                    }
                } catch (IOException e) {
                    displayOptions.displayMessage("Error saving password: " + e.getMessage());
                }
                break;
            case "c":
                config.setupConfig(scanner);
                try {
                    configStorage.saveToFile(config.toString());  // Save the updated config
                } catch (IOException e) {
                    displayOptions.displayMessage("Error saving configuration: " + e.getMessage());
                }
                break;
            case "d":
                try {
                    var passwords = passwordStorage.retrieveAllPasswordsDecrypted();
                    displayOptions.displayMessage("All saved passwords:");
                    passwords.forEach(displayOptions::displayMessage);
                } catch (IOException e) {
                    displayOptions.displayMessage("Error retrieving passwords: " + e.getMessage());
                }
                break;
            case "d -c":
                try {
                    String configContent = configStorage.loadFromFile();
                    displayOptions.displayMessage("Current Configuration: \n" + configContent);
                } catch (IOException e) {
                    displayOptions.displayMessage("Error loading configuration: " + e.getMessage());
                }
                break;
            case "reset":
                try {
                    passwordStorage.deleteAllPasswords();
                    configStorage.deleteConfig();
                    displayOptions.displayMessage("Program reset. All files deleted.");
                } catch (IOException e) {
                    displayOptions.displayMessage("Error resetting the program: " + e.getMessage());
                }
                break;
            case "h":
                displayHelp();
                break;
            case "e":
                System.exit(0);
                break;
            default:
                displayOptions.displayMessage("Invalid command. Type 'h' for help.");
        }
    }

    // method to display help using the Map collection
    private void displayHelp() {
        displayOptions.displayMessage("Available commands:");
        displayOptions.displayMessage("g: Generate a new password using the current configuration.");
        displayOptions.displayMessage("s: Save the most recent password.");
        displayOptions.displayMessage("c: Configure and update your configuration settings.");
        displayOptions.displayMessage("d: Display all saved passwords.");
        displayOptions.displayMessage("d -c: Display current configuration.");
        displayOptions.displayMessage("reset: Reset the program (delete all files and start over).");
        displayOptions.displayMessage("h: Display this help message.");
        displayOptions.displayMessage("e: Exit the program.");
    }
}