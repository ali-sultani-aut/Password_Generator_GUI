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


public class GeneratorConfigTest {

    private GeneratorConfig config;

    public GeneratorConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting GeneratorConfig tests...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Completed GeneratorConfig tests.");
    }

    @Before
    public void setUp() {
        config = new GeneratorConfig();
    }

    @After
    public void tearDown() {
        config = null;
    }

    @Test
    public void testSetIncludeLowerCase() {
        System.out.println("setIncludeLowerCase");
        config.setIncludeLowerCase(true);
        assertTrue("Lowercase should be included", config.isIncludeLowerCase());

        config.setIncludeLowerCase(false);
        assertFalse("Lowercase should not be included", config.isIncludeLowerCase());
    }

    /**
     * Test of setIncludeUpperCase method, of class GeneratorConfig.
     */
    @Test
    public void testSetIncludeUpperCase() {
        System.out.println("setIncludeUpperCase");
        config.setIncludeUpperCase(true);
        assertTrue("Uppercase should be included", config.isIncludeUpperCase());

        config.setIncludeUpperCase(false);
        assertFalse("Uppercase should not be included", config.isIncludeUpperCase());
    }

    /**
     * Test of setIncludeNumbers method, of class GeneratorConfig.
     */
    @Test
    public void testSetIncludeNumbers() {
        System.out.println("setIncludeNumbers");
        config.setIncludeNumbers(true);
        assertTrue("Numbers should be included", config.isIncludeNumbers());

        config.setIncludeNumbers(false);
        assertFalse("Numbers should not be included", config.isIncludeNumbers());
    }

    /**
     * Test of setIncludeSymbols method, of class GeneratorConfig.
     */
    @Test
    public void testSetIncludeSymbols() {
        System.out.println("setIncludeSymbols");
        config.setIncludeSymbols(true);
        assertTrue("Symbols should be included", config.isIncludeSymbols());

        config.setIncludeSymbols(false);
        assertFalse("Symbols should not be included", config.isIncludeSymbols());
    }

    /**
     * Test of setPasswordLength method, of class GeneratorConfig.
     */
    @Test
    public void testSetPasswordLength() {
        System.out.println("setPasswordLength");
        config.setPasswordLength(12);
        assertEquals("Password length should be set to 12", 12, config.getPasswordLength());

        config.setPasswordLength(-5); // Test for a negative value
        assertEquals("Password length should default to 1 if a negative value is provided", 1, config.getPasswordLength());
    }

    /**
     * Test of isIncludeLowerCase method, of class GeneratorConfig.
     */
    @Test
    public void testIsIncludeLowerCase() {
        System.out.println("isIncludeLowerCase");
        config.setIncludeLowerCase(true);
        assertTrue("Lowercase should be included", config.isIncludeLowerCase());
    }

    /**
     * Test of isIncludeUpperCase method, of class GeneratorConfig.
     */
    @Test
    public void testIsIncludeUpperCase() {
        System.out.println("isIncludeUpperCase");
        config.setIncludeUpperCase(true);
        assertTrue("Uppercase should be included", config.isIncludeUpperCase());
    }

    /**
     * Test of isIncludeNumbers method, of class GeneratorConfig.
     */
    @Test
    public void testIsIncludeNumbers() {
        System.out.println("isIncludeNumbers");
        config.setIncludeNumbers(true);
        assertTrue("Numbers should be included", config.isIncludeNumbers());
    }

    /**
     * Test of isIncludeSymbols method, of class GeneratorConfig.
     */
    @Test
    public void testIsIncludeSymbols() {
        System.out.println("isIncludeSymbols");
        config.setIncludeSymbols(true);
        assertTrue("Symbols should be included", config.isIncludeSymbols());
    }

    /**
     * Test of getPasswordLength method, of class GeneratorConfig.
     */
    @Test
    public void testGetPasswordLength() {
        System.out.println("getPasswordLength");
        config.setPasswordLength(10);
        assertEquals("Password length should be 10", 10, config.getPasswordLength());
    }

    /**
     * Test of toString method, of class GeneratorConfig.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        config.setIncludeLowerCase(true);
        config.setIncludeUpperCase(true);
        config.setIncludeNumbers(true);
        config.setIncludeSymbols(false);
        config.setPasswordLength(8);

        String expResult = "Include Lowercase: true\nInclude Uppercase: true\nInclude Numbers: true\nInclude Symbols: false\nPassword Length: 8";
        String result = config.toString();
        assertEquals("The toString method should match the expected format", expResult, result);
    }
}