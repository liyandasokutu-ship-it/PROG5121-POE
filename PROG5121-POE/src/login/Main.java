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
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine().trim();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine().trim();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine().trim();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine().trim();
                    System.out.print("Enter cell phone number: ");
                    String cellNumber = scanner.nextLine().trim();

                    String result = login.registerUser(username, password, cellNumber, firstName, lastName);
                    System.out.println(result);
                    break;

                case "2":
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine().trim();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine().trim();

                    boolean success = login.loginUser(loginUsername, loginPassword);
                    System.out.println(login.returnLoginStatus(success));

                    if (success) {
                        quickChat(scanner);
                        running = false;
                    }
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

    private static void quickChat(Scanner scanner) {
        System.out.println("\nWelcome to QuickChat.");

        int limit = 0;
        while (limit <= 0) {
            System.out.print("How many messages do you wish to enter? ");
            try {
                limit = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                limit = 0;
            }
            if (limit <= 0) {
                System.out.println("Please enter a whole number greater than 0.");
            }
        }

        Message tracker = new Message();
        int entered = 0;
        boolean chatting = true;

        while (chatting) {
            System.out.println("\n=== QuickChat Menu ===");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    if (entered >= limit) {
                        System.out.println("You have already entered all " + limit + " of your messages.");
                    } else {
                        while (entered < limit) {
                            enterMessage(scanner, entered);
                            entered++;
                        }
                        System.out.println("\nTotal messages sent: " + tracker.returnTotalMessages());
                    }
                    break;

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    chatting = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void enterMessage(Scanner scanner, int number) {
        Message msg = new Message();
        String id = msg.generateMessageID();

        System.out.println("\nMessage " + (number + 1));
        System.out.println("Message ID generated: " + id);

        String cellResult;
        do {
            System.out.print("Enter recipient cell number: ");
            cellResult = msg.checkRecipientCell(scanner.nextLine().trim());
            System.out.println(cellResult);
        } while (!cellResult.equals("Cell phone number successfully captured."));

        String text;
        String lengthResult;
        do {
            System.out.print("Enter your message: ");
            text = scanner.nextLine().trim();
            lengthResult = msg.checkMessageLength(text);
            System.out.println(lengthResult);
        } while (!lengthResult.equals("Message ready to send."));

        msg.setMessageNumber(number);
        msg.setMessageText(text);
        msg.createMessageHash(id, number, text);

        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");

        String choice = "";
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            System.out.print("Choose an option (1-3): ");
            choice = scanner.nextLine().trim();
        }

        System.out.println(msg.sentMessage(Integer.parseInt(choice)));

        if (choice.equals("1")) {
            System.out.println("\n" + msg.getMessageDetails());
        } else if (choice.equals("2")) {
            if (scanner.nextLine().trim().equals("0")) {
                System.out.println("Message deleted.");
            } else {
                System.out.println("Message disregarded.");
            }
        }
    }
}