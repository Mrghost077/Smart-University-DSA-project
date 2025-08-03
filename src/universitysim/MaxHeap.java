/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitysim;

import java.util.ArrayList;

public class MaxHeap {
    private ArrayList<Event> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    public void insert(Event event) {
        heap.add(event);
        heapifyUp(heap.size() - 1);
    }

    public Event extractMax() {
        if (heap.isEmpty()) return null;

        Event max = heap.get(0);
        Event last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return max;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public Event peek() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parent)) > 0) {
                swap(index, parent);
                index = parent;
            } else break;
        }
    }

    private void heapifyDown(int index) {
        int left, right, largest;
        while (index < heap.size()) {
            left = 2 * index + 1;
            right = 2 * index + 2;
            largest = index;

            if (left < heap.size() && compare(heap.get(left), heap.get(largest)) > 0) {
                largest = left;
            }

            if (right < heap.size() && compare(heap.get(right), heap.get(largest)) > 0) {
                largest = right;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else break;
        }
    }

    private void swap(int i, int j) {
        Event temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Custom comparison: priority first, then start time
    private int compare(Event e1, Event e2) {
        int priComp = Integer.compare(e1.getPriorityValue(), e2.getPriorityValue());
        if (priComp != 0) return priComp;
        return Integer.compare(e2.startTime, e1.startTime); // earlier start wins
    }

    public void printHeap() {
        for (Event e : heap) {
            System.out.println(e);
        }
    }
    
    // ✅ Print events in true sorted order (priority → startTime)
    public void printSortedSchedule() {
        ArrayList<Event> copy = new ArrayList<>(heap); // Clone heap array

        // Sort based on priority and start time
        copy.sort((e1, e2) -> {
            int priCompare = Integer.compare(e2.getPriorityValue(), e1.getPriorityValue());
            if (priCompare != 0) return priCompare;
            return Integer.compare(e1.startTime, e2.startTime); // earlier events first
        });

        // Print each event in sorted order
        for (Event e : copy) {
            System.out.println(e);
        }
    }

}

