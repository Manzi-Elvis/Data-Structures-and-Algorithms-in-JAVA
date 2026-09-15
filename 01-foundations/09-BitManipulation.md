# Bit Manipulation

## The one-sentence definition

**Bit manipulation is arithmetic and logic done directly on a number's binary representation, usually to gain O(1) tricks that would otherwise take a loop or extra memory.**

You don't need to memorize a long list of tricks. You need to internalize about six ideas — and every "clever" bit trick you'll ever see in an interview is one of these six, combined.

## The six ideas

### 1. AND (`&`) isolates / tests / masks

`a & b` keeps only the bits that are 1 in *both*. Used to **test** a specific bit (`n & (1 << i)`), or to **mask out** everything except a region of bits you care about.

### 2. OR (`|`) sets / merges

`a | b` keeps a bit if it's 1 in *either*. Used to **turn a bit on** without touching the others (`n | (1 << i)`).

### 3. XOR (`^`) toggles / cancels / diffs

`a ^ b` is 1 where the bits *differ*. Three properties make XOR uniquely useful:
- `x ^ x = 0` — a value cancels itself out
- `x ^ 0 = x` — XORing with 0 is a no-op
- It's commutative and associative — order doesn't matter

These three facts together are *the entire reason* `findSingleNumber()` works: XOR every element in an array where everything appears in pairs except one, and every pair cancels to 0, leaving only the unpaired value.

### 4. NOT (`~`) flips everything

Used to build a mask that's "all 1s except one 0" (`~(1 << i)`), which is how `clearBit()` works — ANDing with that mask forces exactly one position to 0 and leaves the rest untouched.

### 5. Shifts (`<<`, `>>`) move bits, which means multiply/divide by powers of 2

`n << k` is `n * 2^k`. `n >> k` is `n / 2^k` (floor division, and for negative numbers in Java, `>>` is an *arithmetic* shift that preserves the sign bit — use `>>>` for a *logical* shift that always fills with 0s).

### 6. Two's complement makes `n & (-n)` isolate the lowest set bit

This one deserves its own explanation because it's the least intuitive.

## Why `n & (-n)` isolates the lowest set bit

Java (like almost every language) represents negative numbers using **two's complement**: `-n` is computed as `~n + 1` (flip every bit, then add 1).

Take `n = 0b1011000` (=88). To get `-n`:
1. Flip every bit: `0b...0100111`
2. Add 1: `0b...0101000`

Notice: everything **below** the original lowest set bit becomes 0 in `-n` (the trailing zeros stay zero — flipping them gives 1s, but adding 1 carries through all of them back to 0). Everything **at or above** the lowest set bit flips in a way that, when ANDed with the original `n`, only the lowest set bit survives.

```
 n = 01011000
-n = 10101000   (two's complement)
 n & -n = 00001000   <- exactly the lowest set bit, isolated
```

This exact trick is the beating heart of the **Fenwick Tree / Binary Indexed Tree** (`24-advanced-data-structures/02-FenwickTree.java`) — it's how a Fenwick Tree computes "the next index to update" or "the next index to query" in O(log n).

## `n & (n-1)`: clears the lowest set bit

Subtracting 1 from `n` flips the lowest set bit to 0 and every bit below it to 1 (`1000 - 1 = 0111`). ANDing the original `n` with `n-1` therefore keeps everything *except* that lowest set bit.

Two direct payoffs:
- **Power-of-2 check:** a power of 2 has *exactly one* bit set, so `n & (n-1)` is 0 only for powers of 2 (plus you must separately check `n > 0`, since 0 would pass the bit test trivially).
- **Counting set bits (Brian Kernighan's algorithm):** repeatedly clear the lowest set bit and count how many times you could do it before hitting 0. This runs in O(number of set bits) — genuinely faster than checking all 32 bit positions when `n` is sparse.

## When bit tricks are worth reaching for (and when they aren't)

Bit tricks earn their place in **exactly these situations**:
- The problem explicitly involves binary representation (e.g., "count bits", "find the missing number using XOR")
- You need genuine O(1) space where a hash set or boolean array would cost O(n) — e.g., using a single `int` as a bitmask to represent a small set (common in `20-dynamic-programming/16-BitmaskDP.java` and `18-recursion-backtracking/`)
- Performance genuinely matters and you're in a tight loop

They are **not** worth reaching for just to look clever — `n * 2` is clearer than `n << 1` in almost all real code, and readability should win unless there's a concrete reason not to.

## Common interview signal: "can you do this in O(1) space?"

If a problem gives you an array of integers where you need to find something (a missing number, a duplicate, a unique element) and the interviewer follow-up is "can you avoid extra space?" — that's almost always a hint toward XOR or bit-masking, because a hash set would be the obvious O(n)-space first pass.