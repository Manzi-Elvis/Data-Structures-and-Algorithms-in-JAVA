/*
  Description:
  --------------------
  An AVL Tree is a self-balancing Binary Search Tree (BST) where the heights
  of two child subtrees of any node differ by at most one. If the balance
  factor becomes more than 1 or less than -1, rotations are performed to restore balance.
 
  Supports:
  - Insert
  - Delete
  - Search
  - In-order traversal
 
  Use Cases:
  --------------------
  - Situations requiring consistently fast search, insert, and delete
  - Databases and file systems
  - Maintaining sorted data dynamically
 
  Time Complexity:
  --------------------
  - Search, Insert, Delete: O(log n)
 
  Approach:
  --------------------
  Think of a bookshelf where you keep it perfectly balanced: if one side gets too heavy,
  you shift books to restore balance. Rotations (left, right, left-right, right-left)
  keep the tree height minimal.
 */

class AVLNode {
    int value, height;
    AVLNode left, right;

    AVLNode(int value) {
        this.value = value;
        height = 1;
    }
}

public class AVLTree {

    private AVLNode root;

    // GET HEIGHT
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // GET BALANCE
    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // RIGHT ROTATE
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // LEFT ROTATE
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // INSERT
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private AVLNode insertRec(AVLNode node, int value) {
        if (node == null) return new AVLNode(value);

        if (value < node.value) node.left = insertRec(node.left, value);
        else if (value > node.value) node.right = insertRec(node.right, value);
        else return node; // duplicates not allowed

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LEFT LEFT CASE
        if (balance > 1 && value < node.left.value) return rightRotate(node);

        // RIGHT RIGHT CASE
        if (balance < -1 && value > node.right.value) return leftRotate(node);

        // LEFT RIGHT CASE
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RIGHT LEFT CASE
        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // IN-ORDER TRAVERSAL
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(AVLNode node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.print(node.value + " ");
        inOrderRec(node.right);
    }

    // DEMO
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();

        int[] values = { 10, 20, 30, 40, 50, 25 };
        for (int v : values) avl.insert(v);

        System.out.print("AVL In-order (sorted): ");
        avl.inOrder();
    }
}