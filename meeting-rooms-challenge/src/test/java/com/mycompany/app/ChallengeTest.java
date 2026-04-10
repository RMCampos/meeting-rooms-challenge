package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ChallengeTest {

    @Test
    void testStart() {
        Challenge challenge = new Challenge();

        Assertions.assertEquals(2, challenge.start(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        Assertions.assertEquals(3, challenge.start(new int[][]{{0, 60}, {5, 10}, {15, 20}}));
        Assertions.assertEquals(4, challenge.start(new int[][]{{0, 60}, {5, 10}, {15, 20}, {0, 25}}));
    }
}
