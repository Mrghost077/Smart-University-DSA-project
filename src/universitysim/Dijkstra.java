/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitysim;

import java.util.*;

public class Dijkstra {
    public static void findShortestPath(Graph graph, String start, String end) {
        HashMap<String, Integer> distances = new HashMap<>();
        HashMap<String, String> previous = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String vertex : graph.getVertices().keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
            previous.put(vertex, null);
        }

        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            Vertex currentVertex = graph.getVertex(current);

            for (Edge edge : currentVertex.edges) {
                String neighbor = edge.destination.name;
                int newDist = distances.get(current) + edge.weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // Output
        if (distances.get(end) == Integer.MAX_VALUE) {
            System.out.println("No path found from " + start + " to " + end);
            return;
        }

        System.out.println("Shortest distance from " + start + " to " + end + " is " + distances.get(end));
        System.out.print("Path: ");
        printPath(previous, end);
        System.out.println();
    }

    private static void printPath(HashMap<String, String> previous, String current) {
        if (previous.get(current) != null) {
            printPath(previous, previous.get(current));
        }
        System.out.print(current + " ");
    }
}

