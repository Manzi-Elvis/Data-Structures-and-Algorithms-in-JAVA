# Big-Ω (Big-Omega) Notation

## The one-sentence definition

**Big-Ω describes the *best-case* lower bound on an algorithm's cost — the least amount of work it could possibly do.**

If Big-O is "how bad can it get," Big-Ω is "how good can it get." They're mirror images of the same idea, applied to opposite ends of the performance spectrum.

## Why this one gets skipped over (and why that's a mistake)

Most study plans rush past Big-Ω because "worst case is what matters." That's often true for guaranteeing performance, but Big-Ω is exactly the tool you need to answer a specific, very common interview question:

> *"Can this algorithm be faster in some cases, or is it always going to do this much work no matter what?"*

Example: linear search through an array.
- **Worst case (Big-O):** O(n) — the target is last, or absent.
- **Best case (Big-Ω):** Ω(1) — the target happens to be the first element.

These are genuinely different bounds because the algorithm's actual runtime *depends on the input*, not just its size.

## A case where best and worst case coincide

Summing all elements of an array:

```java
int sum = 0;
for (int x : arr) sum += x;
```

There is no "lucky" input here — every element must be visited regardless of its value. Best case = worst case = Θ(n) (see `04-BigTheta.md` for what that means).

## Formal definition

f(n) = Ω(g(n)) means there exist positive constants c and n₀ such that for all n ≥ n₀,

```
f(n) ≥ c · g(n)
```

In plain terms: g(n) is a floor. f(n) never does *less* work than g(n), for large enough n.

## Applying it to real algorithms

| Algorithm | Ω (best case) | O (worst case) | Why they differ |
|---|---|---|---|
| Linear search | Ω(1) | O(n) | Target could be anywhere |
| Bubble sort | Ω(n) | O(n²) | Already-sorted input needs only one pass to confirm |
| Quicksort | Ω(n log n) | O(n²) | Balanced pivots vs. worst-case pivot choice |
| Binary search | Ω(1) | O(log n) | Target could be the middle element on the first check |
| Insertion sort | Ω(n) | O(n²) | Nearly-sorted data needs almost no shifting |

The quicksort row is the one worth internalizing: **Big-Ω and Big-O for the same algorithm can be wildly different when the algorithm's performance is input-dependent**, which is precisely why interviewers ask "what's the worst case for your approach" even after you've described how it usually behaves.

## Interview framing

When you propose an algorithm, get in the habit of stating *both* bounds when they differ:

> "This runs in O(n²) worst case, but Ω(n) if the input's already sorted, because the inner loop can exit early."

This signals you understand that complexity isn't one fixed number — it's a *function of the input*, and Big-O/Big-Ω are two different questions about that function.