/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gui_password_generator;

import java.util.Scanner;

public class DisplayOptions {

    private Scanner scanner;

    public DisplayOptions(Scanner scanner) {
        this.scanner = scanner;
    }

    // Displays th message passed to the method to the user
    public void displayMessage(String message) {
        System.out.println(message);
    }

//    returns the scanner value of the user input
    public String getCommand() {
        System.out.print("Enter your command (input h to see possible commands): ");
        return scanner.nextLine().trim().toLowerCase();
    }

}
