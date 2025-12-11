/*
  Description:
  --------------------
  Bucket Sort is a distribution-based sorting algorithm that works by dividing
  elements into several buckets, sorting each bucket individually, and then
  merging all buckets to get the final sorted array.
 
  Instead of comparing elements directly (like Quick Sort or Merge Sort),
  Bucket Sort distributes values into ranges — making it extremely efficient 
  for uniformly distributed data.
 
  Why It Works:
  --------------------
  By grouping values into buckets based on their ranges, Bucket Sort reduces 
  the total amount of work needed. Sorting smaller buckets separately is much 
  faster than sorting one large list.
 
  Use Cases:
  --------------------
  - Sorting floating-point numbers in the range [0, 1)
  - Large datasets that are uniformly or evenly distributed
  - When linear-time complexity is preferred and the input distribution fits
 
  Time Complexity:
  --------------------
  - Best Case: O(n + k) → when elements are evenly distributed across buckets
  - Average Case: O(n + k)
  - Worst Case: O(n²)   → when all elements fall into one bucket
 
  Space Complexity:
  --------------------
  - O(n + k) → requires extra buckets
 
  Approach:
  --------------------
  1. Create `k` empty buckets.
  2. Insert each input value into its appropriate bucket.
  3. Sort individual buckets (often using any stable sort).
  4. Concatenate all buckets back into the original array.
 */

import java.util.*;

public class BucketSort {

    // Main Bucket Sort Function
    public static void bucketSort(float[] arr) {
        int n = arr.length;

        if (n <= 0) return;

        // Step 1: Create buckets (array of lists)
        @SuppressWarnings("unchecked")
        List<Float>[] buckets = new List[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Step 2: Distribute elements into buckets
        for (float value : arr) {
            int bucketIndex = (int) (value * n); // map value to bucket
            buckets[bucketIndex].add(value);
        }

        // Step 3: Sort each bucket individually
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Step 4: Concatenate all buckets into final array
        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float value : bucket) {
                arr[index++] = value;
            }
        }
    }

    // Helper method to print the array
    public static void printArray(float[] arr) {
        for (float value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        float[] numbers = { 0.42f, 0.32f, 0.23f, 0.52f, 0.25f, 0.47f, 0.51f };

        System.out.println("Before Sorting:");
        printArray(numbers);

        bucketSort(numbers);

        System.out.println("After Sorting:");
        printArray(numbers);
    }
}