/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;

import java.util.Scanner;

public class GeneratorConfig {

    private boolean includeLowerCase;
    private boolean includeUpperCase;
    private boolean includeNumbers;
    private boolean includeSymbols;
    private int passwordLength;

    public void setupConfig(Scanner scanner) {
        // Loop until a valid configuration is provided
        while (true) {
            // Ask the user to configure the settings
            this.includeLowerCase = getYesNoInput(scanner, "Include lowercase letters? (y/n):");
            this.includeUpperCase = getYesNoInput(scanner, "Include uppercase letters? (y/n):");
            this.includeNumbers = getYesNoInput(scanner, "Include numbers? (y/n):");
            this.includeSymbols = getYesNoInput(scanner, "Include symbols? (y/n):");

            // Validate that at least one option is selected
            if (!includeLowerCase && !includeUpperCase && !includeNumbers && !includeSymbols) {
                System.out.println("It is not allowed for all options to be 'no'. Please select at least one character type.");
            } else {
                break; // Exit the loop if a valid configuration is provided
            }
        }

        // Ask for the desired password length
        this.passwordLength = getPasswordLength(scanner, "Enter the desired password length:");
    }

    // Method to load configuration from a string (for reloading config)
    public void loadConfigFromString(String configContent) {
        String[] lines = configContent.split("\n");
        for (String line : lines) {
            String[] keyValue = line.split(": ");
            switch (keyValue[0].trim()) {
                case "Include Lowercase":
                    this.includeLowerCase = Boolean.parseBoolean(keyValue[1].trim());
                    break;
                case "Include Uppercase":
                    this.includeUpperCase = Boolean.parseBoolean(keyValue[1].trim());
                    break;
                case "Include Numbers":
                    this.includeNumbers = Boolean.parseBoolean(keyValue[1].trim());
                    break;
                case "Include Symbols":
                    this.includeSymbols = Boolean.parseBoolean(keyValue[1].trim());
                    break;
                case "Password Length":
                    this.passwordLength = Integer.parseInt(keyValue[1].trim());
                    break;
                default:
                    break;
            }
        }
    }

    // Method to check for yes/no input and allow exiting
    private boolean getYesNoInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else if (input.equals("e")) {
                System.exit(0); // Exit the program if the user inputs 'e'
            } else {
                System.out.println("Invalid input. Please enter 'y', 'n', or 'e' to exit.");
            }
        }
    }

    // Method to get a valid password length and allow exiting
    private int getPasswordLength(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("e")) {
                System.exit(0); // Exit the program if the user inputs 'e'
            }
            try {
                int length = Integer.parseInt(input);
                if (length > 0) {
                    return length;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer or 'e' to exit.");
            }
        }
    }

    @Override
    public String toString() {
        return "Include Lowercase: " + includeLowerCase
                + "\nInclude Uppercase: " + includeUpperCase
                + "\nInclude Numbers: " + includeNumbers
                + "\nInclude Symbols: " + includeSymbols
                + "\nPassword Length: " + passwordLength;
    }

    // Getters for each configuration option
    public boolean isIncludeLowerCase() {
        return includeLowerCase;
    }

    public boolean isIncludeUpperCase() {
        return includeUpperCase;
    }

    public boolean isIncludeNumbers() {
        return includeNumbers;
    }

    public boolean isIncludeSymbols() {
        return includeSymbols;
    }

    public int getPasswordLength() {
        return passwordLength;
    }
}
