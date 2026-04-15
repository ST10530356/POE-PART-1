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
public class PhoneNumberCheckerTest {
    
    public PhoneNumberCheckerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of isNumberValid method, of class PhoneNumberChecker.
     */
    
    @Test
    public void testValidSouthAfricanNumber() {
        assertTrue(PhoneNumberChecker.isNumberValid("+27123456789"));
        assertTrue(PhoneNumberChecker.isNumberValid("+27821234567"));
        assertTrue(PhoneNumberChecker.isNumberValid("+27000000000"));
    }
    
    @Test
    public void testIsNumberValid() {
        System.out.println("isNumberValid");
        String cellPhone = "";
        boolean expResult = false;
        boolean result = PhoneNumberChecker.isNumberValid(cellPhone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
