/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitysim;

public class Event {
    public String eventID;
    public String type; 
    public String location;
    public int startTime; 
    public int endTime;
    public String priority; 
    public int capacity;

    public Event(String eventID, String type, String location, int startTime, int endTime, String priority, int capacity) {
        this.eventID = eventID;
        this.type = type;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.capacity = capacity;
    }

    // Convert priority to a numeric value for comparison
    public int getPriorityValue() {
        switch (priority.toLowerCase()) {
            case "mandatory": return 3;
            case "high-profile": return 2;
            case "optional": return 1;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return "[" + priority + "] " + eventID + " (" + type + ") at " + location +
                " from " + startTime + " to " + endTime + " (Capacity: " + capacity + ")";
    }
}

/**
 *
 * @author DELL
 */
