/*

  
  Description:
  Merge Sort is a powerful, efficient, and stable sorting algorithm based on the
  "Divide and Conquer" principle. It works by dividing the array into halves,
  sorting each half recursively, and then merging the sorted halves together.
  
  What makes Merge Sort special is its predictable time complexity and the
  structured way it handles data — it doesn’t rely on random swaps, but on
  systematic merging of already sorted parts.
  
  Use Cases:
  - When working with large datasets.
  - When stability (preserving order of equal elements) is important.
  - When you want consistent O(n log n) performance.
  
  Time Complexity:
  - Best Case: O(n log n)
  - Average Case: O(n log n)
  - Worst Case: O(n log n)
  
  Space Complexity:
  - O(n) → requires temporary arrays for merging.
  
  Approach:
  1. Divide the array into two halves.
  2. Recursively sort each half.
  3. Merge the sorted halves into a single sorted array.
  4. Continue until the entire array is sorted.
 */

public class MergeSort {

    // Function to merge two sorted subarrays
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) leftArray[i] = arr[left + i];
        for (int j = 0; j < n2; j++) rightArray[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        while (i < n1) arr[k++] = leftArray[i++];
        while (j < n2) arr[k++] = rightArray[j++];
    }

    // Recursive Merge Sort function
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
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
        int[] numbers = { 38, 27, 43, 3, 9, 82, 10 };

        System.out.println("Before sorting:");
        printArray(numbers);

        mergeSort(numbers, 0, numbers.length - 1);

        System.out.println("After sorting:");
        printArray(numbers);
    }
}