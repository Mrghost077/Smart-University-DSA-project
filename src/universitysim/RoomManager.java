/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitysim;

import java.util.*;

public class RoomManager {
    private HashTable roomTable;
    private HashMap<String, List<Event>> roomEvents;

    public RoomManager() {
        roomTable = new HashTable();
        roomEvents = new HashMap<>();
    }

    public void addRoom(String roomName) {
        if (roomTable.get(roomName) != null) {
            System.out.println("Room already exists.");
        } else {
            roomTable.put(roomName, true); // basic availability
            roomEvents.put(roomName, new ArrayList<>());
            System.out.println("Room added.");
        }
    }

    public boolean isRoomAvailable(String roomName) {
        Boolean available = roomTable.get(roomName);
        return available != null && available;
    }

    public void releaseRoom(String roomName) {
        roomTable.put(roomName, true);
    }

    public void printRooms() {
        roomTable.printAllRooms();
    }

    // ✅ Time overlap check
    public boolean hasConflict(String room, int newStart, int newEnd) {
        List<Event> events = roomEvents.get(room);
        if (events == null) return false;

        for (Event e : events) {
            if (newStart < e.endTime && newEnd > e.startTime) {
                return true; // overlap exists
            }
        }
        return false;
    }

    // ✅ Assign room only if no conflict
    public boolean assignRoomWithEvent(String room, Event event) {
        if (roomTable.get(room) == null) {
            System.out.println("Room doesn't exist.");
            return false;
        }

        if (hasConflict(room, event.startTime, event.endTime)) {
            System.out.println("Time conflict detected in room " + room + ".");
            return false;
        }

        roomEvents.get(room).add(event);
        System.out.println("Event scheduled in " + room + " from " + event.startTime + " to " + event.endTime);
        return true;
    }
}
