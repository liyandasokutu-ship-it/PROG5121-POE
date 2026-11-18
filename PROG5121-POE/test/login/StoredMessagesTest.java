package login;

import java.io.File;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StoredMessagesTest {

    // Test data from the Part 3 brief
    private static final String RECIPIENT_1 = "+27834557896";
    private static final String TEXT_1 = "Did you get the cake?";
    private static final String RECIPIENT_2 = "+27838884567";
    private static final String TEXT_2 = "Where are you? You are late! I have asked you to be on time.";
    private static final String RECIPIENT_3 = "+27834484567";
    private static final String TEXT_3 = "Yohoooo, I am at your gate.";
    private static final String RECIPIENT_4 = "0838884567";
    private static final String TEXT_4 = "It is dinner time!";
    private static final String RECIPIENT_5 = "+27838884567";
    private static final String TEXT_5 = "Ok, I am leaving without you.";

    // Creates one message and sends it with a flag: 1 = Sent, 2 = Disregard, 3 = Stored
    private void addTestMessage(int number, String recipient, String text, int flag) {
        Message msg = new Message();
        String id = msg.generateMessageID();
        msg.setRecipient(recipient);
        msg.setMessageText(text);
        msg.setMessageNumber(number);
        msg.createMessageHash(id, number, text);
        msg.sentMessage(flag);
    }

    @Before
    public void setUp() {
        Message.clearAllArrays();
        addTestMessage(0, RECIPIENT_1, TEXT_1, 1);
        addTestMessage(1, RECIPIENT_2, TEXT_2, 3);
        addTestMessage(2, RECIPIENT_3, TEXT_3, 2);
        addTestMessage(3, RECIPIENT_4, TEXT_4, 1);
        addTestMessage(4, RECIPIENT_5, TEXT_5, 3);
    }

    @After
    public void tearDown() {
        Message.clearAllArrays();
        new File("storedMessages.json").delete();
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        assertEquals(Arrays.asList(TEXT_1, TEXT_4), Message.getSentMessageList());
    }

    @Test
    public void testDisregardedMessagesArrayPopulated() {
        assertEquals(Arrays.asList(TEXT_3), Message.getDisregardedMessageList());
    }

    @Test
    public void testStoredMessagesArrayPopulated() {
        assertEquals(Arrays.asList(TEXT_2, TEXT_5), Message.getStoredMessageList());
    }

    @Test
    public void testMessageIDAndHashArraysPopulated() {
        assertEquals(5, Message.getMessageIDList().size());
        assertEquals(5, Message.getMessageHashList().size());

        // The hash starts with the first two digits of the ID at the same position
        String id = Message.getMessageIDList().get(0);
        assertTrue(Message.getMessageHashList().get(0).startsWith(id.substring(0, 2)));
    }

    @Test
    public void testDisplayLongestMessage() {
        assertEquals(TEXT_2, Message.getLongestStoredMessage());
    }

    @Test
    public void testSearchForMessageID() {
        String id = Message.getMessageIDList().get(3);
        assertEquals(TEXT_4, Message.searchByMessageID(id));
        assertEquals(RECIPIENT_4, Message.getRecipientByID(id));
    }

    @Test
    public void testSearchForMessageIDNotFound() {
        assertEquals("Message ID not found.", Message.searchByMessageID("0000000000"));
    }

    @Test
    public void testSearchMessagesForRecipient() {
        assertEquals(Arrays.asList(TEXT_2, TEXT_5), Message.searchByRecipient("+27838884567"));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        String hash = Message.getMessageHashList().get(1);
        String result = Message.deleteByHash(hash);

        assertEquals("Message: \"" + TEXT_2 + "\" successfully deleted.", result);
        assertFalse(Message.getStoredMessageList().contains(TEXT_2));
        assertEquals(4, Message.getMessageHashList().size());
    }

    @Test
    public void testDeleteMessageHashNotFound() {
        assertEquals("Message hash not found.", Message.deleteByHash("00:9:NOHASH"));
    }

    @Test
    public void testDisplayReport() {
        String report = Message.getReport();
        String hash = Message.getMessageHashList().get(0);

        assertTrue(report.contains("Message Hash: " + hash));
        assertTrue(report.contains("Recipient: " + RECIPIENT_1));
        assertTrue(report.contains("Message: " + TEXT_1));
        assertFalse(report.contains(TEXT_3));
    }

    @Test
    public void testLoadStoredMessagesFromJsonFile() {
        // setUp already wrote messages 2 and 5 to storedMessages.json
        Message.clearAllArrays();
        Message.loadStoredMessages();
        assertEquals(Arrays.asList(TEXT_2, TEXT_5), Message.getStoredMessageList());
    }
}