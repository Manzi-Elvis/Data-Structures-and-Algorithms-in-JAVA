/*
  Description:
  Exponential Search is a fast searching algorithm designed for sorted arrays.
  It works by first finding a range where the target value may exist—by expanding
  the boundary *exponentially* (1, 2, 4, 8, 16...)—and then performing a Binary Search
  in that narrowed range.
 
  Why this is powerful:
  - You skip large portions of the array quickly.
  - Perfect when the element is near the beginning.
  - Useful for large sorted datasets.
 
  Use Cases:
  - Searching in huge sorted arrays.
  - Situations where the element is expected to appear early.
  - Optimization over binary search when bounds are unknown.
 
  Time Complexity:
  - Best Case: O(1)
  - Average/Worst Case: O(log n)
 
  Space Complexity:
  - O(1)
 
  Approach:
  1. If the first element is the target, return index 0 immediately.
  2. Start with a bound = 1.
  3. Keep doubling the bound until:
       - target ≤ arr[bound], or
       - bound exceeds array length.
  4. Perform Binary Search between previous bound/2 and current bound.
 */

public class ExponentialSearch {

    // Function to perform Binary Search (used internally by Exponential Search)
    public static int binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Found
            } else if (arr[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return -1; // Not found
    }

    // Exponential Search function
    public static int exponentialSearch(int[] arr, int target) {

        // Step 1: Check first element
        if (arr[0] == target) {
            return 0;
        }

        int bound = 1;

        // Step 2: Find the range exponentially
        while (bound < arr.length && arr[bound] <= target) {
            bound *= 2;
        }

        // Step 3: Binary search within the found bounds
        int left = bound / 2;
        int right = Math.min(bound, arr.length - 1);

        return binarySearch(arr, left, right, target);
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
        int[] numbers = { 3, 5, 7, 9, 12, 15, 20, 25, 30, 40, 50 };
        int target = 25;

        System.out.println("Sorted Array:");
        printArray(numbers);

        int result = exponentialSearch(numbers, target);

        if (result != -1) {
            System.out.println("Element " + target + " found at index: " + result);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }
}