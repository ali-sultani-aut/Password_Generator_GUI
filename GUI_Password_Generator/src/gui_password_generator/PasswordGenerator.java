/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gui_password_generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author Ali Sultani
 */ 

public class PasswordGenerator {

    public String generatePassword(GeneratorConfig config) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        // Sets of character strings used to generate the password
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()_+-=.<>";

        // Uses config passed to the class to only include sets user has allowed
        StringBuilder allowedChars = new StringBuilder();
        List<Character> requiredChars = new ArrayList<>();

        // Add required characters based on config to ensure each type is represented
        if (config.isIncludeLowerCase()) {
            allowedChars.append(lowerCaseLetters);
            requiredChars.add(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        }
        if (config.isIncludeUpperCase()) {
            allowedChars.append(upperCaseLetters);
            requiredChars.add(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        }
        if (config.isIncludeNumbers()) {
            allowedChars.append(numbers);
            requiredChars.add(numbers.charAt(random.nextInt(numbers.length())));
        }
        if (config.isIncludeSymbols()) {
            allowedChars.append(symbols);
            requiredChars.add(symbols.charAt(random.nextInt(symbols.length())));
        }

        // Ensure allowedChars is not empty before generating the password
        if (allowedChars.length() == 0) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }

        // Add required characters to ensure each type is represented
        for (char requiredChar : requiredChars) {
            password.append(requiredChar);
        }

        // Fill the remaining length with random characters from the allowed characters
        while (password.length() < config.getPasswordLength()) {
            char nextChar = allowedChars.charAt(random.nextInt(allowedChars.length()));
            password.append(nextChar);
        }

        // Shuffle the password to ensure randomness
        List<Character> passwordChars = password.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());
        Collections.shuffle(passwordChars);

        // Convert back to a string
        StringBuilder finalPassword = new StringBuilder();
        for (char c : passwordChars) {
            finalPassword.append(c);
        }

        return finalPassword.toString();
    }
}
