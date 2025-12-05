/*
  Description:
  A* Search (pronounced “A-star”) is one of the most celebrated pathfinding
  algorithms in computer science. It’s widely used in navigation systems,
  robotics, puzzle solvers, games (like moving NPCs intelligently), and anywhere
  you need the shortest path with maximum efficiency.
 
  A* improves on Dijkstra’s Algorithm by introducing a heuristic — an estimate
  of how close you are to the goal. This helps the algorithm focus only on
  paths that *seem promising*, making it faster and more informed.
 
  Key Idea:
  For each node:
       f(n) = g(n) + h(n)
 
  g(n) — cost from the start node to n  
  h(n) — estimated cost from n to the goal (heuristic)  
 
  A good heuristic makes A* extremely powerful.
 
  Use Cases:
  - GPS navigation
  - Game development (smart movement, enemy AI)
  - Robotics and motion planning
  - Puzzle solving (e.g., 8-puzzle, 15-puzzle)
 
  Time Complexity:
  - Depends heavily on the heuristic.
  - Worst Case: O(E) similar to Dijkstra.
 
  Space Complexity:
  - O(V) for priority queue and tracking sets.
 
  Approach:
  1. Maintain:
       - openSet   → nodes to explore
       - closedSet → nodes already processed
       - gScore[]  → cost from start
       - fScore[]  → predicted total cost
  2. Pick the node in openSet with the lowest fScore.
  3. If it’s the goal, reconstruct and return the path.
  4. Otherwise:
       - Evaluate neighbors
       - Update their scores if a better path is found
       - Add them to openSet
  5. Repeat until goal is found or no path exists.
 */

import java.util.*;

public class AStar {

    // Interface so users can define their own heuristics:
    public interface Heuristic {
        int estimate(Node current, Node goal);
    }

    // Node record to carry priority queue data
    private static class NodeRecord {
        Node node;
        int fScore;

        public NodeRecord(Node node, int fScore) {
            this.node = node;
            this.fScore = fScore;
        }
    }

    public static List<Node> aStar(Graph graph, Node start, Node goal, Heuristic heuristic) {
        Map<Node, Integer> gScore = new HashMap<>();
        Map<Node, Integer> fScore = new HashMap<>();
        Map<Node, Node> cameFrom = new HashMap<>();

        Comparator<NodeRecord> comparator = Comparator.comparingInt(n -> n.fScore);
        PriorityQueue<NodeRecord> openSet = new PriorityQueue<>(comparator);
        Set<Node> closedSet = new HashSet<>();

        for (Node node : graph.getNodes()) {
            gScore.put(node, Integer.MAX_VALUE);
            fScore.put(node, Integer.MAX_VALUE);
        }

        gScore.put(start, 0);
        fScore.put(start, heuristic.estimate(start, goal));

        openSet.add(new NodeRecord(start, fScore.get(start)));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll().node;

            // Goal reached — reconstruct path
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            closedSet.add(current);

            for (Edge edge : graph.getEdgesFrom(current)) {
                Node neighbor = edge.to;

                if (closedSet.contains(neighbor))
                    continue;

                int tentativeG = gScore.get(current) + edge.weight;

                if (tentativeG < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeG);

                    int newF = tentativeG + heuristic.estimate(neighbor, goal);
                    fScore.put(neighbor, newF);

                    openSet.add(new NodeRecord(neighbor, newF));
                }
            }
        }

        // No path found
        return Collections.emptyList();
    }

    private static List<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
        List<Node> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }

        Collections.reverse(path);
        return path;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(false);

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");

        graph.addEdge(a, b, 4);
        graph.addEdge(a, c, 2);
        graph.addEdge(c, d, 5);
        graph.addEdge(b, e, 10);
        graph.addEdge(d, e, 2);

        // Heuristic example (dummy straight-line estimate)
        Heuristic h = (Node current, Node goal) -> Math.abs(current.label.compareTo(goal.label));

        System.out.println("Finding path from A to E...");
        List<Node> path = aStar(graph, a, e, h);

        if (path.isEmpty()) {
            System.out.println("No path exists.");
        } else {
            for (Node n : path) {
                System.out.print(n.label + " ");
            }
        }
    }
}