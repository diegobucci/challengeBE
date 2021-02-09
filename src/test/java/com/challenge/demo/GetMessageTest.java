package com.challenge.demo;

import com.challenge.demo.exception.InvalidMessagesException;
import com.challenge.demo.util.Communication;
import com.challenge.demo.util.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetMessageTest {

    @Test
    void testGetMessageValidWith2Coincidence() {
        String helpMessage = null;
        String [] msg1 = {"este", "", "", "mensaje"};
        String [] msg2 = {"", "es", "un", ""};
        String [] msg3 = {"este", "", "un",""};

        try {
            helpMessage = Message.getMessage(msg1, msg2, msg3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("este es un mensaje",helpMessage);
    }

    @Test
    void testGetMessageValidWithLeftAndRightShift() {
        String helpMessage = "";
        String [] msg1 = {"","","este", "", "", "mensaje"};
        String [] msg2 = {"","","", "es", "un", ""};
        String [] msg3 = {"este", "", "un","", "","",""};

        try {
            helpMessage = Message.getMessage(msg1, msg2, msg3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("este es un mensaje",helpMessage);
    }

    @Test
    void testGetMessageInvalid() {
        String [] msg1 = {"","","", "", "", "mensaje"};
        String [] msg2 = {"","","", "es", "un", ""};
        String [] msg3 = {"este", "", "un","", "","",""};

        assertThrows(Exception.class, ()-> Message.getMessage(msg1, msg2, msg3));
    }

    @Test
    void testGetMessageInvalidNoCoincidence() {
        String [] msg1 = {"","","", "", "", "mensaje"};
        String [] msg2 = {"","","", "es", "", ""};
        String [] msg3 = {"este", "", "un","", "","",""};

        assertThrows(Exception.class, ()-> Message.getMessage(msg1, msg2, msg3));
    }

    @Test
    void testSetMessageInvalid() {
        String [] msg1 = null;
        String [] msg2 = null;
        String [] msg3 = null;

        assertThrows(InvalidMessagesException.class, ()-> Message.getMessage(msg1, msg2, msg3));
    }

    @Test
    void testSetNullMessageValidResponse() {
        String helpMessage = "";
        String [] msg1 = null;
        String [] msg2 = {"","","", "es", "un", ""};
        String [] msg3 = {"este", "es", "un","", "","",""};

        try {
            helpMessage = Message.getMessage(msg1, msg2, msg3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("este es un",helpMessage);
    }

}
