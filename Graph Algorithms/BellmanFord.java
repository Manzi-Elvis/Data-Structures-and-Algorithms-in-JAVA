/*
  Description:
  The Bellman–Ford Algorithm is a classic shortest-path algorithm that—unlike Dijkstra—
  can handle negative edge weights. This makes it extremely powerful when modeling
  real-world systems that involve losses, drops, or penalties.
 
  It works by repeatedly relaxing all edges, gradually correcting inaccurate distance
  estimates until they converge to the optimal solution.
 
  Bellman–Ford can also detect negative weight cycles, something Dijkstra cannot do.
 
  Use Cases:
  - Graphs with negative edge weights.
  - Financial models (profit/loss transitions).
  - Detecting negative cycles (fraud loops, impossibility loops).
  - Learning how shortest-path relaxation works at a deeper level.
 
  Time Complexity:
  - O(V * E) → slower than Dijkstra, but more powerful.
 
  Space Complexity:
  - O(V) → distance array/map.
 
  Approach:
  1. Set distances to all nodes as infinity, except the start node (0).
  2. Relax all edges V-1 times (where V is the number of vertices).
     This ensures all shortest paths settle into their correct values.
  3. Run one extra relaxation:
     - If anything improves again, a negative cycle exists.
 */

import java.util.*;

public class BellmanFord {

    public static Map<Node, Integer> bellmanFord(Graph graph, Node start) {
        Map<Node, Integer> distances = new HashMap<>();

        for (Node node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        List<Edge> allEdges = graph.getAllEdges();

        int V = graph.getNodes().size();

        // Step 1–V: Relax all edges repeatedly
        for (int i = 1; i < V; i++) {
            for (Edge edge : allEdges) {
                Node u = edge.from;
                Node v = edge.to;
                int weight = edge.weight;

                if (distances.get(u) != Integer.MAX_VALUE &&
                    distances.get(u) + weight < distances.get(v)) {
                    distances.put(v, distances.get(u) + weight);
                }
            }
        }

        // Extra pass: detect negative cycle
        for (Edge edge : allEdges) {
            Node u = edge.from;
            Node v = edge.to;
            int weight = edge.weight;

            if (distances.get(u) != Integer.MAX_VALUE &&
                distances.get(u) + weight < distances.get(v)) {
                throw new RuntimeException("Negative weight cycle detected.");
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true);

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");

        graph.addEdge(a, b, 4);
        graph.addEdge(a, c, 5);
        graph.addEdge(b, c, -3);
        graph.addEdge(c, d, 4);
        graph.addEdge(d, b, -6); // Creates a negative cycle

        try {
            Map<Node, Integer> result = bellmanFord(graph, a);
            System.out.println("Shortest distances from A:");
            result.forEach((node, dist) -> System.out.println(node + ": " + dist));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}