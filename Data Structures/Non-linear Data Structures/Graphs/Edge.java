/*
  Description:
  --------------------
  Represents a weighted edge between two nodes in a graph.
 
  Use Cases:
  --------------------
  - Used in graph traversal and shortest path algorithms
  - Supports directed and undirected graphs
 
  Approach:
  --------------------
  Imagine a road connecting two cities with a distance (weight). Each edge stores
  the source, destination, and weight.
 */

public class Edge {
    Node src;
    Node dest;
    int weight;

    public Edge(Node src, Node dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return src.id + " -> " + dest.id + " (weight: " + weight + ")";
    }
}