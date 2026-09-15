# Amortized Analysis

## The one-sentence definition

**Amortized analysis measures the *average* cost of an operation over a long sequence of operations — even when individual operations occasionally spike in cost.**

This is the notation that resolves what looks like a contradiction: *"Why does everyone say `ArrayList.add()` is O(1) when, under the hood, it sometimes has to copy the entire array?"*

## The motivating puzzle

A Java `ArrayList` is backed by a fixed-size array. When you `add()` and the array is full, it must:
1. Allocate a new, larger array (typically 1.5x–2x the old capacity)
2. Copy every existing element into it — an O(n) operation
3. Then insert the new element

So *some* calls to `add()` cost O(n). If you called that "the" cost of `add()`, you'd have to say `ArrayList.add()` is O(n) — but that's misleading, because it happens rarely, and gets *rarer* as the list grows (resizes happen at sizes 1, 2, 4, 8, 16... — exponentially further apart).

## The trick: doubling means resizes are rare enough to "pay off" the cheap operations

Say the array doubles in size every time it fills up. To go from empty to n elements:

- Total elements ever copied across all resizes: `1 + 2 + 4 + 8 + ... + n ≈ 2n`
- Total number of `add()` calls: n
- Average cost per `add()`: `2n / n = 2` → **O(1) amortized**

The key insight: **the expensive resize is rare enough, and the cheap inserts are frequent enough, that averaged over the whole sequence, every operation "pays" only a constant amount.** This is fundamentally different from *average case* analysis (which is about probability over random inputs) — amortized analysis makes **no assumption about randomness at all**. It's a guarantee about the total cost of *any* sequence of n operations, divided by n.

## Three ways to actually compute an amortized bound

You won't be asked to formally derive these often, but recognizing the *names* signals you've studied this properly:

1. **Aggregate method** — what we just did above: sum the total cost of n operations, divide by n.
2. **Accounting method** — assign each operation a "credit" it pays upfront; cheap operations bank credit, expensive operations spend the banked credit. (Think of it like a savings account: cheap inserts deposit a few extra "coins," and when a resize happens, it withdraws from the accumulated pile instead of charging the current operation the full cost.)
3. **Potential method** — define a "potential function" over the data structure's state (e.g., how full the array currently is) and show that expensive operations are always preceded by enough built-up potential to cover them.

For interviews, the **aggregate method's intuition** — "count the total work over the whole sequence, then divide" — is the one you'll actually use out loud.

## Where else amortized analysis shows up

| Structure / operation | Occasional expensive case | Why it's still O(1) amortized |
|---|---|---|
| `ArrayList.add()` | O(n) resize | Resizes double capacity, so they get exponentially rarer |
| `HashMap.put()` | O(n) rehash | Same doubling strategy on the backing table |
| Dynamic array `pop_back` after shrinking | O(n) shrink-to-fit | Shrinking by a fixed *fraction*, mirroring growth |
| Union-Find with path compression | A single `find()` can walk a long chain | Path compression flattens the tree, so future `find()`s are cheap |
| Incrementing a binary counter | Occasionally flips every bit | Most increments flip only the last bit; total flips over n increments is O(n) |

## The trap this notation prevents

Without amortized analysis, you'd either:
- Wrongly call `ArrayList.add()` "O(n)" (too pessimistic — ignores how rare resizes actually are), or
- Wrongly assume it's *always* fast with no caveats (dangerous — a single `add()` call in a latency-sensitive loop really can spike).

**Amortized O(1) means: over many calls, you're fine. It does NOT mean every individual call is fast** — this distinction matters in real systems (e.g., avoiding array resizes inside a hot loop in a game engine or a latency-critical service) even though it rarely matters for interview-style complexity questions.