/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitysim;

import java.util.HashMap;

public class Graph {
    private HashMap<String, Vertex> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

   public void addVertex(String name) {
    if (!vertices.containsKey(name)) {
        vertices.put(name, new Vertex(name));
    }
}


    public void addEdge(String from, String to, int weight) {
        Vertex v1 = vertices.get(from);
        Vertex v2 = vertices.get(to);
        if (v1 == null || v2 == null) return;

        v1.addEdge(new Edge(v2, weight));
        v2.addEdge(new Edge(v1, weight)); 
    }

    public Vertex getVertex(String name) {
        return vertices.get(name);
    }

    public HashMap<String, Vertex> getVertices() {
        return vertices;
    }

    public void printGraph() {
        for (String name : vertices.keySet()) {
            Vertex v = vertices.get(name);
            System.out.print(v.name + " → ");
            for (Edge e : v.edges) {
                System.out.print(e.destination.name + "(" + e.weight + ") ");
            }
            System.out.println();
        }
    }
}

