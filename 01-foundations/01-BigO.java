/**
 * 01-BigO.java
 *
 * Big-O isn't a formula you memorize — it's a promise about how badly
 * your runtime degrades as input grows. This file makes that promise
 * *visible* by actually counting operations, not just theorizing about them.
 *
 * Run:  javac 01-BigO.java -d out && java -cp out BigO
 */
import java.util.HashMap;
import java.util.Map;

class BigO {

    // ---- O(1): Constant time -----------------------------------------
    // Cost doesn't change no matter how big `arr` gets. One array lookup
    // is one array lookup whether the array has 10 or 10 million elements.
    static int constantTime(int[] arr) {
        return arr[0];
    }

    // ---- O(log n): Logarithmic time -----------------------------------
    // Binary search. Every comparison throws away HALF the remaining
    // search space. That's why it's "log" — log2(n) is literally the
    // number of times you can halve n before hitting 1.
    static int logTime(int[] sortedArr, int target) {
        int lo = 0, hi = sortedArr.length - 1;
        int steps = 0;
        while (lo <= hi) {
            steps++;
            int mid = lo + (hi - lo) / 2;
            if (sortedArr[mid] == target) {
                System.out.println("  found in " + steps + " steps (array size " + sortedArr.length + ")");
                return mid;
            } else if (sortedArr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    // ---- O(n): Linear time ---------------------------------------------
    // One unit of work per element. Double the input, double the work.
    static int linearTime(int[] arr, int target) {
        int comparisons = 0;
        for (int val : arr) {
            comparisons++;
            if (val == target) {
                System.out.println("  found after " + comparisons + " comparisons");
                return comparisons;
            }
        }
        return -1;
    }

    // ---- O(n log n): Linearithmic time -----------------------------------
    // Merge sort: n elements, each one touched log(n) times (once per
    // "level" of the split). This is the practical ceiling for
    // comparison-based sorting — you cannot do better than n log n
    // if all you can do is compare two elements.
    static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;
        int mid = arr.length / 2;
        int[] left = java.util.Arrays.copyOfRange(arr, 0, mid);
        int[] right = java.util.Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    // ---- O(n^2): Quadratic time ------------------------------------------
    // A loop inside a loop. Every element compared against every other
    // element. Double the input -> QUADRUPLE the work. This is the
    // shape to watch for in interviews: nested iteration over the same
    // data is the #1 smell that a solution needs optimizing.
    static int quadraticTime(int[] arr) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                comparisons++;
            }
        }
        return comparisons;
    }

    // ---- O(2^n): Exponential time ----------------------------------------
    // Naive recursive Fibonacci. Every call spawns two more calls until
    // the base case. The call tree DOUBLES in size with every +1 to n.
    // This is why fib(50) naive-recursive never finishes but fib(50)
    // memoized (see 06-Recursion.java) is instant.
    static long fibNaive(int n) {
        if (n <= 1) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    // ---- Demonstrating the SAME problem at two different complexities ---
    // This is the point that actually matters in interviews: the
    // complexity class isn't a property of the *problem*, it's a
    // property of your *approach* to it.
    static boolean hasDuplicateSlow(int[] arr) {
        // O(n^2): compare every pair
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) return true;
            }
        }
        return false;
    }

    static boolean hasDuplicateFast(int[] arr) {
        // O(n): trade space for time with a HashSet
        Map<Integer, Boolean> seen = new HashMap<>();
        for (int val : arr) {
            if (seen.containsKey(val)) return true;
            seen.put(val, true);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== O(log n) vs O(n): searching a sorted array of 1,000,000 ===");
        int[] big = new int[1_000_000];
        for (int i = 0; i < big.length; i++) big[i] = i;

        System.out.print("Binary search: ");
        logTime(big, 999_999);

        System.out.print("Linear search: ");
        long start = System.nanoTime();
        linearTime(big, 999_999);
        System.out.println("  (" + (System.nanoTime() - start) / 1_000_000 + " ms)");

        System.out.println("\n=== Same problem, two complexities: duplicate detection ===");
        int[] test = new int[5000];
        for (int i = 0; i < test.length; i++) test[i] = i;

        long t1 = System.nanoTime();
        hasDuplicateSlow(test);
        long slowMs = (System.nanoTime() - t1) / 1_000_000;

        long t2 = System.nanoTime();
        hasDuplicateFast(test);
        long fastMs = (System.nanoTime() - t2) / 1_000_000;

        System.out.println("O(n^2) approach: " + slowMs + " ms");
        System.out.println("O(n) approach:   " + fastMs + " ms");
        System.out.println("(Same answer. Very different cost. This gap only gets worse as n grows.)");

        System.out.println("\n=== O(2^n): why naive Fibonacci explodes ===");
        for (int n : new int[]{10, 20, 30}) {
            long s = System.nanoTime();
            long result = fibNaive(n);
            long ms = (System.nanoTime() - s) / 1_000_000;
            System.out.println("fib(" + n + ") = " + result + "  (" + ms + " ms)");
        }
        System.out.println("Notice: n=10 -> 20 -> 30 is linear growth in input,");
        System.out.println("but the TIME roughly squares each jump. That's exponential.");
    }
}