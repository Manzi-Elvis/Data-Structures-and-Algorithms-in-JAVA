/*
  
  Description:
  Binary Search is one of the most efficient searching algorithms for sorted datasets.
  Instead of checking each element one by one, it repeatedly divides the search range
  in half — dramatically reducing the number of comparisons.
  
  The core idea is simple but powerful:
  - If the target value is smaller than the middle element, focus on the left half.
  - If it’s larger, focus on the right half.
  - Repeat until the value is found or the range becomes empty.
  
  Use Cases:
  - When the dataset is already sorted (ascending or descending).
  - When performance matters and you need logarithmic time searching.
  - Excellent for teaching the concept of divide and conquer.
  
  Time Complexity:
  - Best Case: O(1)   → target found at the middle on the first try.
  - Average Case: O(log n)
  - Worst Case: O(log n)
  
  Space Complexity:
  - O(1) → iterative approach, no extra space needed.
  
  Approach:
  1. Start with the full range of the array.
  2. Find the middle index.
  3. If the middle element equals the target, return its index.
  4. If the target is smaller, search the left half.
  5. If the target is larger, search the right half.
  6. Repeat until the range is empty (target not found).
 */

public class BinarySearch {

    // Function to perform Binary Search
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevent potential overflow

            if (arr[mid] == target) {
                return mid; // Target found
            }

            if (arr[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return -1; // Target not found
    }

    // Helper method to print array elements
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main method for demonstration
    public static void main(String[] args) {
        int[] numbers = { 5, 12, 19, 26, 33, 47, 59, 62, 78, 91 };
        int target = 33;

        System.out.println("Array elements:");
        printArray(numbers);

        int result = binarySearch(numbers, target);

        if (result != -1) {
            System.out.println("Element " + target + " found at index: " + result);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }
}
