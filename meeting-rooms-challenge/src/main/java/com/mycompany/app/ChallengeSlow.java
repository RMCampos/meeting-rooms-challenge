package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChallengeSlow {

    public int start(int[][] meetings) {
        if (meetings.length == 0) {
            return 0;
        }

        // create initial room with 24 hours of availability (1440 minutes)
        Map<Integer, List<Boolean>> meetingRooms = createInitialRoom();

        // sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i=0; i<meetings.length; i++) {
            int meetingStart = meetings[i][0];
            int meetingEnd = meetings[i][1];

            System.out.println("Meeting " + (i+1) + ": " + meetingStart + " - " + meetingEnd);
            boolean roomAvailable = isRoomAvailable(meetingRooms, meetingStart, meetingEnd);
            System.out.println("Room available: " + roomAvailable);

            if (!roomAvailable) {
                // create a new room and mark it as occupied for the duration of the meeting
                meetingRooms = createNewRoom(meetingRooms);
                bookMinutes(meetingRooms, meetingRooms.size(), meetingStart, meetingEnd);
            } else {
                // mark the room as occupied for the duration of the meeting
                bookMinutes(meetingRooms, meetingRooms.size(), meetingStart, meetingEnd);
            }

        }

        return meetingRooms.size();
    }

    private Map<Integer, List<Boolean>> createInitialRoom() {
        HashMap<Integer, List<Boolean>> meetingRooms = new HashMap<>();
        meetingRooms.put(1, new ArrayList<>(Collections.nCopies(60 * 24, false)));
        return meetingRooms;
    }

    private Map<Integer, List<Boolean>> createNewRoom(Map<Integer, List<Boolean>> meetingRooms) {
        Integer newRoomNumber = meetingRooms.size() + 1;
        List<Boolean> newRoomSchedule = new ArrayList<>();
        newRoomSchedule.addAll(Collections.nCopies(60 * 24, false));
        meetingRooms.put(newRoomNumber, newRoomSchedule);
        return meetingRooms;
    }

    private Map<Integer, List<Boolean>> bookMinutes(Map<Integer, List<Boolean>> meetingRooms, int roomNumber, int start, int end) {
        List<Boolean> roomSchedule = meetingRooms.get(roomNumber);
        for (int i = start; i < end; i++) {
            roomSchedule.set(i, true);
        }
        return meetingRooms;
    }

    private boolean isRoomAvailable(Map<Integer, List<Boolean>> meetingRooms, int start, int end) {
        for (Map.Entry<Integer, List<Boolean>> entry : meetingRooms.entrySet()) {
            List<Boolean> roomSchedule = entry.getValue();
            boolean startTimeAvailable = !roomSchedule.get(start);
            boolean endTimeAvailable = !roomSchedule.get(end);
            // if startTimeAvailable is false, but the next minute is available, we can still use this room
            if (!startTimeAvailable && endTimeAvailable && !roomSchedule.get(start + 1)) {
                startTimeAvailable = true;
            }
            if (startTimeAvailable && endTimeAvailable) {
                // mark the room as occupied for the duration of the meeting
                for (int i = start; i < end; i++) {
                    roomSchedule.set(i, true);
                }
                return true;
            }
        }
        return false;
    }
}
