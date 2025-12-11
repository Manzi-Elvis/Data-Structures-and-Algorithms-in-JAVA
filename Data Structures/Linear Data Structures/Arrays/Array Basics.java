/*
  Description:
  --------------------
  This file introduces the absolute fundamentals of working with arrays in Java.
  Arrays are the backbone of many algorithms — fixed-size, index-based collections 
  that allow constant-time access to elements.
 
  This class demonstrates:
  - Declaring arrays
  - Initializing them
  - Accessing values
  - Updating values
  - Iterating through an array
 
  Use Cases:
  --------------------
  - Storing a fixed number of elements
  - Fast access by index (O(1))
  - Foundation for more advanced structures (Dynamic Arrays, Hash Tables, Heaps)
 
  Time Complexity of Common Operations:
  --------------------
  - Access: O(1)
  - Update: O(1)
  - Search (Linear): O(n)
  - Insertion/Deletion at specific positions: O(n)
 
  Approach:
  --------------------
  Think of an array as a row of lockers with fixed slots.  
  You know exactly which locker you're opening, and each slot stores a value.
 */

public class ArrayBasics {

    public static void main(String[] args) {

        // Declaring an array
        int[] numbers = new int[5];

        // Initializing values
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;

        // Accessing values
        System.out.println("Element at index 2: " + numbers[2]);

        // Updating a value
        numbers[3] = 99;

        // Iterating over the array
        System.out.println("All elements:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}