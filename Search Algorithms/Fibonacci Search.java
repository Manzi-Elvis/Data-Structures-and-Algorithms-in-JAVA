/*
  Description:
  Fibonacci Search is a searching algorithm that works on sorted arrays.
  It uses Fibonacci numbers to divide the array into sections, reducing the
  search space in a manner similar to Binary Search but using the golden ratio.
 
  Why Fibonacci Search?
  - Useful when the cost of accessing different memory locations varies.
  - Splits the array using Fibonacci numbers rather than midpoints.
  - Performs well on systems with sequential access storage.
 
  Use Cases:
  - Large sorted datasets.
  - Systems where uniform memory access is not guaranteed.
  - Alternative to binary search with potential cache-friendly behavior.
 
  Time Complexity:
  - Average/Worst Case: O(log n)
 
  Space Complexity:
  - O(1)
 
  Approach:
  1. Find the smallest Fibonacci number >= array length.
  2. Use Fibonacci numbers to determine probe positions.
  3. Narrow down search range until the target is found or range collapses.
 */

public class FibonacciSearch {

    // Function to perform Fibonacci Search
    public static int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;

        // Step 1: Initialize Fibonacci numbers
        int fib2 = 0;  // (m-2)-th Fibonacci number
        int fib1 = 1;  // (m-1)-th Fibonacci number
        int fibM = fib2 + fib1; // m-th Fibonacci number

        // Step 2: Find the smallest Fibonacci number >= n
        while (fibM < n) {
            fib2 = fib1;
            fib1 = fibM;
            fibM = fib2 + fib1;
        }

        int offset = -1; // Marks eliminated range

        // Step 3: Perform search
        while (fibM > 1) {
            int i = Math.min(offset + fib2, n - 1);

            if (arr[i] < target) {
                // Move to right section
                fibM = fib1;
                fib1 = fib2;
                fib2 = fibM - fib1;
                offset = i;
            } else if (arr[i] > target) {
                // Move to left section
                fibM = fib2;
                fib1 = fib1 - fib2;
                fib2 = fibM - fib1;
            } else {
                return i; // Found
            }
        }

        // Last element check
        if (fib1 == 1 && offset + 1 < n && arr[offset + 1] == target) {
            return offset + 1;
        }

        return -1; // Not found
    }

    // Helper method to print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main method for demonstration
    public static void main(String[] args) {
        int[] numbers = { 2, 5, 8, 12, 16, 23, 38, 56, 72, 91 };
        int target = 23;

        System.out.println("Sorted Array:");
        printArray(numbers);

        int result = fibonacciSearch(numbers, target);

        if (result != -1) {
            System.out.println("Element " + target + " found at index: " + result);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }
}