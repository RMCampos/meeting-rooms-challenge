package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ChallengeTest {

    @Test
    void testStart() {
        Challenge challenge = new Challenge();

        Assertions.assertEquals(0, challenge.start(new int[][]{}));
        Assertions.assertEquals(2, challenge.start(new int[][]{{0, 30}, {5, 10}}));
        Assertions.assertEquals(2, challenge.start(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        Assertions.assertEquals(3, challenge.start(new int[][]{{0, 10}, {0, 10}, {0, 10}}));
        // Should be 1: Meeting ends exactly when the next begins
        Assertions.assertEquals(1, challenge.start(new int[][]{{0, 10}, {10, 20}}));
        // Should be 1: Plenty of time between them
        Assertions.assertEquals(1, challenge.start(new int[][]{{0, 5}, {10, 15}, {20, 25}}));
        // Should be 2: The second meeting is entirely inside the first
        Assertions.assertEquals(2, challenge.start(new int[][]{{0, 50}, {10, 20}}));
        // Should be 2: 
        // Room 1: [0, 10], then [15, 25] (Reused)
        // Room 2: [5, 20] (Occupied during the gap in Room 1)
        Assertions.assertEquals(2, challenge.start(new int[][]{{0, 10}, {5, 20}, {15, 25}}));
        // Should be 4: All four meetings exist simultaneously at minute 11
        Assertions.assertEquals(4, challenge.start(new int[][]{
            {0, 30},
            {5, 15}, 
            {10, 20}, 
            {11, 12}
        }));
        // Should be 2: Same as your example, but shuffled
        Assertions.assertEquals(2, challenge.start(new int[][]{{15, 20}, {5, 10}, {0, 30}}));
    }
}
