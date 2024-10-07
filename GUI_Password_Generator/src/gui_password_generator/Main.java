/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;

import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        boolean reset = false;

        // The program will keep runnin in a loope until the user decides to reset the whole thing or just exits
        do {
            Scanner scanner = new Scanner(System.in);
            DisplayOptions displayOptions = new DisplayOptions(scanner);
 
            // Secrete key to encrypt password
            PasswordEncryptor encryptor = new PasswordEncryptor("sljfdkaljfdalioewkdsfj");
            // Creates storage for the paswords and configuration
            PasswordStorage passwordStorage = new PasswordStorage(encryptor);
            ConfigStorage configStorage = new ConfigStorage();

            // initilizes the configuration settings that is gonna be used to make generated passwords
            GeneratorConfig config = new GeneratorConfig();

            // Load existing configuration if there is any
            try {
                String configContent = configStorage.loadFromFile();
                config.loadConfigFromString(configContent);
                displayOptions.displayMessage("Configuration loaded: \n" + configContent);
            } catch (Exception e) {

                // If no configuration is found, prompt the user to setup
                displayOptions.displayMessage("No existing configuration found. Please set up your password preferences:");
                config.setupConfig(scanner);
                try {
//             Save configuration to file 
                    configStorage.saveToFile(config.toString());
                } catch (Exception ioException) {
                    displayOptions.displayMessage("Error saving configuration.");
                }
            }

            // Initialize the command handler with required components. 
            // Command handler to process all the commands the user might enter
            CommandHandler commandHandler = new CommandHandler(displayOptions, passwordStorage, config, configStorage, scanner);

            // Command loop continues until the user resets or exits
            while (true) {
                String command = displayOptions.getCommand();
                if (command.equals("reset")) {
                    // Reset command handler. Deletes all the files and starts over
                    try {
                        passwordStorage.deleteAllPasswords();
                        configStorage.deleteConfig();
                        displayOptions.displayMessage("Program reset. All files deleted.");
                        reset = true;
                        break;  // Exit the loop to restart the program
                    } catch (IOException e) {
                        displayOptions.displayMessage("Error resetting the program: " + e.getMessage());
                    }
                } else {
                    // Handle other commands
                    commandHandler.handleCommand(command);
                }
            }
        } while (reset);
    }
}
