/*
 
  Description:
  Insertion Sort builds the sorted array one element at a time.
  It takes each element and places it in its correct position among
  the previously sorted elements — just like how you’d sort playing cards in your hand.
  
  Though it’s not the most efficient for large datasets, its simplicity
  and logical flow make it one of the best algorithms for beginners to
  visualize and understand sorting behavior.
  
  Use Cases:
  - When working with small datasets.
  - When the data is already partially sorted.
  - Great for teaching how sorting algorithms think and build order step-by-step.
  
  Time Complexity:
  - Best Case: O(n)    → when the array is already sorted.
  - Average Case: O(n²)
  - Worst Case: O(n²)
  
  Space Complexity:
  - O(1) → in-place sorting (no extra memory needed).
  
  Approach:
  1. Assume the first element is already sorted.
  2. Take the next element (the “key”) and compare it with elements before it.
  3. Shift larger elements one position ahead to make room.
  4. Insert the key into its correct position.
  5. Repeat until all elements are sorted.
 */

public class InsertionSort {

    // Function to perform Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];      // The element to be positioned
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key, one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key; // Place the key in its correct position
        }
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
        int[] numbers = { 9, 5, 1, 4, 3 };

        System.out.println("Before sorting:");
        printArray(numbers);

        insertionSort(numbers);

        System.out.println("After sorting:");
        printArray(numbers);
    }
}
