/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author sammy
 */

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Login login = new Login();

    public static void main(String[] args){

        String choice;

       //Displays main menu and handles choices made by user
        while (true) {
            System.out.println("\nRegistration and Login menu");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose one choice: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> register();
                case "2" -> loginUser();
                case "3" -> {
                    System.out.println("Menu has closed. Goodbye");
                    return;
                }
                default -> System.out.println("Invalid choice, please input choices given(1, 2, or 3)");
            }
        }
    } 

    //Prompts user to register when not already registered
    private static void register(){
        System.out.println("\nRegister new acount here:");
        System.out.print("Enter first name here: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter surnname/last name here: ");
        String surname = scanner.nextLine();
        System.out.print("Enter username (Must contain underscore(_) and max 5 chars): ");
        String username = scanner.nextLine();
        System.out.println("Password: min 8 chars, one uppercase, one number, one special char");
        System.out.print("Enterpassword: ");
        String password = scanner.nextLine();
        System.out.println("Cell phone: +27 followed by exactly 9 digits (e.g., +27123456789)");
        System.out.print("Enter your cell phone number here: ");
        String phoneNumber = scanner.nextLine();

        //Outputs message for successful registration and user info
        System.out.println(login.registerUser(firstName, surname , username, password, phoneNumber));


    }

    //Prompts user for login info and returns message if registered already
    private static void loginUser(){
        System.out.println("\nLogin");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        login.loginUser(username, password);
        System.out.println(login.returnLoginStatus());

    }
}

