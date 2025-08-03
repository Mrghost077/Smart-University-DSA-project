/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitysim;

public class HashTable {
    private final int SIZE = 50;
    private String[] keys;
    private Boolean[] values;

    public HashTable() {
        keys = new String[SIZE];
        values = new Boolean[SIZE];
    }

    private int hash(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash += c;
        }
        return hash % SIZE;
    }

    public void put(String room, boolean available) {
        int index = hash(room);
        int start = index;

        while (keys[index] != null && !keys[index].equals(room)) {
            index = (index + 1) % SIZE;
            if (index == start) {
                System.out.println("HashTable full");
                return;
            }
        }

        keys[index] = room;
        values[index] = available;
    }

    public Boolean get(String room) {
        int index = hash(room);
        int start = index;

        while (keys[index] != null) {
            if (keys[index].equals(room)) {
                return values[index];
            }
            index = (index + 1) % SIZE;
            if (index == start) break;
        }
        return null;
    }

    public void printAllRooms() {
        for (int i = 0; i < SIZE; i++) {
            if (keys[i] != null) {
                System.out.println("Room: " + keys[i] + " | Available: " + values[i]);
            }
        }
    }
}

