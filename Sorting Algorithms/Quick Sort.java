/*
  
  Description:
  Quick Sort is one of the fastest and most widely used sorting algorithms.
  It follows the "Divide and Conquer" strategy — choosing a pivot element,
  partitioning the array around that pivot, and then recursively sorting
  the partitions.
  
  Its brilliance lies in how it organizes elements with minimal overhead,
  and when implemented properly, it can outperform most other algorithms.
  
  Use Cases:
  - When performance is key and average efficiency matters.
  - Great for large datasets where in-place sorting is beneficial.
  - Excellent for demonstrating divide and conquer in a more dynamic way.
  
  Time Complexity:
  - Best Case: O(n log n)
  - Average Case: O(n log n)
  - Worst Case: O(n²) → occurs when pivot selection is poor (e.g., already sorted array).
  
  Space Complexity:
  - O(log n) → due to recursive stack calls.
  
  Approach:
  1. Choose a pivot element.
  2. Partition the array — elements smaller than pivot go left, larger ones go right.
  3. Recursively apply Quick Sort to both subarrays.
  4. Continue until the entire array is sorted.
 */

public class QuickSort {

    // Partition function — places pivot in the correct position
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choosing the last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Recursive Quick Sort function
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);

            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
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
        int[] numbers = { 29, 10, 14, 37, 13 };

        System.out.println("Before sorting:");
        printArray(numbers);

        quickSort(numbers, 0, numbers.length - 1);

        System.out.println("After sorting:");
        printArray(numbers);
    }
}
