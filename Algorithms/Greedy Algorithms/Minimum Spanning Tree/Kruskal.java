/*
  Description:
  --------------------
  Kruskal’s algorithm builds a Minimum Spanning Tree (MST) by always choosing
  the edge with the smallest weight that does NOT form a cycle.
 
  It uses the Disjoint Set Union (Union-Find) structure to efficiently
  track connected components and detect cycles.
 
  Why It Works:
  --------------------
  By always picking the lightest safe edge, Kruskal guarantees a globally
  minimal total cost — a classic greedy strategy that’s both elegant
  and powerful.
  
   Use Cases:
  --------------------
  - Network design (internet cables, water pipelines, electrical grids)
  - Reducing cost in connected systems
  - Cluster analysis in machine learning
 
  Time Complexity:
  --------------------
  - Sorting edges: O(E log E)
  - Union-Find operations: almost O(1)
  
  Total: O(E log E)
 */

import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class DisjointSet {
    int[] parent, rank;

    DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i; // each node is its own parent
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

public class Kruskal {

    public static void kruskalMST(List<Edge> edges, int vertices) {

        // Step 1: Sort edges by weight ASC
        edges.sort(Comparator.comparingInt(e -> e.weight));

        DisjointSet ds = new DisjointSet(vertices);

        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        // Step 2: Pick smallest edges that don't create cycles
        for (Edge edge : edges) {
            int rootA = ds.find(edge.src);
            int rootB = ds.find(edge.dest);

            if (rootA != rootB) {
                mst.add(edge);
                totalWeight += edge.weight;
                ds.union(rootA, rootB);
            }
        }

        // Display MST
        System.out.println("Minimum Spanning Tree (Kruskal):");
        for (Edge e : mst) {
            System.out.println(e.src + " -- " + e.dest + "  (weight: " + e.weight + ")");
        }

        System.out.println("Total Weight: " + totalWeight);
    }

    public static void main(String[] args) {

        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 4),
                new Edge(0, 2, 3),
                new Edge(1, 2, 1),
                new Edge(1, 3, 2),
                new Edge(2, 3, 4),
                new Edge(3, 4, 2),
                new Edge(4, 5, 6)
        );

        int numberOfVertices = 6;

        kruskalMST(edges, numberOfVertices);
    }
}