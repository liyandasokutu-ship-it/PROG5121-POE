/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    // Test Case 1 data from the brief
    private final String recipient1 = "+27718693002";
    private final String message1 = "Hi Mike, can you join us for dinner tonight?";

    // Test Case 2 data from the brief
    private final String recipient2 = "08575975889";
    private final String message2 = "Hi Keegan, did you receive the payment?";

    @Test
    public void testMessageLength_Success() {
        Message msg = new Message();
        assertEquals("Message ready to send.", msg.checkMessageLength(message1));
    }

    @Test
    public void testMessageLength_Failure() {
        Message msg = new Message();
        String longMessage = "a".repeat(260);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.checkMessageLength(longMessage));
    }

    @Test
    public void testRecipientCell_Success() {
        Message msg = new Message();
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell(recipient1));
    }

    @Test
    public void testRecipientCell_Failure() {
        Message msg = new Message();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell(recipient2));
    }

    @Test
    public void testMessageHash_TestCase1() {
        Message msg = new Message();
        assertEquals("00:0:HITONIGHT", msg.createMessageHash("0012345678", 0, message1));
    }

    @Test
    public void testMessageHash_Loop() {
        Message msg = new Message();
        String[] messages = {message1, message2};
        String[] expectedHashes = {"00:0:HITONIGHT", "00:1:HIPAYMENT"};

        for (int i = 0; i < messages.length; i++) {
            assertEquals(expectedHashes[i], msg.createMessageHash("0012345678", i, messages[i]));
        }
    }

    @Test
    public void testMessageID_Created() {
        Message msg = new Message();
        String id = msg.generateMessageID();
        assertEquals("Message ID generated: " + id, msg.getMessageIDMessage());
        assertEquals(10, id.length());
        assertTrue(msg.checkMessageID(id));
    }

    @Test
    public void testMessageSent_Send() {
        Message msg = new Message();
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testMessageSent_Disregard() {
        Message msg = new Message();
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    @Test
    public void testMessageSent_Store() {
        Message msg = new Message();
        msg.generateMessageID();
        msg.checkRecipientCell(recipient1);
        msg.setMessageText(message1);
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }

    @Test
    public void testReturnTotalMessages() {
        Message msg = new Message();
        int before = msg.returnTotalMessages();
        msg.sentMessage(1);
        assertEquals(before + 1, msg.returnTotalMessages());
    }

    @Test
    public void testPrintMessages_ContainsHash() {
        Message msg = new Message();
        String id = msg.generateMessageID();
        msg.checkRecipientCell(recipient1);
        msg.setMessageNumber(0);
        msg.setMessageText(message1);
        String hash = msg.createMessageHash(id, 0, message1);
        msg.sentMessage(1);
        assertTrue(msg.printMessages().contains(hash));
    }
}
