/**
 * 06-Recursion.java
 *
 * Recursion is just a function that trusts a smaller version of itself
 * to do most of the work. The skill isn't "understanding recursion" in
 * the abstract — it's reliably identifying two things for any new
 * problem: the base case (when do we stop trusting and just answer
 * directly?) and the recursive step (how do we shrink the problem
 * toward that base case?).
 *
 * Run:  javac 06-Recursion.java -d out && java -cp out Recursion
 */
class Recursion {

    // ---- The simplest possible recursion: factorial ---------------------
    // Base case: 0! = 1 (this is what stops the recursion)
    // Recursive step: n! = n * (n-1)!
    static long factorial(int n) {
        if (n == 0) return 1;                 // base case
        return n * factorial(n - 1);           // recursive step: trust factorial(n-1)
    }

    // ---- Fibonacci: naive vs memoized ------------------------------------
    // Naive: O(2^n) — recomputes the same sub-problems over and over.
    static long fibNaive(int n) {
        if (n <= 1) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    // Memoized: O(n) — the ONLY difference from naive is caching results
    // we've already computed. This is the entire idea behind top-down DP.
    static long fibMemo(int n, long[] cache) {
        if (n <= 1) return n;
        if (cache[n] != 0) return cache[n];    // already solved this sub-problem
        cache[n] = fibMemo(n - 1, cache) + fibMemo(n - 2, cache);
        return cache[n];
    }

    // ---- Visualizing the call stack --------------------------------------
    // Every recursive call adds a "frame" to the call stack. This function
    // prints its own depth so you can literally see the stack grow and
    // shrink as calls are made and returned from.
    static void showStack(int n, int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "-> entering showStack(" + n + "), depth=" + depth);
        if (n == 0) {
            System.out.println(indent + "   [base case hit]");
            return;
        }
        showStack(n - 1, depth + 1);
        System.out.println(indent + "<- returning from showStack(" + n + "), depth=" + depth);
    }

    // ---- Tail recursion vs non-tail recursion ----------------------------
    // Non-tail: the multiplication happens AFTER the recursive call returns.
    // The call stack must hold every pending "n *" until the base case
    // resolves, because there's still work to do after each call returns.
    static long factorialNonTail(int n) {
        if (n == 0) return 1;
        return n * factorialNonTail(n - 1);   // work happens AFTER the recursive call
    }

    // Tail-recursive: the recursive call is the LAST thing that happens —
    // no pending work after it returns. Some languages optimize this into
    // a loop (no stack growth). Java's JVM does NOT do this optimization,
    // so this is still O(n) stack space in Java — but it's worth knowing
    // the pattern, because many other languages (Scala, Kotlin with `tailrec`,
    // Scheme) DO optimize it.
    static long factorialTail(int n, long accumulator) {
        if (n == 0) return accumulator;
        return factorialTail(n - 1, n * accumulator);  // nothing left to do after this call
    }

    // ---- Recursion on a tree-shaped problem: counting nodes --------------
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int countNodes(TreeNode root) {
        if (root == null) return 0;            // base case: empty tree has 0 nodes
        return 1 + countNodes(root.left) + countNodes(root.right); // this node + both subtrees
    }

    public static void main(String[] args) {
        System.out.println("=== Factorial ===");
        System.out.println("5! = " + factorial(5));

        System.out.println("\n=== Naive vs memoized Fibonacci ===");
        long t1 = System.nanoTime();
        long naive = fibNaive(35);
        long naiveMs = (System.nanoTime() - t1) / 1_000_000;

        long t2 = System.nanoTime();
        long memo = fibMemo(35, new long[36]);
        long memoMs = (System.nanoTime() - t2) / 1_000_000;

        System.out.println("fibNaive(35) = " + naive + "  (" + naiveMs + " ms)");
        System.out.println("fibMemo(35)  = " + memo + "  (" + memoMs + " ms)");
        System.out.println("Same answer. The only difference is not re-solving solved sub-problems.");

        System.out.println("\n=== Watching the call stack grow and shrink ===");
        showStack(3, 0);

        System.out.println("\n=== Tail vs non-tail (same result, different stack discipline) ===");
        System.out.println("factorialNonTail(6) = " + factorialNonTail(6));
        System.out.println("factorialTail(6, 1) = " + factorialTail(6, 1));
        System.out.println("(Java does NOT optimize tail calls -- both use O(n) stack frames here.)");

        System.out.println("\n=== Recursion on a tree ===");
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println("Node count: " + countNodes(root));
    }
}