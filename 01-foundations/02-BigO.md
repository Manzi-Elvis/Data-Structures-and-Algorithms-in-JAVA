# Big-O Notation

## The one-sentence definition

**Big-O describes the worst-case upper bound on how an algorithm's cost grows as input size grows.**

Not "how fast it is." Not "how many seconds it takes." *How the cost scales.* An algorithm that takes 10 hours on n=1000 but stays at 10 hours no matter how big n gets is O(1) — constant. An algorithm that takes 1 millisecond at n=1000 but doubles every time n doubles is O(n) — and eventually that 1ms becomes a problem.

## The mental model: the "so what if n gets huge" test

Every time you look at code, ask: **"If I fed this 10x more data, how much MORE work would it do?"**

| Answer to "10x more data →" | Complexity | Name |
|---|---|---|
| Same amount of work | O(1) | Constant |
| A little more work (barely) | O(log n) | Logarithmic |
| 10x more work | O(n) | Linear |
| Slightly more than 10x | O(n log n) | Linearithmic |
| 100x more work | O(n²) | Quadratic |
| 1000x more work | O(n³) | Cubic |
| Unimaginably more work | O(2ⁿ) | Exponential |

This table is more useful to have memorized than the formal definition, because it's what you'll actually reach for when you eyeball a solution in an interview.

## Formal definition (so you're not caught off guard)

f(n) = O(g(n)) means: there exist positive constants c and n₀ such that for all n ≥ n₀,

```
f(n) ≤ c · g(n)
```

In plain terms: *eventually, g(n) grows at least as fast as f(n), possibly scaled by a constant.* Big-O throws away constants and lower-order terms because they stop mattering once n is large enough. `3n² + 100n + 500` is O(n²) — the `100n` and `500` become irrelevant noise once n is in the thousands.

## Why we drop constants (and when that bites you)

Dropping constants is correct *asymptotically*, but it can mislead you at realistic input sizes. An O(n) algorithm with a huge constant factor (say, 1000·n) can be slower than an O(n log n) algorithm with a small constant, for every n you'll ever actually run. **Big-O tells you about scaling, not about which algorithm to pick today.** In practice you often need both the asymptotic complexity *and* a rough sense of the constant factor.

## Reading complexity directly from code shape

This is the actual interview skill. Pattern-match the *shape* of the code to a complexity:

```java
// O(1) — no loop, fixed number of operations
int first = arr[0];

// O(n) — one loop over the input
for (int x : arr) { sum += x; }

// O(n) — a loop that doesn't touch every element but scales with the loop bound
for (int i = 0; i < arr.length; i++) { ... }

// O(log n) — the loop variable is multiplied or divided each iteration
for (int i = 1; i < n; i *= 2) { ... }

// O(n²) — nested loops over the same input, no early exit
for (int i = 0; i < n; i++)
    for (int j = 0; j < n; j++) { ... }

// O(n * m) — nested loops over DIFFERENT inputs of size n and m
for (int i = 0; i < n; i++)
    for (int j = 0; j < m; j++) { ... }

// O(2^n) — a recursive function that branches into 2 (or k) calls per level
int f(n) { return f(n-1) + f(n-2); }
```

The rule of thumb: **count nesting depth of loops that scale with input, and count the branching factor of recursion.** Those two habits solve 90% of complexity questions.

## Space complexity

Big-O applies to memory too. Ask: *"How much EXTRA memory does this use, beyond the input itself?"*

- Recursion has O(depth) space cost from the call stack — even if you never allocate an array, a recursive function that goes `n` levels deep uses O(n) space.
- A hash map that stores one entry per input element is O(n) space.
- An in-place algorithm (swapping elements within the given array) is O(1) *extra* space, even though the array itself is O(n).

## Common trap: "it's technically O(n) but it's really O(1) in practice"

Watch for loops bounded by a *constant*, not by the input:

```java
for (int i = 0; i < 26; i++) { ... }   // O(1) — always 26, regardless of n
for (int i = 0; i < arr.length; i++) { ... } // O(n) — scales with input
```

A loop over the 26 letters of the alphabet is O(1), not O(n), because 26 never changes no matter how large the actual input gets.

## See also
- `03-BigOmega.md` — the *best*-case bound (the mirror image of Big-O)
- `04-BigTheta.md` — when best-case and worst-case are the same bound
- `05-AmortizedAnalysis.md` — why `ArrayList.add()` is "O(1)" even though it sometimes does O(n) work