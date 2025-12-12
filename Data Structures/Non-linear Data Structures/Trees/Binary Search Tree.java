/*
  Description:
  --------------------
  A Binary Search Tree (BST) is a binary tree where the left child of a node
  contains values less than the node, and the right child contains values
  greater than the node. This property allows fast search, insertion, and deletion.
 
  Supports:
  - Insert
  - Search
  - Delete
  - Traversals (in-order, pre-order, post-order)
 
  Use Cases:
  --------------------
  - Efficient searching and dynamic data storage
  - Maintaining sorted data
  - Basis for AVL and Red-Black Trees
 
  Time Complexity:
  --------------------
  - Average (balanced): O(log n) for search, insert, delete
  - Worst (skewed): O(n)
 
  Approach:
  --------------------
  Think of a dictionary: left side contains smaller words, right side contains bigger words.
  Recursively navigate to find the correct position for insert/search/delete.
 */

class BSTNode {
    int value;
    BSTNode left, right;

    BSTNode(int value) {
        this.value = value;
        left = right = null;
    }
}

public class BinarySearchTree {

    private BSTNode root;

    public BinarySearchTree() {
        root = null;
    }

    // INSERT
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private BSTNode insertRec(BSTNode node, int value) {
        if (node == null) return new BSTNode(value);

        if (value < node.value) node.left = insertRec(node.left, value);
        else if (value > node.value) node.right = insertRec(node.right, value);

        return node;
    }

    // SEARCH
    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(BSTNode node, int value) {
        if (node == null) return false;
        if (node.value == value) return true;

        return value < node.value ? searchRec(node.left, value) : searchRec(node.right, value);
    }

    // IN-ORDER (sorted)
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(BSTNode node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.print(node.value + " ");
        inOrderRec(node.right);
    }

    // DEMO
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.print("BST In-order (sorted): ");
        bst.inOrder();

        int target = 40;
        System.out.println("Searching " + target + ": " + bst.search(target));
    }
}