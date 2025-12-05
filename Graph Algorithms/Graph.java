/*
  Description:
   This Graph class is designed to support the algorithms in this repository,
   without hiding the logic behind unnecessary abstractions. Everything is
   intentionally kept readable for beginners while still being robust enough
   to power algorithms like Dijkstra, Bellman-Ford, Floyd-Warshall, BFS, DFS,
   and A* Search..

   The graph uses adjacency lists and supports both directed and undirected edges.
   Each edge stores a weight, making the structure suitable for weighted and
   unweighted graph problems.
 
  Why write my own Graph class?
  Because relying on built-in structures often hides what really happens.
  The goal here is learning — understanding the heart of every algorithm.

   Structure:
   - Node: represents a vertex.
   - Edge: represents a connection between nodes.
   - Graph: stores nodes and edges, and provides utility methods.
 
  Use Cases:
  - Pathfinding
  - Shortest-path algorithms
  - Traversal (DFS, BFS)
  - Network modeling

  Approach:
  1. Represent the graph as an adjacency list.
  2. Add edges using addEdge method.
  3. Access neighbors of a node efficiently for algorithms.
 */

import java.util.*;

public class Graph {
    private Map<Node, List<Edge>> adjList;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        adjList = new HashMap<>();
    }

    public void addNode(Node node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node from, Node to, int weight) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.putIfAbsent(to, new ArrayList<>());
        adjList.get(from).add(new Edge(to, weight));
        if (!directed) {
            adjList.get(to).add(new Edge(from, weight));
        }
    }

    public List<Edge> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Set<Node> getNodes() {
        return adjList.keySet();
    }

    public void printGraph() {
        for (Node node : adjList.keySet()) {
            System.out.print(node + " -> ");
            for (Edge edge : adjList.get(node)) {
                System.out.print("(" + edge.to + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }
}

class Edge {
    Node to;
    int weight;

    public Edge(Node to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}