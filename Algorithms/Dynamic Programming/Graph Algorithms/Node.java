/* *
  Description:
  The Node class represents a single vertex in the graph. Instead of using basic
  integers or strings, having a dedicated Node object makes the entire graph
  structure more expressive, more readable, and easier to extend later.
 
  Every node simply holds a label (like "A", "B", "City1", etc.). The label is
  kept public for simplicity, since this repository focuses on learning the
  algorithms rather than hiding everything behind getters/setters.
 
  Why keep Node simple?
  Because most graph algorithms depend on *relationships* between nodes,
  not on the node itself. A clear, lightweight Node class helps remove the noise
  and keeps learners focused on the algorithmic logic.
 
  Use Cases:
  - DFS, BFS traversal
  - Dijkstra, Bellman-Ford, A* search
  - Floyd–Warshall matrix indexing
  - Graph visualization and debugging
 */

public class Node {

    public String label;

    public Node(String label) {
        this.label = label;
    }

    // Override for clean printing (A, B, City1, etc.)
    @Override
    public String toString() {
        return label;
    }

    // Nodes are compared by reference unless you override equals() and hashCode().
    // For learning purposes, we leave it simple and rely on object identity.
}