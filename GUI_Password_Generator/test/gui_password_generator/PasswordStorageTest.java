/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gui_password_generator;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ali Sultani
 */

public class PasswordStorageTest {

    private PasswordStorage passwordStorage;
    private DBManager dbManager;
    private PasswordEncryptor encryptor;

    @Before
    public void setUp() {
        // Initialize the database manager for the connection.
        dbManager = new DBManager();
        encryptor = new PasswordEncryptor("testSecretKey");
        passwordStorage = new PasswordStorage(encryptor, dbManager);

        // Clean up any existing passwords.
        passwordStorage.deleteAllPasswords();
    }

    @After
    public void tearDown() {
        // Close connections.
        passwordStorage.deleteAllPasswords();
        dbManager.closeConnections();
    }

    /**
     * Test saving and retrieving a password.
     */
    @Test
    public void testSaveAndRetrievePassword() {
        String password = "simpleTestPassword";
        passwordStorage.savePassword(password);

        List<String> savedPasswords = passwordStorage.retrieveAllPasswordsDecrypted();
        assertNotNull("Saved passwords list should not be null", savedPasswords);
        assertTrue("The saved password should be present in the list", savedPasswords.contains(password));
    }

    /**
     * Test that saving a password and then deleting all passwords results in an empty list.
     */
    @Test
    public void testDeleteAllPasswords() {
        passwordStorage.savePassword("tempPassword");

        passwordStorage.deleteAllPasswords();
        List<String> passwords = passwordStorage.retrieveAllPasswordsDecrypted();
        assertNotNull("Passwords list should not be null", passwords);
        assertTrue("Passwords list should be empty after deletion", passwords.isEmpty());
    }

    /**
     * Test that encryption and decryption are consistent.
     */
    @Test
    public void testEncryptionDecryptionConsistency() {
        String originalPassword = "consistentPassword";
        passwordStorage.savePassword(originalPassword);

        List<String> decryptedPasswords = passwordStorage.retrieveAllPasswordsDecrypted();
        assertNotNull("Decrypted passwords should not be null", decryptedPasswords);
        assertTrue("Decrypted passwords should contain the original password",
                decryptedPasswords.contains(originalPassword));
    }
}