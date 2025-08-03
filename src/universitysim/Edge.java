/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitysim;

public class Edge {
    public Vertex destination;
    public int weight; // distance or time

    public Edge(Vertex destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

