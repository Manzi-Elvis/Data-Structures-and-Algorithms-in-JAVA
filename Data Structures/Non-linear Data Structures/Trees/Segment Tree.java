/*
  Description:
  --------------------
  A Segment Tree is a binary tree used for storing intervals or segments.
  It allows querying sums, minimums, maximums, or other associative operations
  over a range efficiently.
 
  Supports:
  - Build tree
  - Range query (sum in this example)
  - Update value
 
  Use Cases:
  --------------------
  - Range sum queries
  - Range minimum/maximum queries
  - Competitive programming and interval problems
 
  Time Complexity:
  --------------------
  - Build: O(n)
  - Query: O(log n)
  - Update: O(log n)
 
  Approach:
  --------------------
  Imagine a tree where each node stores the sum of a segment of an array.
  By combining segments, we can efficiently get the sum of any range.
 */

public class SegmentTree {

    private int[] tree;
    private int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, n - 1, 1);
    }

    private void build(int[] arr, int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        build(arr, start, mid, 2 * node);
        build(arr, mid + 1, end, 2 * node + 1);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    public int query(int l, int r) {
        return queryRec(1, 0, n - 1, l, r);
    }

    private int queryRec(int node, int start, int end, int l, int r) {
        if (r < start || l > end) return 0; // no overlap
        if (l <= start && end <= r) return tree[node]; // total overlap

        int mid = (start + end) / 2;
        int leftSum = queryRec(2 * node, start, mid, l, r);
        int rightSum = queryRec(2 * node + 1, mid + 1, end, l, r);
        return leftSum + rightSum;
    }

    public void update(int index, int value) {
        updateRec(1, 0, n - 1, index, value);
    }

    private void updateRec(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }

        int mid = (start + end) / 2;
        if (idx <= mid) updateRec(2 * node, start, mid, idx, val);
        else updateRec(2 * node + 1, mid + 1, end, idx, val);

        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    // DEMO
    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11 };
        SegmentTree st = new SegmentTree(arr);

        System.out.println("Sum of values in range [1, 3]: " + st.query(1, 3));
        st.update(1, 10);
        System.out.println("After update, sum of values in range [1, 3]: " + st.query(1, 3));
    }
}