/*
  Description:
  --------------------
  A Binary Tree is a hierarchical data structure where each node has at most
  two children (left and right). This implementation provides basic creation
  and traversal methods.
 
  Supports:
  - Pre-order, In-order, Post-order traversal
  - Level-order traversal
 
  Use Cases:
  --------------------
  - Representing hierarchical data (file system, organization charts)
  - Expression trees (arithmetic expressions)
  - Basis for more advanced trees like BST, AVL, and heaps
 
  Time Complexity:
  --------------------
  - Traversal (any order): O(n)
  - Insertion (general, not BST): O(1) if inserting at first empty spot
 
  Approach:
  --------------------
  Imagine a family tree: each person (node) can have at most two children.
  Traversals define different ways to visit all family members.
 */

class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

public class BinaryTree {

    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    // PRE-ORDER: Root → Left → Right
    public void preOrder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // IN-ORDER: Left → Root → Right
    public void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }

    // POST-ORDER: Left → Right → Root
    public void postOrder(TreeNode node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }

    // LEVEL-ORDER using queue
    public void levelOrder(TreeNode node) {
        if (node == null) return;

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.value + " ");

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    // DEMO
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Manual creation
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        System.out.print("Pre-order: ");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.print("In-order: ");
        tree.inOrder(tree.root);
        System.out.println();

        System.out.print("Post-order: ");
        tree.postOrder(tree.root);
        System.out.println();

        System.out.print("Level-order: ");
        tree.levelOrder(tree.root);
        System.out.println();
    }
}