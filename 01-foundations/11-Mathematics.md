# Mathematics for DSA

## Why this module exists

Interviewers assume a handful of number-theory facts as "obvious background" the same way they assume you know how a `for` loop works. This file collects the ones that actually recur — not as a math course, but as the specific tools that show up inside real problems.

## GCD: the Euclidean algorithm

**Claim:** `gcd(a, b) = gcd(b, a % b)`.

**Why:** any number that divides both `a` and `b` must also divide `a - b` (if `d | a` and `d | b`, then `d | (a-b)`). Repeated subtraction of `b` from `a` is exactly what `a % b` computes in one step — so the *set* of common divisors of `(a, b)` is identical to the set of common divisors of `(b, a % b)`. Since the GCD is unchanged, and the numbers strictly shrink each step, this terminates fast — in fact in O(log(min(a,b))) steps, one of the tightest complexity bounds you'll encounter.

## LCM, for free once you have GCD

`lcm(a, b) * gcd(a, b) = a * b` is a hard mathematical fact (worth knowing exists, not worth re-deriving under pressure). Rearranged: `lcm(a, b) = (a / gcd(a, b)) * b`. Notice the code divides *before* multiplying — this matters in practice, because multiplying `a * b` first risks integer overflow for large inputs, while dividing first keeps intermediate values smaller.

## Sieve of Eratosthenes: primes, in bulk

If you need *all* primes up to n, checking each number individually for primality (trial division, O(√k) per number) is needlessly slow: O(n√n) total. The sieve flips the approach — assume everything is prime, then **cross off multiples** of each prime you find, in bulk.

The subtle but important optimization: start crossing off at `i * i`, not `2 * i`. Every smaller multiple of `i` (like `2i`, `3i`, ..., `(i-1)i`) has *already* been crossed off by a smaller prime factor, by the time you reach `i`. Starting at `i*i` avoids redundant work — and this is also *why* the outer loop only needs to run while `i * i <= n`: any composite number ≤ n must have a factor ≤ √n.

Total complexity: O(n log log n) — for practical purposes, this is "almost linear," and it's the standard tool whenever a problem needs primality info for a *range* of numbers rather than a single one.

## Prime factorization by trial division

Divide out all factors of 2 first (so every subsequent candidate divisor can skip evens), then try odd numbers up to `√n`. The `√n` bound has the same justification as the sieve's: if `n` has a factor larger than `√n`, it must pair with a factor smaller than `√n` that trial division would already have found and divided out — so by the time you'd reach a factor larger than √n, `n` would already be reduced to 1, or the remaining value *is* itself a lone prime factor (handled by the `if (n > 1)` at the end).

## Fast exponentiation (binary exponentiation)

**The problem:** compute `base^exp mod m` when `exp` might be in the millions. Multiplying `base` by itself `exp` times is O(exp) — too slow.

**The trick:** decompose `exp` into binary. Since any integer is a sum of powers of 2 (e.g., `13 = 8 + 4 + 1 = 2^3 + 2^2 + 2^0`), `base^13 = base^8 * base^4 * base^1`. You can build `base^1, base^2, base^4, base^8, ...` by repeatedly **squaring** — each squaring doubles the exponent — and multiply the ones you need into the result based on which bits of `exp` are set.

```java
long result = 1;
while (exp > 0) {
    if ((exp & 1) == 1) result = (result * base) % mod;  // this bit is set -- include it
    base = (base * base) % mod;                            // square base -- next power of 2
    exp >>= 1;                                              // move to the next bit
}
```

This is O(log exp) instead of O(exp) — the same halving idea that makes binary search fast, applied to multiplication instead of searching.

## Modular arithmetic: why, and the three rules

**Why it comes up:** answers in combinatorics/DP problems can grow astronomically (well beyond a 64-bit `long`), so problems ask for the answer "mod 1,000,000,007" (a conveniently large prime that avoids overflow issues in intermediate products when combined with `long` arithmetic).

Three facts make this tractable:
- `(a + b) mod m = ((a mod m) + (b mod m)) mod m`
- `(a * b) mod m = ((a mod m) * (b mod m)) mod m`
- `(a - b) mod m` needs care: Java's `%` can return a **negative** result when `a - b` is negative (unlike Python's `%`, which always returns non-negative for a positive modulus). Fix: add `m` before the final mod — `((a - b) % m + m) % m` — to guarantee a non-negative result.

Division is the odd one out: you **cannot** just do `(a / b) mod m` — division doesn't distribute over mod the way + and * do. It requires computing a **modular multiplicative inverse** (via Fermat's Little Theorem when `m` is prime, using fast exponentiation: `b^(m-2) mod m`). This is worth knowing exists; it shows up in `23-mathematical-algorithms/06-ModularArithmetic.java` in more depth.

## The practical takeaway

None of this is about memorizing formulas — it's pattern recognition. When you see:
- "find the GCD/LCM of..." → Euclidean algorithm
- "how many primes below n" or "primes in a range" → Sieve of Eratosthenes
- "factorize n" → trial division up to √n
- "raise to a huge power, mod something" → fast exponentiation
- "count something, answer may be huge, mod 10^9+7" → modular arithmetic rules

recognizing which of these five shapes a problem matches is the actual skill.