/*
  Description:
  --------------------
  Depth-First Search (DFS) is a graph traversal algorithm that explores
  as far as possible along each branch before backtracking. It can be
  implemented using recursion or an explicit stack.
 
  DFS is useful for exploring all possible paths, detecting cycles,
  and topological sorting.
 
  Use Cases:
  --------------------
  - Pathfinding in mazes or puzzles
  - Detecting cycles in graphs
  - Topological sorting in dependency graphs
  - Connected components in networks
 
  Time Complexity:
  --------------------
  - O(V + E) where V is the number of vertices and E is the number of edges
 
  Space Complexity:
  --------------------
  - O(V) for the visited set (recursion stack or explicit stack)
 
  Approach:
  --------------------
  1. Start from the source node
  2. Mark it as visited
  3. Recursively visit all unvisited neighbors
  4. Backtrack when no unvisited neighbors remain
 */

import java.util.*;

public class DFS {

    public static void dfs(Node start) {
        Set<Node> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
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
        Node n5 = g.addNode(5);

        g.addEdge(n1, n2, 1);
        g.addEdge(n1, n3, 1);
        g.addEdge(n2, n4, 1);
        g.addEdge(n3, n4, 1);
        g.addEdge(n4, n5, 1);

        g.display();

        dfs(n1);
    }
}