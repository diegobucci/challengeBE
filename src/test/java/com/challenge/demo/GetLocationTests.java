package com.challenge.demo;

import com.challenge.demo.model.Communication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetLocationTests {

    private Communication com = new Communication();

    @Test
    public void testGetLocationValidPoint() {
        float[] position = new float[2];
        float[] expected = {-200f, 300f};
        try {
            position = com.getLocation(583.2f, 500,728);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(expected[0],position[0], 0.3);
        assertEquals(expected[1],position[1], 0.3);
    }

    @Test
    public void testGetLocationInvalidPoint() {
        assertThrows(Exception.class, ()-> com.getLocation(53.2f, 500,728));
    }

}
