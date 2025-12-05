/*
  Description:
  The Floyd–Warshall Algorithm is one of the most elegant and powerful algorithms
  in graph theory. Unlike Dijkstra or Bellman–Ford (which compute shortest paths 
  from a single source), Floyd–Warshall computes the shortest paths between 
  **every pair of nodes** in a graph.
 
  It works using a dynamic-programming style approach:
  - Start by assuming the direct edge between two nodes is the best path.
  - Gradually allow new "intermediate" nodes to be used.
  - Update shortest paths only if a better (cheaper) route is discovered.
 
  This algorithm shines in dense graphs or situations where you need **complete**
  shortest-path information rather than just one source.
 
  Use Cases:
  - Finding shortest paths between all pairs in a network.
  - Routing tables in communication networks.
  - Detecting negative cycles.
  - Computing reachability or transitive closure.
 
  Time Complexity:
  - O(V³) → not the fastest, but extremely powerful for dense graphs.
 
  Space Complexity:
  - O(V²) → distance matrix.
 
  Approach:
  1. Start with a V×V matrix where dist[i][j] = weight of edge (i→j) or ∞.
  2. Set dist[i][i] = 0 for all i.
  3. For each node k (as an intermediate):
         For each pair (i, j):
             If going through k improves the path i → k → j,
             update dist[i][j].
  4. After all improvements, check for dist[i][i] < 0 to detect negative cycles.
 */

import java.util.*;

public class FloydWarshall {

    public static final int INF = (int) 1e9;

    public static int[][] floydWarshall(Graph graph) {
        List<Node> nodes = new ArrayList<>(graph.getNodes());
        int V = nodes.size();

        int[][] dist = new int[V][V];

        // Step 1: Initialize distance matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        // Insert actual edge weights
        for (Edge edge : graph.getAllEdges()) {
            int from = nodes.indexOf(edge.from);
            int to = nodes.indexOf(edge.to);
            dist[from][to] = edge.weight;
        }

        // Step 2–4: Allow each node to be an intermediate
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {

                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Step 5: Detect negative cycles
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                throw new RuntimeException("Negative cycle detected in graph.");
            }
        }

        return dist;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(true);

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");

        graph.addEdge(a, b, 3);
        graph.addEdge(a, c, 8);
        graph.addEdge(b, d, 1);
        graph.addEdge(c, d, -4);
        graph.addEdge(d, a, 2);

        int[][] result = floydWarshall(graph);

        System.out.println("All-Pairs Shortest Paths Matrix:");
        for (int[] row : result) {
            for (int value : row) {
                if (value >= INF) System.out.print("INF ");
                else System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}