/*
  Description:
  --------------------
  A Graph is a collection of nodes (vertices) and edges connecting them.
  This implementation supports both directed and undirected graphs.
 
  Supports:
  - Add nodes and edges
  - Display the graph
 
  Use Cases:
  --------------------
  - Modeling networks (social networks, computer networks)
  - Pathfinding (maps, GPS)
  - Scheduling and dependencies
 
  Approach:
  --------------------
  Think of a map: cities are nodes, roads are edges. We can navigate, measure distances,
  and analyze connections efficiently.
 */

import java.util.ArrayList;
import java.util.List;

public class Graph {

    List<Node> nodes;
    boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        this.nodes = new ArrayList<>();
    }

    public Node addNode(int id) {
        Node node = new Node(id);
        nodes.add(node);
        return node;
    }

    public void addEdge(Node src, Node dest, int weight) {
        src.addEdge(dest, weight);
        if (!directed) dest.addEdge(src, weight);
    }

    public void display() {
        for (Node node : nodes) {
            System.out.print(node + " -> ");
            for (Edge edge : node.edges) {
                System.out.print(edge.dest.id + "(" + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    // DEMO
    public static void main(String[] args) {
        Graph g = new Graph(false);

        Node n1 = g.addNode(1);
        Node n2 = g.addNode(2);
        Node n3 = g.addNode(3);

        g.addEdge(n1, n2, 10);
        g.addEdge(n2, n3, 20);
        g.addEdge(n3, n1, 30);

        g.display();
    }
}