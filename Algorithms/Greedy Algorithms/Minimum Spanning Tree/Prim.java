/*
  Description:
  --------------------
  Prim’s algorithm builds a Minimum Spanning Tree (MST) by always selecting 
  the smallest-weight edge that connects a visited node to an unvisited node.
 
  Unlike Kruskal, which sorts all edges, Prim grows the MST like a tree 
  expansion process — starting from one node and always choosing the next 
  cheapest connection.
 
  Why It Works:
  --------------------
  By repeatedly choosing the cheapest edge that expands the tree safely,
  Prim guarantees the minimum-cost spanning tree while maintaining 
  efficiency using Priority Queues.
 
  Use Cases:
  --------------------
  - Network routing systems
  - Designing least-cost networks (fiber optics, roads, pipelines)
  - Anytime you want an MST but prefer node-based expansion instead of edge sorting
 
  Time Complexity:
  --------------------
  - Using PriorityQueue: O(E log V)
 */

import java.util.*;

public class Prim {

    static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight; // min-heap behavior
        }
    }

    public static void primMST(int[][] graph) {
        int V = graph.length;

        boolean[] visited = new boolean[V]; // track visited nodes
        int[] parent = new int[V];          // store MST structure
        int[] key = new int[V];             // minimum edge weight to reach a node

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;          // start from node 0
        parent[0] = -1;      // root of MST

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0)); // start with node 0

        while (!pq.isEmpty()) {
            int u = pq.poll().node;

            if (visited[u]) continue;
            visited[u] = true;

            // Explore neighbors
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !visited[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                    pq.add(new Edge(v, key[v]));
                }
            }
        }

        // Display MST
        System.out.println("Minimum Spanning Tree (Prim):");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " -- " + i + "  (weight: " + graph[i][parent[i]] + ")");
        }
    }

    public static void main(String[] args) {

        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        primMST(graph);
    }
}