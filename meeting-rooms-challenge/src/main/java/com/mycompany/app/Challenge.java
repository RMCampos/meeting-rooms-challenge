package com.mycompany.app;

public class Challenge {

    public int start(int[][] meetings) {
        int numberOfRooms = 0;
        int romMaxDurationMinutes = 30;
        int totalMinutes = 0;

        for (int i=0; i<meetings.length; i++) {
            int meetingStart = meetings[i][0];
            int meetingEnd = meetings[i][1];

            System.out.println("Meeting " + i + ": " + meetingStart + " - " + meetingEnd);

            int duration = meetingEnd - meetingStart;
            totalMinutes += duration;
        }
        if (totalMinutes > romMaxDurationMinutes) {
            numberOfRooms = (int) Math.ceil((double) totalMinutes / romMaxDurationMinutes);
        } else {
            numberOfRooms = 1;
        }
        return numberOfRooms;
    }
}
