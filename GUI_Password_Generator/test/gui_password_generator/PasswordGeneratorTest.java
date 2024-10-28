/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gui_password_generator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ali Sultani
 */

/**
 * Unit tests for the PasswordGenerator class.
 */

public class PasswordGeneratorTest {

    private PasswordGenerator passwordGenerator;

    public PasswordGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting PasswordGenerator tests...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Completed PasswordGenerator tests.");
    }

    @Before
    public void setUp() {
        passwordGenerator = new PasswordGenerator();
    }

    @After
    public void tearDown() {
        passwordGenerator = null;
    }

    /**
     * Test generating a password with only lowercase letters.
     */
    @Test
    public void testGeneratePasswordWithLowerCase() {
        GeneratorConfig config = new GeneratorConfig();
        config.setIncludeLowerCase(true);
        config.setPasswordLength(10);

        String password = passwordGenerator.generatePassword(config);
        assertNotNull("Password should not be null", password);
        assertEquals("Password should be of length 10", 10, password.length());
        assertTrue("Password should contain only lowercase letters", password.matches("[a-z]+"));
    }

    /**
     * Test generating a password with only uppercase letters.
     */
    @Test
    public void testGeneratePasswordWithUpperCase() {
        GeneratorConfig config = new GeneratorConfig();
        config.setIncludeUpperCase(true);
        config.setPasswordLength(10);

        String password = passwordGenerator.generatePassword(config);
        assertNotNull("Password should not be null", password);
        assertEquals("Password should be of length 10", 10, password.length());
        assertTrue("Password should contain only uppercase letters", password.matches("[A-Z]+"));
    }

    /**
     * Test generating a password with only numbers.
     */
    @Test
    public void testGeneratePasswordWithNumbers() {
        GeneratorConfig config = new GeneratorConfig();
        config.setIncludeNumbers(true);
        config.setPasswordLength(10);

        String password = passwordGenerator.generatePassword(config);
        assertNotNull("Password should not be null", password);
        assertEquals("Password should be of length 10", 10, password.length());
        assertTrue("Password should contain only numbers", password.matches("[0-9]+"));
    }

    /**
     * Test generating a password with only symbols.
     */
    @Test
    public void testGeneratePasswordWithSymbols() {
        GeneratorConfig config = new GeneratorConfig();
        config.setIncludeSymbols(true);
        config.setPasswordLength(10);

        String password = passwordGenerator.generatePassword(config);
        assertNotNull("Password should not be null", password);
        assertEquals("Password should be of length 10", 10, password.length());
        assertTrue("Password should contain only symbols", password.matches("[!@#$%^&*()_+\\-=.<>]+"));
    }

    /**
     * Test generating a password with a mix of all character types.
     */
    @Test
    public void testGeneratePasswordWithAllTypes() {
        GeneratorConfig config = new GeneratorConfig();
        config.setIncludeLowerCase(true);
        config.setIncludeUpperCase(true);
        config.setIncludeNumbers(true);
        config.setIncludeSymbols(true);
        config.setPasswordLength(12);

        String password = passwordGenerator.generatePassword(config);
        assertNotNull("Password should not be null", password);
        assertEquals("Password should be of length 12", 12, password.length());
        assertTrue("Password should contain lowercase letters", password.matches(".*[a-z].*"));
        assertTrue("Password should contain uppercase letters", password.matches(".*[A-Z].*"));
        assertTrue("Password should contain numbers", password.matches(".*[0-9].*"));
        assertTrue("Password should contain symbols", password.matches(".*[!@#$%^&*()_+\\-=.<>].*"));
    }

    /**
     * Test for IllegalArgumentException when no character types are selected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGeneratePasswordWithNoCharacterTypes() {
        GeneratorConfig config = new GeneratorConfig();
        config.setIncludeLowerCase(false);
        config.setIncludeUpperCase(false);
        config.setIncludeNumbers(false);
        config.setIncludeSymbols(false);
        config.setPasswordLength(10);

        passwordGenerator.generatePassword(config);
    }

    /**
     * Test generating a password with minimum length of 1.
     */
    @Test
    public void testGeneratePasswordWithMinimumLength() {
        GeneratorConfig config = new GeneratorConfig();
        config.setIncludeLowerCase(true);
        config.setPasswordLength(1);

        String password = passwordGenerator.generatePassword(config);
        assertNotNull("Password should not be null", password);
        assertEquals("Password should be of length 1", 1, password.length());
    }

    /**
     * Test generating a password with an empty config object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGeneratePasswordWithEmptyConfig() {
        GeneratorConfig config = new GeneratorConfig();
        passwordGenerator.generatePassword(config);
    }
}
