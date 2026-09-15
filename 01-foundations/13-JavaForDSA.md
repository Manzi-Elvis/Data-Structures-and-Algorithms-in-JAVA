# Java for DSA

## Why this file exists

Knowing an algorithm and knowing how to *express* it cleanly in Java are different skills. This file covers the Java-specific idioms, gotchas, and collection choices that repeatedly trip people up — especially mid-interview, when working memory is already stretched thin.

## Why every `.java` file in this repo uses a non-public class

Java requires a `public` top-level class's name to exactly match its filename. Since this repo numbers files (`01-BigO.java`, `06-Recursion.java`) for study ordering — and `01-BigO` isn't a legal Java identifier anyway (identifiers can't start with a digit) — every class in this repo is **package-private** (no `public` modifier):

```java
class BigO {              // not "public class BigO"
    public static void main(String[] args) { ... }
}
```

This compiles and runs fine (`javac 01-BigO.java -d out && java -cp out BigO`) because the restriction only applies to `public` classes. Worth knowing this convention breaks the moment you copy code into a real multi-file project — there, `public class` + matching filename is the normal, expected pattern.

## The collections you'll actually reach for

| Need | Use | Why |
|---|---|---|
| Resizable array | `ArrayList<T>` | O(1) amortized append, O(1) index access |
| Fast membership check | `HashSet<T>` | O(1) average add/contains |
| Key → value lookup | `HashMap<K,V>` | O(1) average get/put |
| Preserve insertion order + fast lookup | `LinkedHashMap<K,V>` | Same as HashMap, plus predictable iteration order |
| Sorted key → value | `TreeMap<K,V>` | O(log n) operations, keys always sorted — useful when you need `floorKey`/`ceilingKey` |
| Stack behavior (LIFO) | `Deque<T>` via `ArrayDeque<>` | **Not** `Stack` — `java.util.Stack` is a legacy, synchronized class; `ArrayDeque` is faster and is the idiomatic modern choice |
| Queue behavior (FIFO) | `Deque<T>` via `ArrayDeque<>` | Same class, used with `addLast`/`removeFirst` |
| Min/max element access | `PriorityQueue<T>` | Backed by a binary heap; default is a min-heap |
| Double-ended access | `ArrayDeque<T>` | O(1) at both ends — this is the standard choice for sliding-window problems |

**A `Stack` vs `Deque` note worth internalizing:** `java.util.Stack` extends `Vector` and is synchronized (thread-safety overhead you don't need), and it's generally considered a legacy class. The idiomatic modern stack in Java is `Deque<Integer> stack = new ArrayDeque<>();` using `push()`/`pop()`/`peek()`.

## The autoboxing trap

`ArrayList<Integer>`, `HashMap<Integer, ...>`, and similar generic collections cannot hold primitive `int` — Java **autoboxes** to `Integer`. Three consequences that cause real bugs:

1. **`==` compares references for boxed types outside the cached range.** `Integer` caches values from -128 to 127 — so `Integer.valueOf(100) == Integer.valueOf(100)` happens to be `true`, but `Integer.valueOf(200) == Integer.valueOf(200)` is `false`. **Always use `.equals()` (or auto-unbox to `int` first) when comparing boxed values — never rely on `==`.**

2. **Unboxing a `null` throws `NullPointerException`.** `Integer x = map.get(key); if (x == 5)` will NPE if `key` isn't in the map (since `map.get` returns `null`, and comparing `null == 5` tries to unbox `null`).

3. **Boxing has real performance cost** in tight loops — each autobox is a small object allocation. For performance-critical numeric code, primitive arrays (`int[]`) beat `ArrayList<Integer>`.

## `PriorityQueue` is a min-heap by default

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();                     // min at top
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // max at top
PriorityQueue<int[]> custom = new PriorityQueue<>((a, b) -> a[0] - b[0]);   // custom comparator
```

The `(a, b) -> a[0] - b[0]` comparator pattern (sort ascending by first element) is worth having memorized — it appears constantly in "k closest points," "merge k sorted lists," and similar problems.

## `Comparator` idioms worth memorizing

```java
// Sort by one field ascending
list.sort((a, b) -> a.value - b.value);

// Sort descending
list.sort((a, b) -> b.value - a.value);

// Sort by one field, break ties with another
list.sort((a, b) -> a.x != b.x ? a.x - b.x : a.y - b.y);

// Using Comparator.comparing (more readable for objects with named fields)
list.sort(Comparator.comparing((Point p) -> p.x).thenComparing(p -> p.y));
```

**Overflow trap:** `a.value - b.value` is a common shortcut, but if `value` can be near `Integer.MIN_VALUE`/`MAX_VALUE`, the subtraction can overflow and silently give a wrong sign. Prefer `Integer.compare(a.value, b.value)` when values could be extreme.

## `String` immutability and `StringBuilder`

Every `String` concatenation (`s = s + "x"`) creates a **new** `String` object — in a loop, this is O(n²) total. For building strings incrementally, use `StringBuilder`:

```java
StringBuilder sb = new StringBuilder();
for (char c : chars) sb.append(c);
String result = sb.toString();
```

This is the difference between O(n) and O(n²) for what looks like "the same" code, and it's a real, common interview trap.

## 2D arrays: row-major, and common initialization patterns

```java
int[][] grid = new int[rows][cols];                      // all zeros by default
int[][] visited = new int[rows][cols];
boolean[][] seen = new boolean[rows][cols];               // all false by default

// Deep copy a 2D array (Arrays.copyOf on a 2D array only shallow-copies the outer array!)
int[][] copy = new int[grid.length][];
for (int i = 0; i < grid.length; i++) {
    copy[i] = grid[i].clone();
}
```

## Integer overflow: the silent bug

`int` in Java is 32-bit, capped at ~2.1 billion. A very common bug: `int mid = (low + high) / 2;` in binary search overflows if `low + high` exceeds `Integer.MAX_VALUE`. The standard fix:

```java
int mid = low + (high - low) / 2;   // avoids the intermediate overflow
```

Similarly, when multiplying two `int`s that could plausibly be large (e.g., in DP problems), cast to `long` explicitly: `(long) a * b`, not just `a * b` — the multiplication happens in `int` precision *before* any assignment-time widening, so the overflow already happened by the time it's assigned to a `long`.