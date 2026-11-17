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
        // Parallel arrays: the same position in each list belongs to the same message
    private static ArrayList<String> messageIDList = new ArrayList<>();
    private static ArrayList<String> messageHashList = new ArrayList<>();
    private static ArrayList<String> recipientList = new ArrayList<>();
    private static ArrayList<String> messageTextList = new ArrayList<>();
    private static ArrayList<String> messageFlagList = new ArrayList<>();

    // Arrays required by the brief, one for each message flag
    private static ArrayList<String> sentMessageList = new ArrayList<>();
    private static ArrayList<String> disregardedMessageList = new ArrayList<>();
    private static ArrayList<String> storedMessageList = new ArrayList<>();
    
    public String generateMessageID() {
    Random random = new Random();
    long number = (long) (random.nextDouble() * 10000000000L);

    // Convert to a String and pad with leading zeros so the ID is always 10 digits
    String digits = String.valueOf(number);
    while (digits.length() < 10) {
        digits = "0" + digits;
    }

    messageID = digits;
    return messageID;
}
    
    public String getMessageIDMessage() {
    return "Message ID generated: " + messageID;
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

        // Adds this message to the parallel arrays using its flag (Sent, Disregard or Stored)
    private void addToArrays(String flag) {
        messageIDList.add(messageID);
        messageHashList.add(messageHash);
        recipientList.add(recipient);
        messageTextList.add(messageText);
        messageFlagList.add(flag);

        if (flag.equals("Sent")) {
            sentMessageList.add(messageText);
        } else if (flag.equals("Disregard")) {
            disregardedMessageList.add(messageText);
        } else {
            storedMessageList.add(messageText);
        }
    }

    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                sentMessages.add(this);
                totalMessages++;
                addToArrays("Sent");
                return "Message successfully sent.";
            case 2:
                addToArrays("Disregard");
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                addToArrays("Stored");
                return "Message successfully stored.";
            default:
                return "Invalid option, please try again.";
        }
    }

    // Clears every list so the unit tests always start from an empty state
    public static void clearAllArrays() {
        sentMessages.clear();
        storedMessages.clear();
        totalMessages = 0;
        messageIDList.clear();
        messageHashList.clear();
        recipientList.clear();
        messageTextList.clear();
        messageFlagList.clear();
        sentMessageList.clear();
        disregardedMessageList.clear();
        storedMessageList.clear();
    }
    
    public String getMessageDetails() {
    return "Message ID: " + messageID + "\n"
         + "Message Hash: " + messageHash + "\n"
         + "Recipient: " + recipient + "\n"
         + "Message: " + messageText;
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

    // File writing approach adapted from TutorialsPoint (n.d.) How to write/create a JSON file using Java?
// Available at: https://www.tutorialspoint.com/how-to-write-create-a-json-file-using-java (Accessed: 17 October 2026).
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
