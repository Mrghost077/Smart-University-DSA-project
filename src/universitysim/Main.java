/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitysim;

import java.util.Scanner;



public class Main {
    
    // Safe integer input with retry
    public static int readInt(Scanner sc, String prompt) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(sc.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter an integer like 900 or 1430.");
            }
        }
        return value;
    }

    // Safe string input with trimming
    public static String readString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MaxHeap eventManager = new MaxHeap();
        RoomManager roomManager = new RoomManager();
        Graph campusMap = new Graph();

        System.out.println("==== SMART UNIVERSITY SYSTEM ====");

        boolean running = true;
        while (running) {
            System.out.println("\nMENU:");
            System.out.println("1. Add Event");
            System.out.println("2. View Event Schedule");
            System.out.println("3. Add Room");
            System.out.println("4. Assign Room");
            System.out.println("5. View Room Availability");
            System.out.println("6. Add Building & Path");
            System.out.println("7. Show Campus Map");
            System.out.println("8. Find Shortest Route");
            System.out.println("9. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                String id = readString(sc, "Event ID: ");
                String type = readString(sc, "Type (Class/Seminar/Meeting): ");
                String loc = readString(sc, "Location (Room): ");
                int start = readInt(sc, "Start Time (e.g., 900): ");
                int end = readInt(sc, "End Time (e.g., 1100): ");
                String pri = readString(sc, "Priority (Mandatory/High-profile/Optional): ");
                int cap = readInt(sc, "Capacity: ");

                Event event = new Event(id, type, loc, start, end, pri, cap);

                // ✅ Assign room ONLY if no conflict
                if (roomManager.assignRoomWithEvent(loc, event)) {
                    eventManager.insert(event);
                } else {
                    System.out.println("Event not added due to room/time conflict.");
                }
                break;

                case 2:
                    if (eventManager.isEmpty()) {
                        System.out.println("No events scheduled yet.");
                    } else {
                         System.out.println("=== Event Schedule by Priority ===");
                        eventManager.printSortedSchedule();
                        }
                    break;


                case 3:
                    System.out.print("Enter room name: ");
                    String room = sc.nextLine();
                    roomManager.addRoom(room);
                    System.out.println("Room added.");
                    break;

                case 4:
                    System.out.println(" Rooms are now assigned automatically when you add an event.");
                    break;



                case 5:
                    System.out.println("=== Room Availability ===");
                    roomManager.printRooms();
                    break;

                case 6:
                    System.out.print("Enter building name to add: ");
                    String building = sc.nextLine();
                    campusMap.addVertex(building);
                    System.out.print("Connect to building: ");
                    String connect = sc.nextLine();
                    System.out.print("Enter distance: ");
                    int weight = sc.nextInt();
                    sc.nextLine();

                    campusMap.addVertex(connect); // safe add
                    campusMap.addEdge(building, connect, weight);
                    System.out.println("Path added.");
                    break;

                case 7:
                    System.out.println("=== CAMPUS MAP ===");
                    campusMap.printGraph();
                    break;

               case 8:
                    String from = readString(sc, "From: ");
                    String to = readString(sc, "To: ");
             
                    if (campusMap.getVertex(from) == null) {
                        System.out.println("Source building '" + from + "' does not exist.");
                    break;
                    }

                    if (campusMap.getVertex(to) == null) {
                        System.out.println("Destination building '" + to + "' does not exist.");
                    break;
                    }

                    System.out.println("=== SHORTEST PATH ===");
                    Dijkstra.findShortestPath(campusMap, from, to);
                    break;


                case 9:
                    running = false;
                    System.out.println("Goodbye, Captain!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        sc.close();
    }
}
