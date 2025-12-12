/*
  Description:
  --------------------
  Breadth-First Search (BFS) is a graph traversal algorithm that explores
  nodes level by level. It starts at a given source node and visits all
  neighboring nodes before moving to the next level.
 
  BFS guarantees the shortest path in unweighted graphs.
 
  Use Cases:
  --------------------
  - Finding shortest paths in unweighted graphs
  - Social networks (finding friends within N degrees)
  - Web crawling
  - Solving puzzles or games level by level
 
  Time Complexity:
  --------------------
  - O(V + E) where V is the number of vertices and E is the number of edges
 
  Space Complexity:
  --------------------
  - O(V) for the visited set and queue
 
  Approach:
  --------------------
  1. Start with a queue containing the source node.
  2. Mark the source as visited.
  3. While the queue is not empty:
       a. Dequeue a node
       b. Process it
       c. Enqueue all unvisited neighbors
 */

import java.util.*;

public class BFS {

    public static void bfs(Node start) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.id + " ");

            for (Edge edge : current.edges) {
                if (!visited.contains(edge.dest)) {
                    queue.add(edge.dest);
                    visited.add(edge.dest);
                }
            }
        }
        System.out.println();
    }

    // DEMO
    public static void main(String[] args) {
        Graph g = new Graph(false);

        Node n1 = g.addNode(1);
        Node n2 = g.addNode(2);
        Node n3 = g.addNode(3);
        Node n4 = g.addNode(4);
        Node n5 = g.addNode(5);

        g.addEdge(n1, n2, 1);
        g.addEdge(n1, n3, 1);
        g.addEdge(n2, n4, 1);
        g.addEdge(n3, n4, 1);
        g.addEdge(n4, n5, 1);

        g.display();

        bfs(n1);
    }
}