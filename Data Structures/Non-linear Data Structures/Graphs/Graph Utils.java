/*
  Description:
  --------------------
  Contains utility methods for graph algorithms like BFS, DFS, and shortest paths.
 
  Supports:
  - BFS (Breadth-First Search)
  - DFS (Depth-First Search)
 
  Use Cases:
  --------------------
  - Exploring all nodes in a network
  - Checking connectivity
  - Pathfinding
 
  Approach:
  --------------------
  BFS explores level by level, like waves spreading from a source node.
  DFS dives deep along a branch before backtracking, like exploring a maze.
 */

import java.util.*;

public class GraphUtils {

    // BFS traversal from a starting node
    public static void bfs(Node start) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        System.out.print("BFS: ");

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.id + " ");

            for (Edge edge : current.edges) {
                if (!visited.contains(edge.dest)) {
                    visited.add(edge.dest);
                    queue.add(edge.dest);
                }
            }
        }
        System.out.println();
    }

    // DFS traversal from a starting node
    public static void dfs(Node start) {
        Set<Node> visited = new HashSet<>();
        System.out.print("DFS: ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private static void dfsHelper(Node node, Set<Node> visited) {
        visited.add(node);
        System.out.print(node.id + " ");

        for (Edge edge : node.edges) {
            if (!visited.contains(edge.dest)) {
                dfsHelper(edge.dest, visited);
            }
        }
    }

    // DEMO
    public static void main(String[] args) {
        Graph g = new Graph(false);

        Node n1 = g.addNode(1);
        Node n2 = g.addNode(2);
        Node n3 = g.addNode(3);
        Node n4 = g.addNode(4);

        g.addEdge(n1, n2, 5);
        g.addEdge(n1, n3, 10);
        g.addEdge(n2, n4, 15);
        g.addEdge(n3, n4, 20);

        g.display();
        bfs(n1);
        dfs(n1);
    }
}