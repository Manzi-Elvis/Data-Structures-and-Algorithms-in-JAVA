# A Repeatable Framework for Approaching a New Problem

## Why this file exists

Knowing individual algorithms doesn't automatically make you good at *new* problems — that requires a repeatable process for going from "I've never seen this exact problem" to "here's a working solution." This is that process, distilled from how strong problem-solvers actually work, not how solutions look *after* they're polished.

## The five stages

### 1. Restate the problem in your own words

Before touching code, say back what's being asked — out loud or in a comment. This surfaces misunderstandings immediately, and it's also exactly what interviewers want to hear, because it demonstrates you're not pattern-matching to a memorized problem.

### 2. Work a small example by hand

Pick the smallest non-trivial input and manually produce the expected output. This does two things: it confirms you actually understand the problem (stage 1 can feel right and still be wrong), and it often *reveals the algorithm* — the steps you took by hand are frequently the steps the code needs to take.

### 3. Identify constraints, and let them guide complexity

Constraints in a problem statement aren't decoration — they're a direct hint at the intended complexity:

| Constraint on n | What it usually signals |
|---|---|
| n ≤ 10–20 | Brute force / exponential (2ⁿ) is probably fine — try all subsets or permutations |
| n ≤ 500–1000 | O(n²) is probably fine |
| n ≤ 10⁵–10⁶ | You need O(n log n) or O(n) |
| n ≤ 10⁹ or more | You need O(log n) or O(1) — think math, not iteration |

If you propose an O(n²) solution and the constraint says n ≤ 10⁶, that's a signal to reconsider *before* writing code, not after it times out.

### 4. Identify the shape: does this match a known pattern?

Most interview problems are a known pattern wearing a costume. Learning to recognize the costume is most of the skill:

| Signal in the problem | Likely pattern |
|---|---|
| "contiguous subarray/substring" | Sliding window (`08-sliding-window/`) |
| "sorted array" + "find pair/triplet" | Two pointers (`07-two-pointers/`) |
| "find in sorted/monotonic space" | Binary search on answer (`09-binary-search/`) |
| "shortest path" / "fewest steps" | BFS (`15-graphs/`) |
| "all paths" / "explore every option" | DFS or backtracking (`18-recursion-backtracking/`) |
| "top k" / "k largest/smallest" | Heap (`11-heaps-priority-queues/`) |
| "overlapping subproblems" / "count ways" / "min/max over choices" | Dynamic programming (`20-dynamic-programming/`) |
| "next greater/smaller element" | Monotonic stack (`05-stacks-queues/06-MonotonicStack.java`) |
| "connected components" / "grouping" | Union-Find (`17-union-find/`) |
| "intervals" / "scheduling" / "merge ranges" | Sort + greedy or sweep (`19-greedy/`) |

This table is the actual payoff of studying every module in this repo — not memorizing each algorithm in isolation, but building the reflex to recognize *which* module a new problem belongs to.

### 5. Start with brute force, THEN optimize

Even when you can see the optimal approach immediately, stating the brute-force approach first is valuable: it's a working baseline you can fall back to if the optimization goes wrong, it's usually easier to verify correct, and — in an interview — it demonstrates you can identify *why* the optimization is an improvement, rather than reciting a memorized solution.

The optimization step itself is usually one of:
- **Trade space for time** (e.g., a hash map to avoid a nested loop — see `06-hashing/`)
- **Sort first** to unlock two pointers or greedy reasoning
- **Avoid redundant recomputation** (memoization — see `07-Recursion.md`)
- **Use a smarter data structure** for the specific query pattern you need (heap for "top-k," monotonic stack for "next greater," etc.)

## Talking through edge cases (before you're asked)

Habitually check, out loud or in comments: empty input, single-element input, all-duplicate input, already-sorted/already-solved input, and the largest input the constraints allow (does your approach still fit the time budget?). Raising these *before* being asked is one of the clearest, cheapest signals of experience in an interview.

## What this framework is NOT

It's not a script to follow mechanically on every single problem — with practice, stages 1–4 happen in seconds for familiar problem shapes. It's scaffolding for the moments you're stuck, and a way to talk through your reasoning out loud in an interview, so the interviewer can follow *why* you're proposing what you're proposing — which is usually more valuable to them than the final answer itself.