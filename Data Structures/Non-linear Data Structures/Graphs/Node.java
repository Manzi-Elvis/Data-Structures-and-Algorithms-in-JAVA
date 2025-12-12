/*
  Description:
  --------------------
  Represents a node in a graph. Each node stores a unique identifier and can
  maintain connections to other nodes (edges) in the graph.
 
  Use Cases:
  --------------------
  - Fundamental building block of graph structures
  - Used in graph traversal, shortest path, and connectivity algorithms
 
  Approach:
  --------------------
  Think of each city on a map as a node. Roads between cities are edges.
 */

import java.util.ArrayList;
import java.util.List;

public class Node {
    int id;
    List<Edge> edges; // list of outgoing edges

    public Node(int id) {
        this.id = id;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Node destination, int weight) {
        edges.add(new Edge(this, destination, weight));
    }

    @Override
    public String toString() {
        return "Node " + id;
    }
}