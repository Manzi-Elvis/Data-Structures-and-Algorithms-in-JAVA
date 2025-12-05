/*
 Description:
  Depth-First Search explores as far as possible along each branch
  before backtracking. Perfect for traversing or searching tree/graph structures.
 
  Use Cases:
  - Pathfinding, cycle detection, connectivity checks.
  - Topological sorting in directed acyclic graphs (DAGs).
  - Solving puzzles with only one solution, like mazes.

 
  Time Complexity:
  - O(V + E) where V = vertices, E = edges
 */

import java.util.*;

public class DFS {

    public static void dfs(Graph graph, Node start) {
        Set<Node> visited = new HashSet<>();
        dfsHelper(start, visited, graph);
    }

    private static void dfsHelper(Node node, Set<Node> visited, Graph graph) {
        visited.add(node);
        System.out.println("Visited: " + node);

        for (Edge edge : graph.getNeighbors(node)) {
            if (!visited.contains(edge.to)) {
                dfsHelper(edge.to, visited, graph);
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

        System.out.println("DFS Traversal:");
        dfs(graph, a);
    }
}