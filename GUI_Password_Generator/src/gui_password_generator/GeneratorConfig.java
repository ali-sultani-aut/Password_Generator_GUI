/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bardiakhalifeh
 */ 

package gui_password_generator;

// Class representing the configuration settings for password generation
public class GeneratorConfig {

    // Flags to determine whether to include certain character types in the password
    private boolean includeLowerCase; // Whether to include lowercase letters
    private boolean includeUpperCase; // Whether to include uppercase letters
    private boolean includeNumbers;   // Whether to include numbers
    private boolean includeSymbols;   // Whether to include symbols
    private int passwordLength = 8;   // Default password length

    // Setters to update the configuration settings
    public void setIncludeLowerCase(boolean includeLowerCase) {
        this.includeLowerCase = includeLowerCase;
    }

    public void setIncludeUpperCase(boolean includeUpperCase) {
        this.includeUpperCase = includeUpperCase;
    }

    public void setIncludeNumbers(boolean includeNumbers) {
        this.includeNumbers = includeNumbers;
    }

    public void setIncludeSymbols(boolean includeSymbols) {
        this.includeSymbols = includeSymbols;
    }

    // Sets the password length, ensuring it is at least 1
    public void setPasswordLength(int length) {
        this.passwordLength = Math.max(1, length);
    }

    // Getters to retrieve the configuration settings
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

    // Returns a string representation of the configuration settings
    @Override
    public String toString() {
        return "Include Lowercase: " + includeLowerCase
                + "\nInclude Uppercase: " + includeUpperCase
                + "\nInclude Numbers: " + includeNumbers
                + "\nInclude Symbols: " + includeSymbols
                + "\nPassword Length: " + passwordLength;
    }
}