# Recursion

## The one-sentence definition

**Recursion is delegating a problem to a smaller version of itself, and trusting that smaller version to come back with the right answer.**

The single biggest unlock for actually *getting* recursion is: stop trying to mentally trace every call. You don't debug recursion by unwinding the whole call tree in your head every time — you write it by trusting the recursive call, the same way you trust a library function you didn't write. This is sometimes called the **"leap of faith"** approach.

## The two things every recursive function needs

1. **Base case** — the condition where you stop recursing and return a direct answer. Without this, you get infinite recursion → `StackOverflowError`.
2. **Recursive step** — how the problem shrinks toward the base case, and how you combine the smaller answer into the answer for the current call.

```java
static long factorial(int n) {
    if (n == 0) return 1;              // base case
    return n * factorial(n - 1);        // recursive step: trust factorial(n-1), then use it
}
```

When writing new recursive code, answer these two questions *before* writing anything else:
- What's the smallest version of this problem I can answer directly?
- Given the answer to a slightly smaller version of this problem, how do I build the answer to the current one?

## The call stack is real memory — recursion has a space cost

Every recursive call pushes a new **stack frame** — local variables, the return address, parameters — onto the call stack. `06-Recursion.java`'s `showStack()` function prints its own depth precisely to make this visible: the indentation *is* the stack growing and shrinking.

This means: **a recursive function that goes `n` levels deep uses O(n) space on the call stack, even if it never allocates a single array.** This is the single most common thing people forget when stating a recursive algorithm's space complexity — they count the explicit data structures and forget the implicit stack cost.

```java
// This "looks" like it uses no extra memory... but it's O(n) space,
// because of n stacked function calls waiting to return.
static int sum(int[] arr, int i) {
    if (i == arr.length) return 0;
    return arr[i] + sum(arr, i + 1);
}
```

In Java specifically, deep enough recursion (tens of thousands of frames, roughly, depending on JVM stack size settings) throws `StackOverflowError`. This is a real, practical failure mode — not just a theoretical space-complexity footnote.

## Why naive recursion can be catastrophically slow: overlapping subproblems

`fibNaive(n)` recomputes the same values over and over. `fibNaive(5)` calls `fibNaive(3)` **twice** — once from the `fibNaive(4)` branch, once directly. At `fibNaive(35)`, that redundancy compounds into millions of wasted calls.

**Memoization** fixes this by caching results the first time they're computed, so every unique sub-problem is solved exactly once:

```java
static long fibMemo(int n, long[] cache) {
    if (n <= 1) return n;
    if (cache[n] != 0) return cache[n];     // already solved — reuse it
    cache[n] = fibMemo(n - 1, cache) + fibMemo(n - 2, cache);
    return cache[n];
}
```

This single idea — "cache sub-problem results so you never solve the same one twice" — is the entire foundation of top-down dynamic programming (`20-dynamic-programming/`). If you understand *why* memoized Fibonacci is fast, you already understand the core idea of DP; the rest is pattern recognition for *which* problems have overlapping sub-problems.

## Tail recursion (and why it doesn't save you in Java)

A recursive call is in **tail position** if it's the very last thing the function does — nothing happens after it returns.

```java
// NOT tail-recursive: multiplication happens AFTER the call returns.
// The stack must hold every pending "n *" until the base case resolves.
static long factorialNonTail(int n) {
    if (n == 0) return 1;
    return n * factorialNonTail(n - 1);
}

// Tail-recursive: nothing happens after the recursive call — the
// "work so far" is carried forward as a parameter (the accumulator)
// instead of being deferred until the call returns.
static long factorialTail(int n, long accumulator) {
    if (n == 0) return accumulator;
    return factorialTail(n - 1, n * accumulator);
}
```

Some languages (Scheme, Scala, Kotlin with the `tailrec` keyword) detect this pattern and compile it into a loop, using O(1) stack space instead of O(n). **The JVM does not do this** — both versions above use O(n) stack frames in Java. It's still worth recognizing the pattern, both because it clarifies *why* a function needs (or doesn't need) to defer work, and because you may write Kotlin, Scala, or other JVM languages where it matters.

## Recursion on non-linear structures: trees

Recursion isn't just for "count down to zero" problems — it's the natural fit for any structure that's defined in terms of smaller versions of itself, which is exactly what a tree is (a node, plus two subtrees that are themselves trees):

```java
static int countNodes(TreeNode root) {
    if (root == null) return 0;                                  // base case: empty tree
    return 1 + countNodes(root.left) + countNodes(root.right);    // this node + both subtrees
}
```

This pattern — base case on `null`, recursive step combines the current node with recursive results from children — is the backbone of nearly everything in `12-binary-trees/` and `15-graphs/`.

## The trap to watch for: forgetting the base case handles ALL terminating conditions

A common bug is handling only *one* stopping condition when the problem actually has several. For tree recursion, forgetting to check `root == null` causes a `NullPointerException`, not infinite recursion — but the *category* of mistake is the same: an incomplete base case.