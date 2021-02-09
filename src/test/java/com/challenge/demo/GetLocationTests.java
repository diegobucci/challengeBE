package com.challenge.demo;

import com.challenge.demo.util.Communication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetLocationTests {

    @Test
    void testGetLocationValidPoint() {
        float[] position = new float[2];
        float[] expected = {-200f, 300f};
        try {
            position = Communication.getLocation(583.2f, 500,728);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(expected[0],position[0], 0.3);
        assertEquals(expected[1],position[1], 0.3);
    }

    @Test
    void testGetLocationInvalidPoint() {
        assertThrows(Exception.class, ()-> Communication.getLocation(53.2f, 500,728));
    }

}
