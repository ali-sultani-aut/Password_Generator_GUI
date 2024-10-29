package gui_password_generator;
/**
 *
 * @author bardiakhalifeh
 */ 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordEncryptorTest {
    
    private PasswordEncryptor encryptor;
    private final String secretKey = "testKey";

    public PasswordEncryptorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting PasswordEncryptor tests...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Completed PasswordEncryptor tests.");
    }

    @Before
    public void setUp() {
        encryptor = new PasswordEncryptor(secretKey);
    }

    @After
    public void tearDown() {
        encryptor = null;
    }

    /**
     * Test of encrypt method, of class PasswordEncryptor.
     */
    @Test
    public void testEncrypt() {
        System.out.println("encrypt");
        String data = "password123";
        String encrypted = encryptor.encrypt(data);
        assertNotNull("Encrypted string should not be null", encrypted);
        assertNotEquals("Encrypted string should not match the original data", data, encrypted);
    }

    /**
     * Test of decrypt method, of class PasswordEncryptor.
     */
    @Test
    public void testDecrypt() {
        System.out.println("decrypt");
        String data = "password123";
        String encrypted = encryptor.encrypt(data);
        String decrypted = encryptor.decrypt(encrypted);
        assertNotNull("Decrypted string should not be null", decrypted);
        assertEquals("Decrypted string should match the original data", data, decrypted);
    }

    /**
     * Test that encrypting an empty string returns an empty string.
     */
    @Test
    public void testEncryptEmptyString() {
        System.out.println("encrypt empty string");
        String data = "";
        String encrypted = encryptor.encrypt(data);
        assertEquals("Encrypting an empty string should return an empty string", "", encrypted);
    }

    /**
     * Test that decrypting an empty string returns an empty string.
     */
    @Test
    public void testDecryptEmptyString() {
        System.out.println("decrypt empty string");
        String encrypted = "";
        String decrypted = encryptor.decrypt(encrypted);
        assertEquals("Decrypting an empty string should return an empty string", "", decrypted);
    }

    /**
     * Test that encrypting and decrypting a string with special characters works correctly.
     */
    @Test
    public void testEncryptDecryptSpecialCharacters() {
        System.out.println("encrypt and decrypt special characters");
        String data = "!@#$%^&*()_+";
        String encrypted = encryptor.encrypt(data);
        String decrypted = encryptor.decrypt(encrypted);
        assertEquals("Decrypted special characters should match the original data", data, decrypted);
    }

    /**
     * Test that the shift value is consistent by checking repeated encryption and decryption.
     */
    @Test
    public void testConsistentEncryptionDecryption() {
        System.out.println("consistent encryption and decryption");
        String data = "consistentEncryptionTest";
        String encrypted = encryptor.encrypt(data);
        String decrypted = encryptor.decrypt(encrypted);

        assertEquals("Decrypted string should match the original after encryption and decryption", data, decrypted);
    }
}