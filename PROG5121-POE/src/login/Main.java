/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine(). trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine(). trim();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine(). trim();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine(). trim();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine(). trim();
                    System.out.print("Enter cell phone number: ");
                    String cellNumber = scanner.nextLine(). trim();

                    String result = login.registerUser(username, password, cellNumber, firstName, lastName);
                    System.out.println(result);
                    break;

                case "2":
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine(). trim();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine(). trim();

                    boolean success = login.loginUser(loginUsername, loginPassword);
                    System.out.println(login.returnLoginStatus(success));
                    break;

                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}