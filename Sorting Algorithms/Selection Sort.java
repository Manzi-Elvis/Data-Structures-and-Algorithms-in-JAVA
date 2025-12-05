/*
  
 * Description:
 * Selection Sort is a straightforward sorting algorithm that divides the array 
 * into two parts: a sorted portion and an unsorted portion. 
 * It repeatedly selects the smallest element from the unsorted part 
 * and moves it to the end of the sorted part.
 * 
 * Use Cases:
 * - Useful when memory is limited since it performs sorting in-place.
 * - Great for small datasets where simplicity is preferred over performance.
 * - Often used in educational settings to teach sorting logic and selection techniques.
 * 
 * Time Complexity:
 * - Best Case: O(n²)
 * - Average Case: O(n²)
 * - Worst Case: O(n²)
 * 
 * Space Complexity:
 * - O(1) → in-place sorting, requires no extra memory.
 * 
 * Approach:
 * 1. Loop through the array.
 * 2. On each iteration, assume the current index is the smallest.
 * 3. Compare it with every element to the right to find the true smallest value.
 * 4. Swap the smallest value with the current index.
 * 5. Repeat until the entire array is sorted.
 */

public class SelectionSort {

    // Function to perform Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // Assume the current index is the smallest
            int minIndex = i;

            // Find the index of the smallest element in the remaining unsorted array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first unsorted element
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
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

        selectionSort(numbers);

        System.out.println("After sorting:");
        printArray(numbers);
    }
}
