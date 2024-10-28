/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;


public class Main {

    public static void main(String[] args) {
        // Create a new DBManager instance, which will connect to the database and create tables if needed
        DBManager dbManager = new DBManager();

        // Create the necessary tables if they don't exist
        dbManager.createTables();

        // Secret key to encrypt password
        PasswordEncryptor encryptor = new PasswordEncryptor("sljfdkaljfdalioewkdsfj");

        // Creates storage for the passwords using the database connection
        PasswordStorage passwordStorage = new PasswordStorage(encryptor, dbManager);

        // Create the View for user interaction
        View view = new View();

        // Create the Controller to handle user actions
        Controller controller = new Controller(view, passwordStorage);
        controller.initController();

        // Register the view as a listener to the password storage
        passwordStorage.setListener(view);

        // Add a shutdown hook to close the database connection when the application exits
        Runtime.getRuntime().addShutdownHook(new Thread(dbManager::closeConnections));
    }
}
