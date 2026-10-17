/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package login;

import java.util.Random; import java.util.ArrayList;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static int totalMessages = 0;
    
    public String generateMessageID() {
    Random random = new Random();
    StringBuilder id = new StringBuilder();

    for (int i = 0; i < 10; i++) {
        id.append(random.nextInt(10));
    }

    messageID = id.toString();
    return messageID;
}
    
    public void setMessageText(String text) {
    messageText = text;
}

    public void setMessageNumber(int number) {
    messageNumber = number;
}

    public boolean checkMessageID(String id) {
    return id != null && id.length() <= 10;
}

    public String checkRecipientCell(String cellNumber) {
    Login login = new Login();

    if (login.checkCellPhoneNumber(cellNumber)) {
        recipient = cellNumber;
        return "Cell phone number successfully captured.";
    } else {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
}

    public String createMessageHash(String id, int number, String text) {
    String[] words = text.trim().split("\\s+");
    String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
    String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");

    messageHash = (id.substring(0, 2) + ":" + number + ":" + firstWord + lastWord).toUpperCase();
    return messageHash;
}

    public String sentMessage(int choice) {
    switch (choice) {
        case 1:
            sentMessages.add(this);
            totalMessages++;
            return "Message successfully sent.";
        case 2:
            return "Press 0 to delete the message.";
        case 3:
            storeMessage();
            return "Message successfully stored.";
        default:
            return "Invalid option, please try again.";
    }
}



    public String printMessages() {
        return "";
    }
    
    public int returnTotalMessages() {
    return totalMessages;
}

    public void storeMessage() {
    }
}
