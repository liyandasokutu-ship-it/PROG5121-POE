/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package login;

import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static int totalMessages = 0;
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    
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
    
    public String checkMessageLength(String text) {
    if (text.length() <= 250) {
        return "Message ready to send.";
    } else {
        int extra = text.length() - 250;
        return "Message exceeds 250 characters by " + extra + "; please reduce the size.";
    }
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
    StringBuilder output = new StringBuilder();

    for (Message m : sentMessages) {
        output.append("Message ID: ").append(m.messageID).append("\n");
        output.append("Message Hash: ").append(m.messageHash).append("\n");
        output.append("Recipient: ").append(m.recipient).append("\n");
        output.append("Message: ").append(m.messageText).append("\n\n");
    }

    return output.toString();
}
    
    public int returnTotalMessages() {
    return totalMessages;
}

    // File writing approach adapted from TutorialsPoint:
// https://www.tutorialspoint.com/how-to-write-create-a-json-file-using-java
public void storeMessage() {
    storedMessages.add(this);

    StringBuilder json = new StringBuilder();
    json.append("[\n");

    for (int i = 0; i < storedMessages.size(); i++) {
        Message m = storedMessages.get(i);
        json.append("  {\n");
        json.append("    \"messageID\": \"").append(escapeJson(m.messageID)).append("\",\n");
        json.append("    \"messageHash\": \"").append(escapeJson(m.messageHash)).append("\",\n");
        json.append("    \"recipient\": \"").append(escapeJson(m.recipient)).append("\",\n");
        json.append("    \"message\": \"").append(escapeJson(m.messageText)).append("\"\n");
        json.append("  }");
        if (i < storedMessages.size() - 1) {
            json.append(",");
        }
        json.append("\n");
    }

    json.append("]");

    try (FileWriter file = new FileWriter("storedMessages.json")) {
        file.write(json.toString());
    } catch (IOException e) {
        System.out.println("Could not save the message: " + e.getMessage());
    }
}

private String escapeJson(String value) {
    if (value == null) {
        return "";
    }
    return value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
}
}
