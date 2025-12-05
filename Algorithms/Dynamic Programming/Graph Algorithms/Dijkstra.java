/*
  Description:
  Dijkstra's Algorithm finds the shortest path from a starting node
  to all other nodes in a weighted graph (with non-negative weights).
 
  Use Cases:
  - Road networks, routing, and navigation.
  - When you need the shortest distance efficiently.
 
  Time Complexity:
  - O((V + E) log V) using a priority queue, where V = vertices, E = edges
 
  Space Complexity:
  - O(V + E) → adjacency list + distance map
 
  Approach:
  1. Initialize distances from start to all nodes as infinity.
  2. Set distance to start node as 0.
  3. Use a priority queue to pick the node with the smallest distance.
  4. Update distances of neighbors if a shorter path is found.
  5. Repeat until all nodes are visited.
 */

import java.util.*;

public class Dijkstra {

    public static Map<Node, Integer> dijkstra(Graph graph, Node start) {
        Map<Node, Integer> distances = new HashMap<>();
        for (Node node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingInt(nd -> nd.distance));
        pq.add(new NodeDistance(start, 0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            Node node = current.node;

            for (Edge edge : graph.getNeighbors(node)) {
                int newDist = distances.get(node) + edge.weight;
                if (newDist < distances.get(edge.to)) {
                    distances.put(edge.to, newDist);
                    pq.add(new NodeDistance(edge.to, newDist));
                }
            }
        }
        return distances;
    }

    private static class NodeDistance {
        Node node;
        int distance;

        NodeDistance(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true);

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");

        graph.addEdge(a, b, 1);
        graph.addEdge(a, c, 4);
        graph.addEdge(b, c, 2);
        graph.addEdge(b, d, 6);
        graph.addEdge(c, d, 3);

        Map<Node, Integer> distances = dijkstra(graph, a);
        System.out.println("Shortest distances from A:");
        distances.forEach((node, dist) -> System.out.println(node + ": " + dist));
    }
}