/*

  Description:
  Linear Search (also known as Sequential Search) is the simplest search algorithm.
  It checks every element in the array, one by one, until it finds the target value
  or reaches the end of the array. 
  
  Even though it's not the fastest for large datasets, it's one of the most
  intuitive algorithms — perfect for understanding how searching begins at the
  most fundamental level.
  
  Use Cases:
  - When the dataset is small or unsorted.
  - When you just need a quick and simple search solution.
  - When you’re introducing beginners to algorithmic thinking.
  
  Time Complexity:
  - Best Case: O(1)   → target found at the first element.
  - Average Case: O(n)
  - Worst Case: O(n)  → target not found or found at the end.
  
  Space Complexity:
  - O(1) → no extra memory used.
  
  Approach:
  1. Start from the first element in the array.
  2. Compare each element with the target value.
  3. If a match is found, return its index.
  4. If the end of the array is reached without finding the value, return -1.
 */

public class LinearSearch {

    // Function to perform Linear Search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Target found at index i
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
        int[] numbers = { 10, 23, 45, 70, 11, 15 };
        int target = 70;

        System.out.println("Array elements:");
        printArray(numbers);

        int result = linearSearch(numbers, target);

        if (result != -1) {
            System.out.println("Element " + target + " found at index: " + result);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }
}
