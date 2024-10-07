/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gui_password_generator;


import java.util.Random;

public class PasswordGenerator {

    public String generatePassword(GeneratorConfig config) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        // Sets of character strings used to generate the password
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()_+-=.<>";

        //  Uses config passed to the class to only include sets user has allowed
        String allowedChars = "";
        if (config.isIncludeLowerCase()) {
            allowedChars += lowerCaseLetters;
        }
        if (config.isIncludeUpperCase()) {
            allowedChars += upperCaseLetters;
        }
        if (config.isIncludeNumbers()) {
            allowedChars += numbers;
        }
        if (config.isIncludeSymbols()) {
            allowedChars += symbols;
        }

        // Generate the password based on the allowed characters
        for (int i = 0; i < config.getPasswordLength(); i++) {
            char nextChar = allowedChars.charAt(random.nextInt(allowedChars.length()));
            if (password.indexOf(String.valueOf(nextChar)) != -1) {
                i--;
            } else {
                password.append(nextChar);
            }
        }
        return password.toString();
    }
}
