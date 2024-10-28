/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;

/**
 *
 * @author Ali Sultani
 */

// Interface for listening to password related events
public interface PasswordListener {

    // Called when the passwords are updated, providing a message with details
    void onPasswordsUpdated(String message);

    // Called when the password configuration is updated, providing the updated configuration details
    void onConfigUpdated(String configDetails);
}