/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ali Sultani
 */


public class Controller implements ActionListener {

    private final View view;
    private final PasswordStorage passwordStorage;

    // Constructor now only takes view and passwordStorage as parameters
    public Controller(View view, PasswordStorage passwordStorage) {
        this.view = view;
        this.passwordStorage = passwordStorage;
    }

    // Method to register the controller as an ActionListener to the view
    public void initController() {
        this.view.addActionListener(this); // Attach the controller as an action listener to the view
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Generate":
                try {
                // Get configuration directly from the view and validate it
                GeneratorConfig config = view.getConfig();

                if (!config.isIncludeLowerCase() && !config.isIncludeUpperCase()
                        && !config.isIncludeNumbers() && !config.isIncludeSymbols()) {
                    view.showMessage("Please select at least one character type for password generation.");
                    return;
                }

                PasswordGenerator generator = new PasswordGenerator();
                String generatedPassword = generator.generatePassword(config);
                view.setPasswordField(generatedPassword); // Display the generated password in the view
            } catch (IllegalArgumentException ex) {
                view.showMessage("Error: " + ex.getMessage());
            }
            break;

            case "Save":
                // Save the generated password if it exists
                String password = view.getPasswordField();
                if (password == null || password.isEmpty()) {
                    view.showMessage("No password generated to save. Please generate a password first.");
                } else {
                    passwordStorage.savePassword(password);
                    view.showMessage("Password saved successfully.");
                }
                break;

            case "Saved Passwords":
                // Retrieve and display all saved passwords
                String[] savedPasswords = passwordStorage.retrieveAllPasswordsDecrypted()
                        .toArray(String[]::new);
                view.updatePasswordHistory(savedPasswords);
                view.showPasswordHistory(true);
                break;

            case "Clear All":
                // Delete all saved passwords and update the view
                passwordStorage.deleteAllPasswords();
                view.updatePasswordHistory(new String[0]); // Clear the table display
                view.showMessage("All saved passwords have been deleted.");
                break;

            case "Close":
                // Close the password history view
                view.showPasswordHistory(false);
                break;

            default:
                break;
        }
    }
}
