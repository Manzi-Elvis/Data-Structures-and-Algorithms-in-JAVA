# Big-Θ (Big-Theta) Notation

## The one-sentence definition

**Big-Θ is a "tight bound": it means the best case AND the worst case grow at the same rate.**

If Big-O is the ceiling and Big-Ω is the floor, Big-Θ is when the ceiling and floor are the *same shape* — the algorithm's cost is pinned to a single growth rate no matter what the input looks like.

## Why this is the notation people actually mean

Here's a mild but important confession about how the field talks: when engineers casually say "this is O(n)," they usually *mean* Θ(n) — they mean the algorithm is reliably, always, linear — not "linear in the worst case but maybe better sometimes." Big-O is technically just an upper bound (an algorithm that's Θ(1) is *also*, technically, O(n²), O(n!), anything at or above it) — but colloquially, "Big-O" is used to mean "the tight bound," which is really Big-Θ.

Knowing this distinction — and being able to state it precisely — is a small thing that reliably signals rigor in an interview.

## Formal definition

f(n) = Θ(g(n)) means f(n) = O(g(n)) **and** f(n) = Ω(g(n)) simultaneously. There exist constants c₁, c₂, n₀ such that for all n ≥ n₀:

```
c₁ · g(n)  ≤  f(n)  ≤  c₂ · g(n)
```

f(n) is sandwiched between two scaled copies of g(n) — it can never escape above or below that band once n is large enough.

## Worked example: array summation

```java
int sum = 0;
for (int x : arr) sum += x;
```

- Best case: every element must be added → Ω(n)
- Worst case: every element must be added → O(n)
- Since both bounds are n: **Θ(n)**

Compare to linear search, which is Ω(1) but O(n) — those don't match, so linear search has **no single Θ bound** that applies to all inputs. You can only say "Θ(n) in the worst case" (meaning: for the specific *subset* of inputs that are worst-case, i.e. target absent or last, the cost is tightly n).

## The practical rule

Ask two questions about your algorithm:
1. Does its cost depend on the *values* in the input, or only on the *size* of the input?
2. If it depends on size only — same cost for every arrangement of n elements — you have a Θ(g(n)) bound.

| Algorithm | Θ bound? | Reason |
|---|---|---|
| Array traversal | Θ(n) | Always visits every element |
| Merge sort | Θ(n log n) | Always splits and merges the same way, regardless of input order |
| Linear search | No single Θ | Cost depends on *where* (or if) the target is |
| Quicksort | No single Θ | Cost depends on pivot choices relative to data |
| Matrix multiplication (naive) | Θ(n³) | Always does the same triple-nested work |

Merge sort is the example worth remembering: unlike quicksort, merge sort's split-then-merge structure doesn't care about the input's order, so it has a genuine, unconditional Θ(n log n) — this is *why* merge sort is preferred when you need a runtime *guarantee*, even though quicksort is often faster in practice.