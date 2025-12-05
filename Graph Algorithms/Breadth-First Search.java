/*
  Description:
  Breadth-First Search explores neighbors level by level before moving deeper.
 
  Use Cases:
  - Shortest path in unweighted graphs
  - Connectivity checks
 
  Time Complexity:
  - O(V + E)
 */

import java.util.*;

public class BFS {

    public static void bfs(Graph graph, Node start) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println("Visited: " + node);

            for (Edge edge : graph.getNeighbors(node)) {
                if (!visited.contains(edge.to)) {
                    visited.add(edge.to);
                    queue.add(edge.to);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(false);

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");

        graph.addEdge(a, b, 1);
        graph.addEdge(a, c, 1);
        graph.addEdge(b, d, 1);
        graph.addEdge(c, d, 1);

        System.out.println("BFS Traversal:");
        bfs(graph, a);
    }
}
