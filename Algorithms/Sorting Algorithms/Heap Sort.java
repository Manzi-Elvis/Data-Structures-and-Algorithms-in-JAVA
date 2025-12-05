/*
 
  Description:
  Heap Sort is a comparison-based sorting algorithm built on top of a binary heap.
  It treats the array like a special kind of tree structure where every parent node
  is larger (in a max heap) than its children. Once the heap is built, the algorithm
  repeatedly extracts the largest element and places it at the end of the array.
  
  What sets Heap Sort apart is its predictable performance and its in-place nature —
  no extra arrays, no complicated merging. Everything happens right inside the original
  array while still guaranteeing O(n log n) time complexity.
  
  Use Cases:
  - When you need guaranteed O(n log n) performance without using extra memory.
  - Ideal for scenarios where recursion depth (like in Quick Sort) is a concern.
  - Great for teaching how tree structures can be represented using arrays.
  
  Time Complexity:
  - Best Case: O(n log n)
  - Average Case: O(n log n)
  - Worst Case: O(n log n)
  
  Space Complexity:
  - O(1) → in-place sorting.
  
  Approach:
  1. Build a max heap from the unsorted array.
  2. The largest element now sits at the root (index 0).
  3. Swap it with the last element of the heap.
  4. Reduce the heap size and restore the heap by "heapifying".
  5. Repeat until the entire array is sorted.
 */

public class HeapSort {

    // Function to turn a subtree into a max heap
    private static void heapify(int[] arr, int size, int root) {
        int largest = root;
        int leftChild = 2 * root + 1;
        int rightChild = 2 * root + 2;

        // Check if left child exists and is greater
        if (leftChild < size && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        // Check if right child exists and is greater
        if (rightChild < size && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        // If the root isn't the largest, swap and continue heapifying
        if (largest != root) {
            int temp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = temp;

            heapify(arr, size, largest);
        }
    }

    // Main Heap Sort function
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Step 1: Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Step 2: Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move the current largest element to the end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Restore the heap with reduced size
            heapify(arr, i, 0);
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
        int[] numbers = { 21, 1, 45, 78, 3, 5 };

        System.out.println("Before sorting:");
        printArray(numbers);

        heapSort(numbers);

        System.out.println("After sorting:");
        printArray(numbers);
    }
}