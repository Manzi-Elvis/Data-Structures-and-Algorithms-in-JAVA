/*
 
 
  Description:
  Counting Sort is a non-comparison sorting algorithm designed for datasets
  where the range of values is known and not excessively large. Instead of 
  comparing elements, it counts how many times each value appears, then uses 
  those counts to place each element directly into its correct position.
 
  It's incredibly fast when used under the right conditions — often beating 
  comparison-based algorithms — but it requires additional memory for its 
  counting array and works only with non-negative integers (or integers that 
  can be shifted into that range).
 
  Use Cases:
  - When sorting integers within a limited range.
  - Useful in scenarios where stability matters and speed is critical.
  - Ideal for teaching how sorting can be done without comparisons.
 
  Time Complexity:
  - Best Case: O(n + k)
  - Average Case: O(n + k)
  - Worst Case: O(n + k)
    (where n = number of elements, k = range of input values)
 
  Space Complexity:
  - O(k) for the counting array.
 
  Approach:
  1. Find the maximum value in the array (to determine range).
  2. Build a count array where each index tracks how many times a value occurs.
  3. Transform the count array into a prefix sum array to determine positions.
  4. Build the output array by placing each element in its sorted position.
  5. Copy the output back into the original array.
 */

public class CountingSort {

    // Function to perform Counting Sort
    public static void countingSort(int[] arr) {
        if (arr.length == 0) return;

        // Step 1: Find maximum value
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        // Step 2: Create a count array
        int[] count = new int[max + 1];

        for (int num : arr) {
            count[num]++;
        }

        // Step 3: Convert count[] to prefix sum array
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Step 4: Build the output array
        int[] output = new int[arr.length];

        // Loop backwards to keep Counting Sort stable
        for (int i = arr.length - 1; i >= 0; i--) {
            int value = arr[i];
            output[count[value] - 1] = value;
            count[value]--;
        }

        // Step 5: Copy sorted output back into original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    // Helper method to print arrays
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main method for demonstration
    public static void main(String[] args) {
        int[] numbers = { 4, 2, 2, 8, 3, 3, 1 };

        System.out.println("Before sorting:");
        printArray(numbers);

        countingSort(numbers);

        System.out.println("After sorting:");
        printArray(numbers);
    }
}