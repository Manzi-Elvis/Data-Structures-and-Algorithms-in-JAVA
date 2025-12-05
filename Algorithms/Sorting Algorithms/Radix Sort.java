/*
  Description:
  Radix Sort is a non-comparison integer sorting algorithm that sorts numbers
  digit by digit, starting from the least significant digit (LSD) to the
  most significant digit (MSD). It uses a stable counting sort as a subroutine
  to sort numbers based on individual digits.
 
  Radix Sort is very efficient for sorting large sets of integers, especially
  when the number of digits (k) is not significantly greater than the number
  of elements (n).
 
  Use Cases:
  - Large datasets of integers.
  - Situations where stability in sorting is required.
  - Excellent for educational purposes to understand non-comparison sorts.
 
  Time Complexity:
  - Best/Average/Worst Case: O(n * k), where n = number of elements, k = number of digits.
 
  Space Complexity:
  - O(n + k) → for temporary counting arrays.
 
  Approach:
  1. Find the maximum number to know the number of digits.
  2. Sort the array digit by digit using Counting Sort.
  3. Start from the least significant digit and move to the most significant.
 */

public class RadixSort {

    // A utility method to get the maximum value in arr[]
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // A method to do counting sort of arr[] according to the digit represented by exp
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n]; // output array
        int[] count = new int[10]; // digits 0 to 9

        // Store count of occurrences of each digit
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Change count[i] so that it contains actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy output array to arr[]
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Main Radix Sort method
    public static void radixSort(int[] arr) {
        int max = getMax(arr);

        // Apply counting sort to each digit, starting with LSD
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    // Helper method to print array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main method for demonstration
    public static void main(String[] args) {
        int[] numbers = { 170, 45, 75, 90, 802, 24, 2, 66 };

        System.out.println("Before sorting:");
        printArray(numbers);

        radixSort(numbers);

        System.out.println("After sorting:");
        printArray(numbers);
    }
}