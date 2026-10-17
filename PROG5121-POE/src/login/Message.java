/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package login;

import java.util.Random;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    public String generateMessageID() {
    Random random = new Random();
    StringBuilder id = new StringBuilder();

    for (int i = 0; i < 10; i++) {
        id.append(random.nextInt(10));
    }

    messageID = id.toString();
    return messageID;
}

    public boolean checkMessageID(String id) {
    return id != null && id.length() <= 10;
}

    public String checkRecipientCell() {
        return "";
    }

    public String createMessageHash() {
        return "";
    }

    public String sentMessage() {
        return "";
    }

    public String printMessages() {
        return "";
    }

    public int returnTotalMessages() {
        return 0;
    }

    public void storeMessage() {
    }
}
