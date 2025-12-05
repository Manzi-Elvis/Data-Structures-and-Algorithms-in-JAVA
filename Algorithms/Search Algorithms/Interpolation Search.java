/*
 
 
  Description:
  Interpolation Search is an improvement over Binary Search for data that is 
  uniformly distributed. While Binary Search always checks the middle, 
  Interpolation Search tries to estimate *where* the target might be based 
  on its value — just like looking up a word in a dictionary by guessing 
  roughly where it should be.
 
  This makes it significantly faster than Binary Search on predictable datasets,
  but much less effective on uneven or randomly distributed ones.
 
  Use Cases:
  - When dealing with a large, sorted, and evenly distributed dataset.
  - Useful for understanding how search algorithms can “predict” positions.
  - Helpful when seeking performance gains beyond traditional binary search.
 
  Time Complexity:
  - Best Case: O(1)   → when the target is exactly where we estimate it.
  - Average Case: O(log log n)
  - Worst Case: O(n)  → occurs when data is highly non-uniform.
 
  Space Complexity:
  - O(1) → no additional memory required.
 
  Approach:
  1. Start with two pointers: low and high.
  2. Estimate the probable position of the target using:
       pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low])
  3. If value at pos is the target, return pos.
  4. If the value is smaller, move low up.
  5. If the value is larger, move high down.
  6. Repeat until low exceeds high or the target is found.
 */

public class InterpolationSearch {

    // Function to perform Interpolation Search
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {

            // Prevent division by zero
            if (arr[low] == arr[high]) {
                if (arr[low] == target) {
                    return low;
                }
                break;
            }

            // Estimate the position
            int pos = low + (int)(((double)(target - arr[low]) 
                        / (arr[high] - arr[low])) * (high - low));

            // If target found
            if (arr[pos] == target) {
                return pos;
            }

            // If target is larger, target is in the upper part
            if (arr[pos] < target) {
                low = pos + 1;
            }
            // If target is smaller, target is in the lower part
            else {
                high = pos - 1;
            }
        }

        return -1; // Not found
    }

    // Helper method to print array elements
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Main method for demonstration
    public static void main(String[] args) {
        int[] sortedNumbers = { 10, 20, 30, 40, 50, 60, 70 };
        int target = 40;

        System.out.println("Array elements:");
        printArray(sortedNumbers);

        int result = interpolationSearch(sortedNumbers, target);

        if (result != -1) {
            System.out.println("Element " + target + " found at index: " + result);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }
}