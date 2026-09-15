# DSA in Java

A from-scratch, hands-on journey through data structures and algorithms — built to be **understood**, not memorized by rote. Every topic pairs a runnable Java implementation with a companion note that explains *why* it works, not just *how*.

## Philosophy

- **Intuition before code.** Every concept starts with a mental model or analogy before the implementation.
- **Runnable, not theoretical.** Every `.java` file has a `main` method you can run and see the result of immediately.
- **Complexity is never assumed.** Every technique states its time/space complexity and *why* it's that complexity.
- **Mistakes are part of the curriculum.** `31-mistakes-and-lessons/` captures real bugs and misconceptions hit along the way — the fastest way to actually retain a lesson.

## Structure

Each numbered folder is a self-contained topic with:
- `NN-Topic.java` — a runnable implementation with inline reasoning
- `NN-Topic.md` — the "why," complexity analysis, common pitfalls, and interview framing
- `README.md` — a study guide for that folder: what to learn, in what order, and how to know you've got it

## Roadmap

- [x] 01 — Foundations (Big-O, recursion, bit manipulation, math)
- [ ] 02 — Arrays
- [ ] 03 — Strings
- [ ] 04 — Linked Lists
- [ ] 05 — Stacks & Queues
- [ ] 06 — Hashing
- [ ] 07 — Two Pointers
- [ ] 08 — Sliding Window
- [ ] 09 — Binary Search
- [ ] 10 — Sorting
- [ ] 11 — Heaps & Priority Queues
- [ ] 12 — Binary Trees
- [ ] 13 — Balanced Trees
- [ ] 14 — Tries
- [ ] 15 — Graphs
- [ ] 16 — Graph Algorithms
- [ ] 17 — Union-Find
- [ ] 18 — Recursion & Backtracking
- [ ] 19 — Greedy
- [ ] 20 — Dynamic Programming
- [ ] 21 — Bit Manipulation (deep dive)
- [ ] 22 — String Algorithms
- [ ] 23 — Mathematical Algorithms
- [ ] 24 — Advanced Data Structures
- [ ] 25 — Advanced Algorithms
- [ ] 26 — Interview Patterns
- [ ] 27 — Problems (easy/medium/hard/company-mixed)
- [ ] 28 — Competitive Programming
- [ ] 29 — Interview Preparation
- [ ] 30 — Mock Interviews
- [ ] 31 — Mistakes & Lessons
- [ ] 32 — Progress Tracking

## Running the code

```bash
# Compile and run a single file directly
javac 01-foundations/06-Recursion.java -d out && java -cp out Recursion

# Or build the whole project with Maven
mvn compile
```

## Workflow

This repo is built module by module on dedicated `ft/` branches, merged to `main` only after the module's code compiles, runs, and its notes are reviewed. See commit history for the build log.