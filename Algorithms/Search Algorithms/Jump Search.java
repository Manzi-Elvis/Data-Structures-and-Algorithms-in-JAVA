/*
  Description:
  Jump Search is a searching algorithm designed for sorted arrays. Instead of 
  checking every element one by one (like Linear Search), it “jumps” ahead 
  by fixed blocks to quickly narrow down where the target might be. 
 
  Once the block that *might* contain the target is found, the algorithm 
  switches to a simple linear scan within that block. 
 
  This blend of skipping and scanning makes Jump Search efficient on 
  large sorted datasets where Binary Search might not be preferred.
 
  Use Cases:
  - When the array is sorted and random access is cheap.
  - When you want something faster than Linear Search but simpler than Binary Search.
  - Ideal for teaching the trade-off between skipping and scanning.
 
  Time Complexity:
  - Best Case: O(1)
  - Average Case: O(√n)
  - Worst Case: O(√n)
 
  Space Complexity:
  - O(1) → no extra memory needed.
 
  Approach:
  1. Choose a block size (commonly √n).
  2. Jump ahead through the array in steps of that block size.
  3. Stop when you either find the target or jump past where it should be.
  4. Perform a Linear Search backwards in the identified block.
  5. If found, return the index; otherwise, return -1.
 */

public class JumpSearch {

    // Function to perform Jump Search
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;

        if (n == 0) return -1;

        // Optimal block size: sqrt(n)
        int blockSize = (int) Math.sqrt(n);
        int start = 0;
        int end = blockSize;

        // Step 1: Jump in blocks until target <= arr[end] or end reaches array limit
        while (end < n && arr[end] < target) {
            start = end;
            end += blockSize;
        }

        // Step 2: Linear search within identified block
        for (int i = start; i < Math.min(end, n); i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1; // Element not found
    }

    // Helper method to print array values
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Main method for demonstration
    public static void main(String[] args) {
        int[] sortedNumbers = { 3, 8, 12, 18, 25, 30, 41, 55, 60 };
        int target = 30;

        System.out.println("Array elements:");
        printArray(sortedNumbers);

        int result = jumpSearch(sortedNumbers, target);

        if (result != -1) {
            System.out.println("Element " + target + " found at index: " + result);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }
}