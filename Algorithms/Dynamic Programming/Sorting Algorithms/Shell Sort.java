/*
  Description:
  Shell Sort is an optimization over Insertion Sort. Instead of comparing
  adjacent elements, it allows comparisons of elements that are far apart
  by using a “gap” sequence. Over multiple passes, the gap reduces until
  it becomes 1, which is equivalent to a final insertion sort.
 
  This approach helps elements move quickly to their correct positions,
  significantly improving efficiency over standard Insertion Sort on larger arrays.
 
  Use Cases:
  - When you want a simple yet faster alternative to Insertion Sort.
  - Suitable for medium-sized datasets.
  - Great for teaching the concept of “gap-based sorting.”
 
  Time Complexity:
  - Best Case: O(n log n) (depending on gap sequence)
  - Average Case: O(n^(3/2))
  - Worst Case: O(n^2)
 
  Space Complexity:
  - O(1) → in-place sorting
 
  Approach:
  1. Choose an initial gap (commonly n/2).
  2. Compare and sort elements at this gap distance.
  3. Reduce the gap and repeat until the gap is 1.
  4. Perform a final insertion sort pass when gap = 1.
 */

public class ShellSort {

    // Function to perform Shell Sort
    public static void shellSort(int[] arr) {
        int n = arr.length;

        // Start with a big gap, then reduce it
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform insertion sort for this gap
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;

                // Shift earlier gap-sorted elements up until the correct position is found
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                arr[j] = temp;
            }
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
        int[] numbers = { 23, 12, 1, 8, 34, 54, 2, 3 };
        
        System.out.println("Before sorting:");
        printArray(numbers);

        shellSort(numbers);

        System.out.println("After sorting:");
        printArray(numbers);
    }
}
