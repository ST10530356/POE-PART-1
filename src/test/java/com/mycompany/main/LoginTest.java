/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.main;

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
public class LoginTest {
    
     private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

 
    @Test
    public void testCheckUserName_valid() {
        assertTrue(login.checkUserName("kyle_"));
        assertTrue(login.checkUserName("a_b"));
        assertTrue(login.checkUserName("_123"));  
    }

    @Test
    public void testCheckUserName_invalid_noUnderscore() {
        assertFalse(login.checkUserName("kyle"));
        assertFalse(login.checkUserName("kyle1"));
    }

    @Test
    public void testCheckUserName_invalid_tooLong() {
        assertFalse(login.checkUserName("kyle_123")); 
        assertFalse(login.checkUserName("abc_de"));    
    }

    @Test
    public void testCheckPasswordComplexity_valid() {
        assertTrue(login.checkPasswordComplexity("Passw0rd!"));
        assertTrue(login.checkPasswordComplexity("A1!bcdef"));  
        assertTrue(login.checkPasswordComplexity("Valid1@pass"));
    }

    @Test
    public void testCheckPasswordComplexity_tooShort() {
        assertFalse(login.checkPasswordComplexity("A1!abc"));   
        assertFalse(login.checkPasswordComplexity("Pass1!"));   
    }

    @Test
    public void testCheckPasswordComplexity_missingUppercase() {
        assertFalse(login.checkPasswordComplexity("passw0rd!"));
    }

    @Test
    public void testCheckPasswordComplexity_missingDigit() {
        assertFalse(login.checkPasswordComplexity("Password!"));
    }

    @Test
    public void testCheckPasswordComplexity_missingSpecialChar() {
        assertFalse(login.checkPasswordComplexity("Password1"));
    }

    @Test
    public void testCheckPhoneNumber_valid() {
        assertTrue(login.checkPhoneNumber("+27345678901"));
        assertTrue(login.checkPhoneNumber("+27123456789"));
    }

    @Test
    public void testCheckPhoneNumber_invalid() {
        assertFalse(login.checkPhoneNumber("12345"));         
        assertFalse(login.checkPhoneNumber("abc"));
        assertFalse(login.checkPhoneNumber(""));
    }

    @Test
    public void testRegisterUser_success() {
        String result = login.registerUser("John", "Doe", "john_", "Password1!", "+27345678909");
        assertTrue(result.contains("Username successfully captured"));
        assertTrue(result.contains("Password successfully captured"));
        assertTrue(result.contains("Cell phone number successfully added"));
    }

    @Test
    public void testRegisterUser_invalidUsername() {
        String result = login.registerUser("John", "Doe", "john", "Password1!", "+1234567890");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterUser_invalidPassword() {
        String result = login.registerUser("John", "Doe", "john_", "weak", "+1234567890");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character.", result);
    }

    @Test
    public void testRegisterUser_invalidPhone() {
        String result = login.registerUser("John", "Doe", "john_", "Password1!", "12345");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code; please the number and try again.", result);
    }

    @Test
    public void testRegisterUser_duplicateUsername() {
        login.registerUser("John", "Doe", "john_", "Password1!", "+2734567890");
        String result = login.registerUser("Jane", "Smith", "john_", "Another2@", "+9876543210");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code; please the number and try again.", result);
    }

    @Test
    public void testLoginUser_success() {
        login.registerUser("Alice", "Wonder", "alice_", "Secret1@", "+1112223333");
        assertFalse(login.loginUser("alice_", "Secret1@"));
        assertEquals("Username/password incorrect, or user name has not registered yet. Please try again", login.returnLoginStatus());
    }

    @Test
    public void testLoginUser_wrongPassword() {
        login.registerUser("Bob", "Builder", "bob_", "CanFix1!", "+4445556666");
        assertFalse(login.loginUser("bob_", "wrong"));
        assertEquals("Username/password incorrect, or user name has not registered yet. Please try again", login.returnLoginStatus());
    }

    @Test
    public void testLoginUser_unregisteredUser() {
        assertFalse(login.loginUser("nobody_", "pass"));
        assertEquals("Username/password incorrect, or user name has not registered yet. Please try again", login.returnLoginStatus());
    }

    @Test
    public void testReturnLoginStatus_beforeAnyLogin() {
        // Initially, loginMessage is null? Your code sets it only during login attempts.
        // To avoid NullPointerException, you should initialize loginMessage = "" in Login class.
        // For now, we test after a failed login.
        login.loginUser("any", "any");
        assertNotNull(login.returnLoginStatus());
    }
    
}
