package com.mycompany.app;

import java.util.Arrays;

public class Challenge {

    public int start(int[][] meetings) {
        if (meetings.length == 0) {
            return 0;
        }

        int[] starts = new int[meetings.length];
        for (int i = 0; i < meetings.length; i++) {
            starts[i] = meetings[i][0];
        }
        Arrays.sort(starts);

        int[] ends = new int[meetings.length];
        for (int i = 0; i < meetings.length; i++) {
            ends[i] = meetings[i][1];
        }
        Arrays.sort(ends);

        int rooms = 0, endIndex = 0;

        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[endIndex]) {
                rooms++;
            } else {
                endIndex++;
            }
        }
        return rooms;
    }
}
