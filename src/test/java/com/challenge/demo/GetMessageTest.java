package com.challenge.demo;

import com.challenge.demo.model.Communication;
import com.challenge.demo.model.Fleet;
import com.challenge.demo.model.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetMessageTest {
    private Communication com = new Communication();

    @Test
    public void testGetMessageValidWith2Coincidence() {
        String helpMessage = new String();
        String [] msg1 = {"este", "", "", "mensaje"};
        String [] msg2 = {"", "es", "un", ""};
        String [] msg3 = {"este", "", "un",""};

        try {
            helpMessage = com.getMessage(msg1, msg2, msg3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("este es un mensaje",helpMessage);
    }

    @Test
    public void testGetMessageValidWithLeftAndRightShift() {
        String helpMessage = new String();
        String [] msg1 = {"","","este", "", "", "mensaje"};
        String [] msg2 = {"","","", "es", "un", ""};
        String [] msg3 = {"este", "", "un","", "","",""};

        try {
            helpMessage = com.getMessage(msg1, msg2, msg3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("este es un mensaje",helpMessage);
    }

    @Test
    public void testGetMessageInvalid() {
        String [] msg1 = {"","","", "", "", "mensaje"};
        String [] msg2 = {"","","", "es", "un", ""};
        String [] msg3 = {"este", "", "un","", "","",""};

        assertThrows(Exception.class, ()-> com.getMessage(msg1, msg2, msg3));
    }

    @Test
    public void testGetMessageInvalidNoCoincidence() {
        String [] msg1 = {"","","", "", "", "mensaje"};
        String [] msg2 = {"","","", "es", "", ""};
        String [] msg3 = {"este", "", "un","", "","",""};

        assertThrows(Exception.class, ()-> com.getMessage(msg1, msg2, msg3));
    }

}
