/*
  Description:
  Ternary Search is a divide-and-conquer algorithm used to find a target element
  in a sorted array. Instead of splitting the array into two halves like Binary Search,
  it divides the search space into three parts using two midpoints.
 
  It repeatedly narrows the search to one of these three regions until the target
  is found or the range becomes too small to continue.
 
  Why Ternary Search?
  - Useful for educational purposes when learning divide-and-conquer techniques.
  - Can be applied to unimodal functions in continuous search (not just arrays),
    though in arrays it performs similarly to binary search.
 
  Use Cases:
  - Sorted datasets.
  - Situations where division into more segments helps illustrate search strategies.
  - Teaching algorithmic problem-solving for beginners and intermediates.
 
  Time Complexity:
  - Average/Worst Case: O(log₃ n)
 
  Space Complexity:
  - O(1)
 
  Approach:
  1. Choose two midpoints: mid1 and mid2.
  2. Compare both with the target:
       - If target equals arr[mid1], return mid1.
       - If target equals arr[mid2], return mid2.
  3. Determine which of the three regions still holds the target.
  4. Repeat the process within the chosen region.
 */

public class TernarySearch {

    // Function to perform Ternary Search
    public static int ternarySearch(int[] arr, int target, int left, int right) {
        while (left <= right) {
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            if (arr[mid1] == target) {
                return mid1; // Found at mid1
            }

            if (arr[mid2] == target) {
                return mid2; // Found at mid2
            }

            if (target < arr[mid1]) {
                right = mid1 - 1; // Search in left segment
            } else if (target > arr[mid2]) {
                left = mid2 + 1;  // Search in right segment
            } else {
                left = mid1 + 1;  // Search in middle segment
                right = mid2 - 1;
            }
        }

        return -1; // Not found
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
        int[] numbers = { 3, 7, 12, 18, 25, 31, 44, 59 };
        int target = 25;

        System.out.println("Sorted Array:");
        printArray(numbers);

        int result = ternarySearch(numbers, target, 0, numbers.length - 1);

        if (result != -1) {
            System.out.println("Element " + target + " found at index: " + result);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }
}