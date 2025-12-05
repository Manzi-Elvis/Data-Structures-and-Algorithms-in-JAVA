/*
  
  Description:
  Bubble Sort is one of the simplest sorting algorithms. It repeatedly compares
  adjacent elements and swaps them if they are in the wrong order. This process
  continues until the array is completely sorted.
  
  Use Cases:
  - When you need a simple sorting algorithm for small datasets.
  - Good for educational purposes to understand the concept of comparisons and swaps.
  - Not ideal for large datasets (inefficient compared to more advanced algorithms).
 
  Time Complexity:
  - Best Case: O(n)  → when the array is already sorted.
  - Average Case: O(n²)
  - Worst Case: O(n²)
  
  Space Complexity:
  - O(1) → in-place sorting (no extra memory used)
  
  Approach:
  1. Loop through the array multiple times.
  2. On each pass, compare adjacent elements.
  3. If one element is larger than the next, swap them.
  4. With every pass, the largest element "bubbles up" to the end.
  5. Repeat until no swaps are needed (the array is sorted).
 */

public class BubbleSort {

    // Function to perform Bubble Sort
    public static void bubbleSort(int[] arr) {
        boolean swapped;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Compare each pair of adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap if elements are in the wrong order
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no elements were swapped, array is already sorted
            if (!swapped) {
                break;
            }
        }
    }

    // Helper method to print the array
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Main method for demonstration
    public static void main(String[] args) {
        int[] numbers = { 64, 25, 12, 22, 11 };

        System.out.println("Before sorting:");
        printArray(numbers);

        bubbleSort(numbers);

        System.out.println("After sorting:");
        printArray(numbers);
    }
}
