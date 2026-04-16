/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sammy
 */
public class MainTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    public MainTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
    
    private void provideInput(String data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
    }
    
    @Test
    public void testRegistrationAndLoginFlow() throws Exception {
        String input = "1\nJohn\nDoe\njohn_\nPassword1!\n+27123456789\n2\njohn_\nPassword1!\n3\n";
        provideInput(input);
        
        
        Main.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("Username successfully captured"));
        assertTrue(output.contains("Password successfully captured"));
        assertTrue(output.contains("Cell phone number successfully added"));

        assertTrue(output.contains("Welcome John Doe, it is great to see you."));
    }
    
    @Test
    public void testInvalidUsernameRegistration() throws Exception {
        String input = "1\nJohn\nDoe\njohn\nPassword1!\n+27123456789\n3\n";
        provideInput(input);
        Main.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("Username is not correctly formatted"));
    }
    
    @Test
    public void testInvalidPasswordRegistration() throws Exception {
        String input = "1\nJohn\nDoe\njohn_\nweak\n+27123456789\n3\n";
        provideInput(input);
        Main.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("Password is not correctly formatted"));
    }
    
    @Test
    public void testInvalidPhoneRegistration() throws Exception {
        String input = "1\nJohn\nDoe\njohn_\nPassword1!\n12345\n3\n";
        provideInput(input);
        Main.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("Cell phone number incorrectly formatted"));
    }

    @Test
    public void testDuplicateUsernameRegistration() throws Exception {
        String input1 = "1\nJohn\nDoe\njohn_\nPassword1!\n+27123456789\n3\n";
        provideInput(input1);
        Main.main(new String[]{});
        outContent.reset();
        
        String input2 = "1\nJane\nSmith\njohn_\nAnother2@\n+27876543210\n3\n";
        provideInput(input2);
        Main.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("Username already exists"));
    }

    @Test
    public void testLoginWithWrongPassword() throws Exception {
        String regInput = "1\nAlice\nWonder\nalice_\nSecret1@\n+27111222333\n3\n";
        provideInput(regInput);
        Main.main(new String[]{});
        outContent.reset();
        
        String loginInput = "2\nalice_\nwrongpass\n3\n";
        provideInput(loginInput);
        Main.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("Username/password incorrect"));
    }

    @Test
    public void testLoginUnregisteredUser() throws Exception {
        String input = "2\nnobody_\npass\n3\n";
        provideInput(input);
        Main.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("Username/password incorrect"));
    }
}
